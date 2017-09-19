package com.k2.common.snippets;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SnippetFactoryImpl<O> implements SnippetFactory<O>, ApplicationContextAware {

	private ApplicationContext snippetContext;
	@Override
	public void setApplicationContext(ApplicationContext snippetContext) throws BeansException { this.snippetContext = snippetContext; }

	@Override
	public <T> T getSnippet(Class<T> snippetClass) { return snippetContext.getBean(snippetClass); }


}
