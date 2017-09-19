package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.TemplateBindingENT;

@SuppressWarnings("rawtypes")
public interface TemplateBinding extends ServiceModel, K2SnippetBinding {
	
	@Override public TemplateBindingENT getEntity();
	@Override public TemplateBinding Null();

	public TemplateBindingENT.Types.BindingSource getBindingSource();

	public void setBindingSource(TemplateBindingENT.Types.BindingSource bindingSource);

	public Template getBindingTemplate();

	public void setBindingTemplate(Template template);

	public K2SnippetParameter getBindingParameter();

	public void setBindingParameter(K2SnippetParameter bindingParameter);

	public Literal getLiteral();

	public void setLiteral(Literal literal);

}