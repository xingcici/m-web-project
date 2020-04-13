package com.xingci.tomcat.processor;

import com.xingci.tomcat.constant.Constants;
import com.xingci.tomcat.http.MiniHttpServletRequest;
import com.xingci.tomcat.http.MiniHttpServletResponse;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniServletProcessor v0.1 2020/4/13 12:45 By Kevin.
 * @description :
 */
public class MiniServletProcessor {

    public void process(MiniHttpServletRequest servletRequest, MiniHttpServletResponse servletResponse){
        String uri = servletRequest.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try{
            // 我们的请求中URL只有一个，所以实例化一个大小为1的URL数组
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            // repository，从此URL目录("仓库")来加载类
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, urlStreamHandler);
            // 从urls指定的url来加载类
            loader = new URLClassLoader(urls);
        } catch (Exception e){
            e.printStackTrace();
        }
        Class<?> clazz = null;
        try{
            clazz = loader.loadClass(Constants.MINI_HTTP_SERVLET);
        } catch (Exception e){
            e.printStackTrace();
        }
        Servlet servlet = null;
        try{
            servlet = (Servlet) clazz.newInstance();
            servlet.service(servletRequest, servletResponse);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
