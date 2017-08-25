package com.yatou.automation.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindBy {

    String description() default "";

    String id() default "";

    String name() default "";

    String className() default "";

    String css() default "";

    String tagName() default "";

    String linkText() default "";

    String partialLinkText() default "";

    String xpath() default "";
}