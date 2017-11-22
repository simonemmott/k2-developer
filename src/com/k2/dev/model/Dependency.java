package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.DependencyENT;

@SuppressWarnings("rawtypes")
public interface Dependency  extends ServiceModel, ID {
	
	@Override
	public DependencyENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public Dependency Null();

	public Long getId();
	public void setId(Long id);

    public K2Entity getK2Entity();
    public void setK2Entity(K2Entity k2Entity);

    public K2Method getK2Method();
    public void setK2Method(K2Method k2Method);

    public String getDependsOnPackageName();
	public void setDependsOnPackageName(String dependesOnPackageName);

	public String getDependsOnComponentName();
	public void setDependsOnComponentName(String dependsOnComponentName);

	public ServiceList<K2Entity> getEntities();

	public ServiceList<K2Method> getEntityMethods();

}