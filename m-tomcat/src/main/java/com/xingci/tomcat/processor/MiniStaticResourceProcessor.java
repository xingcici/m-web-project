package com.xingci.tomcat.processor;

import com.xingci.tomcat.http.MiniHttpServletRequest;
import com.xingci.tomcat.http.MiniHttpServletResponse;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : StaticResourceProcessor v0.1 2020/4/13 12:44 By Kevin.
 * @description :
 */
public class MiniStaticResourceProcessor {

    public void process(MiniHttpServletRequest servletRequest, MiniHttpServletResponse servletResponse){
        try{
            servletResponse.sendStaticResources();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
