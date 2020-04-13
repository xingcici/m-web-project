package com.xingci.tomcat.constant;

import java.io.File;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : Constants v0.1 2020/4/13 12:39 By Kevin.
 * @description :
 */
public class Constants {

    public static final String WEB_ROOT = new File("").getAbsoluteFile().getPath()
            + "\\src\\main\\webapp";

    public static final String MINI_HTTP_SERVLET = "com.xingci.tomcat.servlet.MiniHttpServlet";
}
