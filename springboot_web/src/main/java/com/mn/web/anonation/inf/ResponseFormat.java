package com.mn.web.anonation.inf;

import java.lang.annotation.*;

/**
 * 返回格式-注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseFormat {
}
