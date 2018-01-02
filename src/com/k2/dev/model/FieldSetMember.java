package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ComponentENT.Types;
import com.k2.dev.model.entity.FieldSetMemberENT;
import com.k2.dev.model.entity.K2FieldSetENT;

@SuppressWarnings({"rawtypes"})
public interface FieldSetMember  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public FieldSetMemberENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public FieldSetMember Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// FieldSet field
	public K2FieldSet getFieldSet();
	public void setFieldSet(K2FieldSet fieldSet);

	// OrderInSet field
	public Integer getOrderInSet();
	public void setOrderInSet(Integer orderInSet);

	// Member field
	public K2Field getMember();
	public void setMember(K2Field member);
	
	// Lists ---------
	// ComponentFields list
	public ServiceList<K2Field> getComponentFields();
	
	// FieldSets list
	public ServiceList<K2FieldSet> getFieldSets();

}