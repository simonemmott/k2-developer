package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2PermittedContentENT;

@SuppressWarnings("rawtypes")
public interface K2PermittedContent extends ServiceModel {
	
	@Override
	public K2PermittedContentENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2PermittedContent Null();

	public Long getID();

	public void setID(Long id);

	public String getName();

	public void setName(String name);

	public K2SnippetContainer getContainer();

	public void setContainer(K2SnippetContainer container);

}