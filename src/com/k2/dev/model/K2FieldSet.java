package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ComponentENT.Types;
import com.k2.dev.model.entity.K2FieldSetENT;

@SuppressWarnings({"rawtypes"})
public interface K2FieldSet  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public K2FieldSetENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public K2FieldSet Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// FieldOrder field
	public String getName();
	public void setName(String name);

	// FieldInSet field
	public Component getComponent();
	public void setComponent(Component component	);
	
	// Lists ---------
	// ServiceComponents list
	public ServiceList<Component> getServiceComponents();
	
	// Members list
	public ServiceList<FieldSetMember> getMembers();

}