package com.k2.common.entityOutputFormatter.outputSnippet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SnippetContainer {

	String name();

	String description() default "";

	String[] permitted() default "";

}
