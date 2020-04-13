package com.xingci.tomcat.http;

import com.xingci.tomcat.constant.Constants;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniHttpServletResponse v0.1 2020/4/13 12:36 By Kevin.
 * @description :
 */
public class MiniHttpServletResponse implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    private OutputStream out;
    private MiniHttpServletRequest servletRequest;
    private PrintWriter writer;

    public MiniHttpServletResponse(OutputStream out, MiniHttpServletRequest servletRequest) {
        this.out = out;
        this.servletRequest = servletRequest;
    }

    public void sendStaticResources() throws IOException {
        FileInputStream fis = null;
        try{
            File file = new File(Constants.WEB_ROOT, servletRequest.getUri());
            fis = new FileInputStream(file);

            // 头部信息
            out.write(
                    ("HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n" +
                            "\r\n" ).getBytes());

            byte[] bytes = new byte[BUFFER_SIZE];
            int ch = -1;
            while ( (ch = fis.read(bytes, 0, BUFFER_SIZE) )!=-1) {
                out.write(bytes, 0, ch);
            }

        } catch (FileNotFoundException e) {
            String notFoundMessage =
                    "HTTP/1.1 404 FILE NOT FOUND\r\n" +
                            "Content-Type: text/html\r\n" +
                            "Content-Length: 28\r\n" +
                            "\r\n" +
                            "<h1>404: FILE NOT FOUND</h1>";
            out.write(notFoundMessage.getBytes());
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            if(fis != null){
                fis.close();
            }
        }
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        // true stand for autoFlush
        writer = new PrintWriter(out, true);
        return writer;
    }

    public void setCharacterEncoding(String charset) {

    }

    public void setContentLength(int len) {

    }

    public void setContentLengthLong(long len) {

    }

    public void setContentType(String type) {

    }

    public void setBufferSize(int size) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale loc) {

    }

    public Locale getLocale() {
        return null;
    }
}
