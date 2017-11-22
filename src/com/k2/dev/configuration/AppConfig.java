package com.k2.dev.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@EnableTransactionManagement
@EnableSpringConfigured
@EnableAspectJAutoProxy
@EnableLoadTimeWeaving(aspectjWeaving=EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
@ComponentScan({
		"com.k2.common, "
		+ "com.k2.core, "
		+ "com.k2.dev.dao, "
		+ "com.k2.dev.configuration, "
		+ "com.k2.dev.model, "
		+ "com.k2.dev.service, "
		+ "com.k2.dev.snippets, "
		+ "com.k2.dev.usage"
		})
public class AppConfig {
	
	@SuppressWarnings("unused")
	@Autowired
	private Environment env;
	
	@Bean
	Gson getGson() {
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
	}


}
