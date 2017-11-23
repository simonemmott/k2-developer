package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ComponentENT.Types;

@SuppressWarnings({"rawtypes"})
public interface Component  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public ComponentENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public Component Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// Name field
	public String getName();
	public void setName(String name);

	// PackageName field
	public String getPackageName();
	public void setPackageName(String packageName);
	
	// K2Service field
	public K2Service getK2Service();
	public void setK2Service(K2Service k2Service);

	// ComponentType discriminator field
	public Types.ComponentType getComponentType();
	
}