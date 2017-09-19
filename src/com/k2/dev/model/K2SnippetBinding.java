package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2SnippetBindingENT;

@SuppressWarnings("rawtypes")
public interface K2SnippetBinding extends ServiceModel {

	@Override public K2SnippetBindingENT getEntity();
	@Override public K2SnippetBinding Null();

	public Long getID();

	public void setID(Long id);

	public K2Snippet getWidget();

	public void setWidget(K2Snippet widget);

	public K2SnippetParameter getBoundParameter();

	public void setBoundParameter(K2SnippetParameter boundParameter);
	
}