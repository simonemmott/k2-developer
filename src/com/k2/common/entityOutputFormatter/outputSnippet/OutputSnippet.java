package com.k2.common.entityOutputFormatter.outputSnippet;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;

public interface OutputSnippet<O> {
	
	public OutputSnippet<O> setContext(OutputFormatterContext<?, O> context);
	
	public O output(O output);
	
	public String getType();
	
}
