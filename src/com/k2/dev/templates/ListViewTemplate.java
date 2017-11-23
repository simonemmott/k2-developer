package com.k2.dev.templates;

import java.util.ArrayList;
import java.util.List;
import com.k2.common.entityOutputFormatter.outputTemplate.OutputTemplate;
import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;
import com.k2.common.entityOutputFormatter.outputTemplate.AbstractOutputTemplate;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.common.snippets.html.HtmlPage;



public class ListViewTemplate<O> extends AbstractOutputTemplate<O> implements OutputTemplate<O> {
	
	
	@SuppressWarnings("unchecked")
	private HtmlPage<O> template = context.getSnippet(HtmlPage.class);
  
	private java.lang.String listOf;
	public ListViewTemplate<O> setListOf(java.lang.String listOf) { this.listOf = listOf; return this; }
	public java.lang.String getListOf() { return listOf; }
  
	private List<OutputSnippet<O>> body1;
	public ListViewTemplate<O> addToBody1(OutputSnippet<O> snippet) {
		snippet.setContext(context);
		if (body1 == null) { body1 = new ArrayList<OutputSnippet<O>>(); }
		body1.add(snippet);
		return this;
	}
  
	@SuppressWarnings("unchecked")
	@Override
	public O output(O output) {
    
		template.setContext(context);
    
		{
    			HtmlHeader<O> title = context.getSnippet(HtmlHeader.class);
    			if ((listOf != null) && (!"".equals(listOf))) {
    				title.setText("List of "+listOf);
    			} else {
    				title.setText("List");
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
