package com.k2.common.entityOutputFormatter.outputSnippet.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Snippet {
	
	public String name();

	public String description();

}
