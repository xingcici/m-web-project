package com.xingci.spring.annotation;

import java.lang.annotation.*;

/**
 * @author : Haifeng.Pang.
 * @version 0.1 : MiniCompontent v0.1 2020/4/13 15:44 By Kevin.
 * @description :
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MiniComponent {

    String value() default "";
}
