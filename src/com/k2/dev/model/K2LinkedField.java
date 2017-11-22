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
	
	public Boolean getAllowInsert();
	public void setAllowInsert(Boolean allowInsert);
	
	public Boolean getAllowNavigate();
	public void setAllowNavigate(Boolean allow);
	
	public K2Entity getLinkedEntity();
	public void setLinkedEntity(K2Entity linkedEntity);


}