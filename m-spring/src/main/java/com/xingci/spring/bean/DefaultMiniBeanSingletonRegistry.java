package com.xingci.spring.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : DefaultMiniBeanSingletonRegistry v0.1 2020/4/13 15:58 By Kevin.
 * @description :
 */
public class DefaultMiniBeanSingletonRegistry implements MiniBeanSingletonRegistry {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMiniBeanSingletonRegistry.class);

    protected static final Object NULL_OBJECT = new Object();

    /** Cache of singleton objects: bean name --> bean instance */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    /** Set of registered singletons, containing the bean names in registration order */
    private final Set<String> registeredSingletons = new LinkedHashSet<String>(256);

    public void register(String beanName, Object singletonObject) {
        logger.info("开始注册单例Bean");
        synchronized (this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Singleton MiniBean " + beanName + " already register");
            }

            addSingleton(beanName, singletonObject);
        }
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.registeredSingletons.add(beanName);
        }
    }

    public Object get(String beanName) {
        return null;
    }

    public boolean contains(String beanName) {
        return false;
    }
}
