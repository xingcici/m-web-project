package com.xingci.spring.annotation;

import java.lang.annotation.*;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniBean v0.1 2020/4/13 15:41 By Kevin.
 * @description :
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MiniBean {

    String value() default "";
}
