package com.xingci.tomcat.server;

import com.xingci.tomcat.http.MiniHttpServletRequest;
import com.xingci.tomcat.http.MiniHttpServletResponse;
import com.xingci.tomcat.processor.MiniServletProcessor;
import com.xingci.tomcat.processor.MiniStaticResourceProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniHttpServer v0.1 2020/4/13 12:07 By Kevin.
 * @description :
 */
public class MiniHttpServer {

    public static final String SHUTDOWN = "/SHUTDOWN";

    public static final int PORT = 8090;

    private boolean isShutDown = false;

    public static void main(String[] args) {
        MiniHttpServer httpServer = new MiniHttpServer();

        httpServer.await();
    }

    public void await() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT, 1, InetAddress.getByName("localhost"));
        }catch (Exception e) {
            System.exit(1);
        }

        while (! isShutDown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                MiniHttpServletRequest request = new MiniHttpServletRequest(inputStream);
                MiniHttpServletResponse response = new MiniHttpServletResponse(outputStream, request);

                if (request.getUri() != null && request.getUri().startsWith("/servlet/")) {
//                  //如果是请求Servlet
                    MiniServletProcessor processor = new MiniServletProcessor();
                    processor.process(request, response);
                }else {
                    // 否则，我们认为它是请求静态资源
                    MiniStaticResourceProcessor srp = new MiniStaticResourceProcessor();
                    srp.process(request, response);
                }
                socket.close();
                isShutDown = request.getUri().equals(SHUTDOWN);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
