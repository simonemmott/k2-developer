package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.EntityBindingENT;

@SuppressWarnings("rawtypes")
public interface EntityBinding  extends ServiceModel, K2SnippetBinding {
	
	@Override
	public EntityBindingENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public EntityBinding Null();

	public K2Field getBindingField();

	public void setBindingField(K2Field bindingField);

}