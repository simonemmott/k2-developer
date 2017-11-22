package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ComponentENT.Types.ComponentType;
import com.k2.dev.model.entity.K2TypeDefENT;
import com.k2.dev.model.entity.K2TypeValueENT;

@SuppressWarnings({"rawtypes"})
public interface K2TypeValue  extends ServiceModel, ID {
	
	@Override
	public K2TypeValueENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2TypeValue Null();

	public Long getId();

	public void setId(Long id);

	public String getAlias();
	public void setAlias(String alias);

	public String getName();
	public void setName(String name);
	
	public String getDescription();
	public void setDescription(String description);
	
	public K2TypeDef getTypeDefinition();
	public void setTypeDefinition(K2TypeDef typeDefinition);

	public ServiceList<K2TypeDef> getTypeDefinitions();


}