package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.DependencyENT;

@SuppressWarnings("rawtypes")
public interface Dependency  extends ServiceModel {
	
	@Override
	public DependencyENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public Dependency Null();

	public Long getID();

	public void setID(Long id);

	public String getDependsOnPackageName();

	public void setDependsOnPackageName(String dependesOnPackageName);

	public String getDependsOnComponentName();

	public void setDependsOnComponentName(String dependsOnComponentName);

}