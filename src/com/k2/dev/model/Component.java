package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ComponentENT;

@SuppressWarnings("rawtypes")
public interface Component  extends ServiceModel {
	
	@Override
	public ComponentENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public Component Null();

	public Long getID();

	public void setID(Long id);

	public String getName();

	public void setName(String name);

	public String getPackageName();

	public void setPackageName(String packageName);

}