package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ComponentENT.Types.ComponentType;
import com.k2.dev.model.entity.K2TypeDefENT;

@SuppressWarnings({"rawtypes"})
public interface K2TypeDef  extends ServiceModel, ID {
	
	@Override
	public K2TypeDefENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2TypeDef Null();

	public Long getId();

	public void setId(Long id);

	public String getAlias();
	public void setAlias(String alias);

	public String getName();
	public void setName(String name);
	
	public K2Entity getK2Entity();
	public void setK2Entity(K2Entity k2Entity);

	public ServiceList<K2Entity> getEntities();
	
	public ServiceList<K2TypeValue> getTypeValues();

}