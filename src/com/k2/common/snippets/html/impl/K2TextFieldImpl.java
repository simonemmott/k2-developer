package com.k2.common.snippets.html.impl;

import java.io.PrintWriter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.k2.common.entityOutputFormatter.outputSnippet.AbstractOutputSnippet;
import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.Snippet;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.SnippetParameter;
import com.k2.common.entityOutputFormatter.outputSnippet.annotation.SnippetParameterMethod;
import com.k2.common.snippets.html.K2TextField;

@Component("TEXTFIELD")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Snippet(name="TEXTFIELD", description="A text field bound to an underlying object field.")
public class K2TextFieldImpl<O> extends AbstractOutputSnippet<O> implements OutputSnippet<O>, K2TextField<O> {

	public class Model {
		@Expose
		String label;
		@Expose
		Integer length;
		@Expose
		Boolean required;
	}
	
	@Expose
	private Model model = new Model();
	
	public K2TextFieldImpl() {
		type = "TEXTFIELD";
	}
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#setLabel(java.lang.String)
	 */
	@Override
	@SnippetParameter(name="Label", type=SnippetParameterMethod.SET, dataType="java.lang.String", description="Sets the label of the text field")
	public K2TextField<O> setLabel(String v) { model.label=v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#getLabel()
	 */
	@Override
	@SnippetParameter(name="Label", type=SnippetParameterMethod.GET)
	public String getLabel() { return model.label; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#setLength(java.lang.Integer)
	 */
	@Override
	@SnippetParameter(name="Length", type=SnippetParameterMethod.SET, dataType="java.lang.Long", description="The number of charaters that the field is to display")
	public K2TextField<O> setLength(Integer v) { model.length = v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#getLength()
	 */
	@Override
	@SnippetParameter(name="Length", type=SnippetParameterMethod.GET)
	public Integer getLength() { return model.length; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#setRequired(java.lang.Boolean)
	 */
	@Override
	@SnippetParameter(name="Required", type=SnippetParameterMethod.SET, dataType="java.lang.Boolean", description="Whether this field is required before the context of the presentation changes")
	public K2TextField<O> setRequired(Boolean v) { model.required = v; return this; }
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#getRequired()
	 */
	@Override
	@SnippetParameter(name="Required", type=SnippetParameterMethod.GET)
	public Boolean getRequired() { return model.required; }
	
	/* (non-Javadoc)
	 * @see com.k2.common.snippets.html.impl.K2TextField#output(O)
	 */
	@Override
	public O output(O output) {
		
		if (PrintWriter.class.isAssignableFrom(output.getClass())) {
			
			PrintWriter pw = (PrintWriter)output;
			
			addToPrintWriter(pw, "<div id=\""+context.getNextID()+"\" class=\"k2TextField\">");
			context.indent();
			if (!"".equals(model.label) && model.label != null) { addToPrintWriter(pw, "<label>"+model.label+"</label>"); }
			String input = "<input type=\"text\"";
			if (model.length != null) { input = input+" size=\""+model.length+"\""; }
			if ((model.required != null) && model.required) { input = input+" required"; }
			input = input+"/>";
			addToPrintWriter(pw, input);
			context.outdent();
			addToPrintWriter(pw, "</div>");
		
			pw.flush();
		}

		return output;

	}
	
	
}