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
import com.k2.common.snippets.html.K2HTMLPage;

@Component("PAGE")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Snippet(name="PAGE", description="An empty bordered container with a title bar")
public class K2HTMLPageImpl<O> extends AbstractOutputSnippet<O> implements OutputSnippet<O>, K2HTMLPage<O> {

	List<String> stylesheets = null;
	List<String> javascripts = null;
	List<String> keywords = null;
	
	public class Model {
		@Expose()
		public String title;
	}

	public class Containers {

		@Expose()
		public List<OutputSnippet<O>> body;
	}

	@Expose()
	private Model model = new Model();
	@Expose()
	public Containers containers = new Containers();

	public K2HTMLPageImpl() {
		type = "PAGE";
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#addStyleSheets(java.lang.String)
	 */
	@Override
	public K2HTMLPage<O> addStyleSheets(String ... styles) {
		if (stylesheets == null) { stylesheets = new ArrayList<String>(); }
		for (String style : styles) {
			stylesheets.add(style);
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#addScripts(java.lang.String)
	 */
	@Override
	public K2HTMLPage<O> addScripts(String ... scripts) {
		if (javascripts == null) { javascripts = new ArrayList<String>(); }
		for (String script : scripts) {
			javascripts.add(script);
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#addKeywords(java.lang.String)
	 */
	@Override
	public K2HTMLPage<O> addKeywords(String ... words) {
		if (keywords == null) { keywords = new ArrayList<String>(); }
		for (String word : words) {
			keywords.add(word);
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#setTitle(java.lang.String)
	 */
	@Override
	@SnippetParameter(name="Title", type=SnippetParameterMethod.SET, dataType="java.lang.String", description="Sets the title of the html page.")
	public K2HTMLPage<O> setTitle(String v) { model.title = v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#getTitle()
	 */
	@Override
	@SnippetParameter(name="Title", type=SnippetParameterMethod.GET)
	public String getTitle() { return model.title; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#addToBody(com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet)
	 */
	@Override
	@SnippetContainer(name="Body", description="The body container of the html page.")
	public K2HTMLPage<O> addToBody(OutputSnippet<O> snippet) {
		snippet.setContext(context);
		if (containers.body == null) { containers.body = new ArrayList<OutputSnippet<O>>(); }
		containers.body.add(snippet);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2HTMLPage#output(O)
	 */
	@Override
	public O output(O output) {
		if (PrintWriter.class.isAssignableFrom(output.getClass())) {
			PrintWriter pw = (PrintWriter)output;
			
			
			addToPrintWriter(pw, "<html>");
			context.indent();
			addToPrintWriter(pw, "<head>");
			if (model.title != null) { context.indent(); addToPrintWriter(pw, "<title>"+model.title+"</title>"); context.outdent(); }
			context.indent();
			for (String sheet : stylesheets) { addToPrintWriter(pw, "<link rel=\"stylesheet\" media=\"all\" href=\""+sheet+"\" type=\"text/css\"/>"); }
			for (String script : javascripts) { addToPrintWriter(pw, "<script src=\""+script+"\" type=\"text/javascript\"></script>"); }
			if (keywords != null) {
				String words = new String();
				for (String word : keywords) {
					if (words.isEmpty()) { words = word; } else { words = words+", "+word; }
				}

				addToPrintWriter(pw, "<meta name=\"keywords\" content=\""+words+"\"/>");
			}
			context.outdent();
			addToPrintWriter(pw, "</head>");
			addToPrintWriter(pw, "<body class=\"body\">");
			if (containers.body != null) {
				context.indent();
				for (OutputSnippet<O> s : containers.body) { s.output(output); }
				context.outdent();
			}
			addToPrintWriter(pw, "</body>");
			context.outdent();
			addToPrintWriter(pw, "</html>");
			
			pw.flush();
		}
		return output;
	}

}








