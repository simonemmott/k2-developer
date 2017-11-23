package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.K2EntityENT;

public interface K2EntityService extends EntityService<K2EntityENT, Long, K2Entity>  {

	public K2Entity newK2Entity();
	public K2Entity fetchK2Entity(Long id);
	public K2Entity fetchForName(String name);
	public ServiceList<K2Entity> listForService(K2Service k2Service);
	public ServiceList<K2Entity> listSubEntitiesForEntity(K2Entity k2Entity);
	public ServiceList<K2Entity> listRootEntitiesForService(K2Service k2Service);

}
