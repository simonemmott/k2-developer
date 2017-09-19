package com.k2.common.entityOutputFormatter.outputFormatterContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.k2.common.entityOutputFormatter.outputFormatter.OutputFormatter;
import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;
import com.k2.common.snippets.SnippetFactory;

public class OutputFormatterContextImpl<E, O> implements OutputFormatterContext<E, O>{

	private LinkedList<OutputFormatter<E, O>> formatters = new LinkedList<OutputFormatter<E, O>>();
	@Override
	public OutputFormatter<E, O> getCurrentFormatter() { return formatters.getLast(); }
	@Override
	public OutputFormatterContext<E, O> addFormatter(OutputFormatter<E, O> formatter) { formatters.add(formatter); return this; }
	@Override
	public OutputFormatter<E, O> removeFormatter() { return formatters.removeLast(); }

	private int currentId = 0;
	@Override
	public int getNextID() { return currentId++; }

	private HashMap<Integer, Object> index = null;
	@Override
	public void index(Integer i, Class<?> cls, Object obj) {
		if (index == null) { index = new HashMap<Integer, Object>(); }
		index.put(i, obj);
	}
	@Override
	public Object getIndexedObject(Integer i) {
		if (index == null) { return null; }
		return index.get(i);
	}

	private int indent = 0;
	@Override
	public int getCurrentIndent() { return indent; }

	@Override
	public void indent() { indent++; }

	@Override
	public void outdent() { indent--; }

	@Override
	public String getCurrentIndendStr() {
		if (indent == 0) { return ""; }
		String retVal = "";
		for (int i=0; i < indent;  i++) { retVal = retVal+"  "; }
		return retVal;
	}
	
	private boolean verbose = false;

	@Override
	public OutputFormatterContext<E, O> setVerbose() { return setVerbose(true); }
	@Override
	public OutputFormatterContext<E, O> setVerbose(boolean verbose) { this.verbose = verbose; return this; }

	@Override
	public boolean verbose() { return verbose; }
	
	private List<Exception> errors;
	@Override
	public void reportException(Exception e) {
		if (errors == null) { errors = new ArrayList<Exception>(); }
		errors.add(e);
	}
	@Override
	public boolean hasErrors() {
		if (errors == null) { return false; }
		return !errors.isEmpty();
	}
	@Override
	public List<Exception> getErrors() { return errors; }
	
	private SnippetFactory<O> snippetFactory;
	@Override
	public void setSnippetFactory(SnippetFactory<O> snippetFactory) { this.snippetFactory = snippetFactory; }
	@Override
	public SnippetFactory<O> getSnippetFactory() { return snippetFactory; }
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getSnippet(Class<T> snippetClass) { 
		T retVal = snippetFactory.getSnippet(snippetClass);
		((OutputSnippet<O>) retVal).setContext(this);
		return retVal;
	}
	@Override
	public void reset() {
		indent = 0;
		errors = null;
		index = null;
		currentId = 0;
		
		
	}

}
