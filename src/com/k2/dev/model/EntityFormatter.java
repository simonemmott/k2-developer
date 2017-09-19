package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.EntityFormatterENT;

@SuppressWarnings("rawtypes")
public interface EntityFormatter  extends ServiceModel {
	
	@Override
	public EntityFormatterENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public EntityFormatter Null();

	public K2Entity getEntityToFormat();

	public void setEntityToFormat(K2Entity aEntity);

}