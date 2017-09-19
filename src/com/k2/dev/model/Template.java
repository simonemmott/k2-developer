package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.entity.TemplateENT;

@SuppressWarnings("rawtypes")
public interface Template extends ServiceModel, K2Snippet {
	
	@Override public TemplateENT getEntity();
	@Override public Template Null();

	public K2Snippet getPopulatesSnippet();

	public void setPopulatesSnippet(K2Snippet snippet);

	public ServiceList<TemplateBinding> getTemplateBindings();

	public ServiceList<TemplateContent> getContents();

}