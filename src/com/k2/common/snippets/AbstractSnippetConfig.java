package com.k2.common.snippets;

import org.springframework.context.annotation.Bean;

public abstract class AbstractSnippetConfig {
	
	@SuppressWarnings("rawtypes")
	@Bean
	SnippetFactory<?> snippetFactory() { return new SnippetFactoryImpl(); }


}
