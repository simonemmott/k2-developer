package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.entity.K2SnippetContainerENT;

@SuppressWarnings("rawtypes")
public interface K2SnippetContainer extends ServiceModel {
	
	@Override public K2SnippetContainerENT getEntity();
	@Override public K2SnippetContainer Null();

	public Long getID();

	public void setID(Long id);

	public K2Snippet getWidget();

	public void setWidget(K2Snippet widget);

	public String getAlias();

	public void setAlias(String alias);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public ServiceList<K2PermittedContent> getPermittedContents();

}