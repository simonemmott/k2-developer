package com.k2.common.snippets;

import org.springframework.context.ApplicationContextAware;

public interface SnippetFactory<O> extends ApplicationContextAware {
	
	public <T> T getSnippet(Class<T> snippetClass);

}
