package com.cyn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author G0dc
 * @description:
 * @date 2022/11/3 13:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BloomFilterAnno {
    /**
     * 用于拼接Redis的key
     * @return
     */
    String key();
}
