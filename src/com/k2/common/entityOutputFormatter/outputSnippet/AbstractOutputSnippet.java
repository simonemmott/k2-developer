package com.k2.common.entityOutputFormatter.outputSnippet;

import java.io.PrintWriter;

import com.google.gson.annotations.Expose;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;

public abstract class AbstractOutputSnippet<O> implements OutputSnippet<O> {

	protected OutputFormatterContext<?, O> context;
	@Override
	public OutputSnippet<O> setContext(OutputFormatterContext<?, O> context) {
		this.context = context;
		return this;
	}
	
	@Expose()
	protected String type;
	@Override
	public String getType() { return type; }
	
	protected void addToPrintWriter(PrintWriter pw, String output, boolean newLine) {
		if (newLine) {
			pw.println(context.getCurrentIndendStr()+output);
		} else {
			pw.print(output);
		}	
	}

	protected void addToPrintWriter(PrintWriter pw, String output) {
		addToPrintWriter(pw, output, context.verbose());
	}

}
