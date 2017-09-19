package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2SnippetParameterENT;

@SuppressWarnings("rawtypes")
public interface K2SnippetParameter extends ServiceModel {
	
	@Override public K2SnippetParameterENT getEntity();
	@Override public K2SnippetParameter Null();

	public Long getID();

	public void setID(Long id);

	public String getName();

	public void setName(String name);

	public K2Snippet getWidget();

	public void setWidget(K2Snippet widget);

	public String getSetterMethod();

	public void setSetterMethod(String setterMethod);

	public String getGetterMethod();

	public void setGetterMethod(String getterMethod);

	public String getDataType();

	public void setDataType(String dataType);

	public String getDescription();

	public void setDescription(String description);

}