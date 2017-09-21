package com.k2.dev.templates;

import java.util.ArrayList;
import java.util.List;
import com.k2.common.entityOutputFormatter.outputTemplate.OutputTemplate;
import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;
import com.k2.common.entityOutputFormatter.outputTemplate.AbstractOutputTemplate;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlTitle;
import com.k2.common.snippets.html.K2HTMLPage;
import com.k2.common.snippets.html.K2Panel;
import com.k2.common.snippets.html.K2TextField;



public class ListViewTemplate<O> extends AbstractOutputTemplate<O> implements OutputTemplate<O> {
	
	
	@SuppressWarnings("unchecked")
	private K2HTMLPage<O> template = context.getSnippet(K2HTMLPage.class);
  
	private java.lang.String listOf;
	public ListViewTemplate<O> setListOf(java.lang.String listOf) { this.listOf = listOf; return this; }
	public java.lang.String getListOf() { return listOf; }
  
	private java.lang.String fieldLabel;
	public ListViewTemplate<O> setFieldLabel(java.lang.String fieldLabel) { this.fieldLabel = fieldLabel; return this; }
	public java.lang.String getFieldLabel() { return fieldLabel; }
  
	private List<OutputSnippet<O>> body1;
	public ListViewTemplate<O> addToBody1(OutputSnippet<O> snippet) {
		snippet.setContext(context);
		if (body1 == null) { body1 = new ArrayList<OutputSnippet<O>>(); }
		body1.add(snippet);
		return this;
	}
  
	private List<OutputSnippet<O>> body2;
	public ListViewTemplate<O> addToBody2(OutputSnippet<O> snippet) {
		snippet.setContext(context);
		if (body2 == null) { body2 = new ArrayList<OutputSnippet<O>>(); }
		body2.add(snippet);
		return this;
	}
  
	@SuppressWarnings("unchecked")
	@Override
	public O output(O output) {
    
		template.setContext(context);
    
		{
    			HtmlTitle<O> title = context.getSnippet(HtmlTitle.class);
    			if ((listOf != null) && (!"".equals(listOf))) {
    				title.setTitle("List of "+listOf);
    			} else {
    				title.setTitle("List");
    			}
    			title.setLevel(1);
    			template.addToBody(title);
		}
		{
    			HtmlButton<O> newButton = context.getSnippet(HtmlButton.class);
    			newButton.setLabel("New");
    			newButton.setAction("new");
    			template.addToBody(newButton);
		}
		{
			for (OutputSnippet<O> s : body1) { template.addToBody(s); }
		}
		{
			HtmlButton<O> newButton = context.getSnippet(HtmlButton.class);
			newButton.setLabel("Canel");
			newButton.setAction("cancel");
			template.addToBody(newButton);
		}
		return template.output(output);
	}

}
