package com.xingci.tomcat.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniServlet v0.1 2020/4/13 12:00 By Kevin.
 * @description :
 */
public class MiniHttpServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化 MiniServlet");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("From service");
        PrintWriter out = response.getWriter();
        // 头部信息
        out.write("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" );
        out.println("<h1>Hello " + this.getClass().getSimpleName() + " </h1>");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy。。。");
    }
}
