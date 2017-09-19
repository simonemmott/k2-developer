package com.k2.dev.snippets;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import com.k2.common.snippets.AbstractSnippetConfig;

@Configuration
@EnableSpringConfigured
@ComponentScan({"com.k2.common.snippets.html, com.k2.core.snippets.java"})
@PropertySource("file:${K2_HOME}/conf/k2.properties")
public class SnippetConfig extends AbstractSnippetConfig {
	
}
