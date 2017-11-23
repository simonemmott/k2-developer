package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2MethodENT;
import com.k2.dev.model.entity.K2MethodENT.Types;

@SuppressWarnings("rawtypes")
public interface K2Method  extends ServiceModel, ID {
	
	@Override
	public K2MethodENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2Method Null();

	public Long getId();
	public void setId(Long id);

    public String getAlias();
	public void setAlias(String alias);

	public String getName();
	public void setName(String name);

	public Types.returnTypes getReturnsType();
	public void setReturnsType(Types.returnTypes returnsType);

    public K2Entity getReturnsEntity();
    public void setReturnsEntity(K2Entity returnsEntity);

	public Types.nativeReturnType getNativeReturnType();
	public void setNativeReturnType(Types.nativeReturnType nativeReturnType);

	public String getMethodBody();
	public void setMethodBody(String methodBody);

	public String getDependencyFields();
	public void setDependencyFields(String dependencyFields);

	public K2Entity getK2Entity();
	public void setK2Entity(K2Entity k2Entity);

	public ServiceList<K2Entity> getEntities();

	public ServiceList<Dependency> getMethodDependencies();


}