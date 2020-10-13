package com.bulain.common.dataset;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSet {
    String file() default "";
    String setUp() default "CLEAN_INSERT";
    String tearDown() default "NONE";
}