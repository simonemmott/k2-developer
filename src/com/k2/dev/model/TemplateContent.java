package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.entity.TemplateContentENT;

@SuppressWarnings("rawtypes")
public interface TemplateContent extends ServiceModel {
	
	@Override public TemplateContentENT getEntity();
	@Override public TemplateContent Null();

	public Long getID();

	public void setID(Long id);

	public String getAlias();

	public void setAlias(String alias);

	public Template getTemplate();

	public void setTemplate(Template template);

	public K2Snippet getContainedSnippet();

	public void setContainedSnippet(K2Snippet snippet);

	public K2SnippetContainer getInContainer();

	public void setInContainer(K2SnippetContainer inContainer);

	public TemplateContentENT.Types.Type getType();

	public void setType(TemplateContentENT.Types.Type type);

	public ServiceList<TemplateContent> getContents();

	public TemplateContent getParentContent();

	public void setParentContent(TemplateContent parentContent);

	public ServiceList<TemplateBinding> getBindings();

	public K2SnippetContainer getFromContainer();
	
	public void setFromContainer(K2SnippetContainer container);

}