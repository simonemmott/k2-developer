package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.entity.K2SnippetENT;

@SuppressWarnings("rawtypes")
public interface K2Snippet extends ServiceModel, ID {
	
	@Override public K2SnippetENT getEntity();
	@Override public K2Snippet Null();

	public Long getId();

	public void setId(Long id);

	public String getName();

	public void setName(String name);

	public String getPackageName();

	public void setPackageName(String packageName);

	public String getClassName();

	public void setClassName(String className);

	public String getDescription();

	public void setDescription(String description);

	public ServiceList<K2SnippetContainer> getContainers();

	public K2SnippetContainer getContainerForAlias(String alias);

	public ServiceList<K2SnippetParameter> getParameters();
	
	public K2SnippetParameter getParameterForName(String name);

}