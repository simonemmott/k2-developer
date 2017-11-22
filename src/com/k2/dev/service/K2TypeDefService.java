package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.entity.K2TypeDefENT;

public interface K2TypeDefService extends EntityService<K2TypeDefENT, Long, K2TypeDef>{
	
	public K2TypeDef newK2TypeDef();
	public K2TypeDef fetchK2TypeDef(Long id);
	
	public ServiceList<K2TypeDef> listForEntity(K2Entity k2Entity);

}
