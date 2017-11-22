package com.k2.dev.model;

import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2TypeFieldENT;

@SuppressWarnings("rawtypes")
public interface K2TypeField  extends ServiceModel, K2Field {
	
	@Override
	public K2TypeFieldENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2TypeField Null();
	
	public K2TypeDef getTypeDefinition();
	public void setTypeDefinition(K2TypeDef typeDefintion);

	public ServiceList<K2TypeDef> getEntityTypes();

}