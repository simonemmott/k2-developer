package com.k2.common.snippets.html.impl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.k2.common.entityOutputFormatter.outputSnippet.AbstractOutputSnippet;
import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.Snippet;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.SnippetContainer;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.SnippetParameter;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.SnippetParameterMethod;
import com.k2.common.snippets.html.K2Panel;

@Component("PANEL")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Snippet(name="PANEL", description="An empty bordered container with a title bar")
public class K2PanelImpl<O> extends AbstractOutputSnippet<O> implements OutputSnippet<O>, K2Panel<O> {

	public class Model{
		@Expose
		String title;
		@Expose
		Integer width;
		@Expose
		Integer height;
	}
	
	public class Containers {
		@Expose
		List<OutputSnippet<O>> title;
		@Expose
		List<OutputSnippet<O>> body;
	}

	@Expose
	private Model model = new Model();
	@Expose
	private Containers containers = new Containers();


	public K2PanelImpl() {
		type = "PANEL";
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#setTitle(java.lang.String)
	 */
	@Override
	@SnippetParameter(name="Title", type=SnippetParameterMethod.SET, dataType="java.lang.String", description="Sets the title of the panel.  This is ignored if widgets are added to the title section.")
	public K2Panel<O> setTitle(String v) { model.title = v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#getTitle()
	 */
	@Override
	@SnippetParameter(name="Title", type=SnippetParameterMethod.GET)
	public String getTitle() { return model.title; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#setWidth(java.lang.Integer)
	 */
	@Override
	@SnippetParameter(name="Width", type=SnippetParameterMethod.SET, dataType="java.lang.Long", description="Sets the width of the panel")
	public K2Panel<O> setWidth(Integer v) { model.width = v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#getWidth()
	 */
	@Override
	@SnippetParameter(name="Width", type=SnippetParameterMethod.GET)
	public Integer getWidth() { return model.width; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#setHeight(java.lang.Integer)
	 */
	@Override
	@SnippetParameter(name="Height", type=SnippetParameterMethod.SET, dataType="java.lang.Long", description="Sets the height of the panel")
	public K2Panel<O> setHeight(Integer v) { model.height = v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#getHeight()
	 */
	@Override
	@SnippetParameter(name="Height", type=SnippetParameterMethod.GET)
	public Integer getHeight() { return model.height; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#addToTitle(com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet)
	 */
	@Override
	@SnippetContainer(name="Title", description="The title container of the panel")
	public K2Panel<O> addToTitle(OutputSnippet<O> snippet) {
		snippet.setContext(context);
		if (containers.title == null) { containers.title = new ArrayList<OutputSnippet<O>>(); }
		containers.title.add(snippet);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#addToBody(com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet)
	 */
	@Override
	@SnippetContainer(name="Body", description="The body container of the panel", permitted={"PANEL", "TEXTFIELD"})
	public K2Panel<O> addToBody(OutputSnippet<O> snippet) {
		snippet.setContext(context);
		if (containers.body == null) { containers.body = new ArrayList<OutputSnippet<O>>(); }
		containers.body.add(snippet);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2Panel#output(O)
	 */
	@Override
	public O output(O output) {
		if (PrintWriter.class.isAssignableFrom(output.getClass())) {
			PrintWriter pw = (PrintWriter)output;
			
			String div = "<div id=\""+context.getNextID()+"\" class=\"k2Panel\"";
			if (model.width != null) { div = div+" width=\""+model.width+"\""; }
			if (model.height != null) { div = div+" height=\""+model.height+"\""; }
			div = div+">";
			addToPrintWriter(pw, div);
			
			context.indent();
			addToPrintWriter(pw, "<div class=\"title\">");
			if (containers.title != null) {
				context.indent();
				for (OutputSnippet<O> s : containers.title) { s.output(output); }
				context.outdent();
			} else {
				if (model.title != null) { context.indent(); addToPrintWriter(pw, "<h1>"+model.title+"</h1>"); context.outdent(); }
			}
			addToPrintWriter(pw, "</div>");
			addToPrintWriter(pw, "<div class=\"body\">");
			if (containers.body != null) {
				context.indent();
				for (OutputSnippet<O> s : containers.body) { s.output(output); }
				context.outdent();
			}
			addToPrintWriter(pw, "</div>");
			context.outdent();

			pw.flush();
		}
		return output;
	}

}






