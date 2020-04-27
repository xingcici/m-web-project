package com.xingci.spring.bean;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniBeanRegistry v0.1 2020/4/13 15:54 By Kevin.
 * @description :
 */
public interface MiniBeanSingletonRegistry {

    void register(String beanName, Object singletonObject);

    Object get(String beanName);

    boolean contains(String beanName);
}
