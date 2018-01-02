package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2LinkedFieldENT;

@SuppressWarnings("rawtypes")
public interface K2LinkedField  extends ServiceModel, K2Field {
	
	@Override
	public K2LinkedFieldENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2LinkedField Null();
	
	// Fields -------
	// AllowInsert field
	public Boolean getAllowInsert();
	public void setAllowInsert(Boolean allowInsert);
	
	// AllowNavigate field
	public Boolean getAllowNavigate();
	public void setAllowNavigate(Boolean allow);
	
	// LinkedEntity field
	public K2Entity getLinkedEntity();
	public void setLinkedEntity(K2Entity linkedEntity);

	// ValuesList field
	public K2List getValuesList();
	public void setValuesList(K2List valuesList);

	// Expressions -----------
	// ValuesListName expression
	public String getValuesListName();


}