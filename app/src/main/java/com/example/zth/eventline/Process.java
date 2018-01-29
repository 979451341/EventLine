package com.example.zth.eventline;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ZTH on 2018/1/25.
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Process {
    int value() default 0;

}
