package com.k2.common.entityOutputFormatter.outputFormatterContext;

import org.springframework.stereotype.Component;

@Component
public class OutputFormatterContextFactory {
	
	@SuppressWarnings("rawtypes")
	public OutputFormatterContext getContext() { return new OutputFormatterContextImpl(); }

}
