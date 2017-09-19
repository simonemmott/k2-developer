package com.k2.dev.web;

import java.io.File;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.k2.dev.configuration.AppConfig;

public class K2WebInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Loading web config!");
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() { 
		return new String[] { "/" , "/*"}; 
	}

}
