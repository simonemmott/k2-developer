package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.entity.K2TypeValueENT;

public interface K2TypeValueService extends EntityService<K2TypeValueENT, Long, K2TypeValue>{
	
	public K2TypeValue newK2TypeValue();
	public K2TypeValue fetchK2TypeValue(Long id);
	
	public ServiceList<K2TypeValue> listForType(K2TypeDef typeDefinition);

}
