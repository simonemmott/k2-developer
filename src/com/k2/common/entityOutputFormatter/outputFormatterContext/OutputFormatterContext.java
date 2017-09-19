package com.k2.common.entityOutputFormatter.outputFormatterContext;

import java.util.List;

import com.k2.common.entityOutputFormatter.outputFormatter.OutputFormatter;
import com.k2.common.snippets.SnippetFactory;

public interface OutputFormatterContext<E, O> {
	
//	@SuppressWarnings("rawtypes")
//	public static OutputFormatterContext create() {
//		return new OutputFormatterContextImpl();
//	}
	
	public OutputFormatter<E, O> getCurrentFormatter();
	
	public int getNextID();
		
	public int getCurrentIndent();
	public void indent();
	public void outdent();
	
	public String getCurrentIndendStr();
	
	public OutputFormatterContext<E, O> setVerbose();
	public OutputFormatterContext<E, O> setVerbose(boolean verbose);
	public boolean verbose();

	public OutputFormatterContext<E, O> addFormatter(OutputFormatter<E, O> formatter);

	public OutputFormatter<E, O> removeFormatter();

	public void index(Integer index, Class<?> cls, Object obj);

	public Object getIndexedObject(Integer i);
	
	public void reportException(Exception e);

	public boolean hasErrors();

	public List<Exception> getErrors();
	
	public void setSnippetFactory(SnippetFactory<O> snippetFactory);
	public SnippetFactory<O> getSnippetFactory();
	public <T> T getSnippet(Class<T> SnippetClass);

	public void reset();
	


}
