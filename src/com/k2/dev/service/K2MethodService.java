package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.entity.K2MethodENT;

public interface K2MethodService extends EntityService<K2MethodENT, Long, K2Method>  {

	public K2Method newK2Method();
	public K2Method fetchK2Method(Long id);
	
	public ServiceList<K2Method> listForEntity(K2Entity k2Entity);

}
