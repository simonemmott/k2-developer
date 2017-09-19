package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.entity.K2EntityENT;

public interface K2EntityService extends EntityService<K2EntityENT, Long, K2Entity>  {

	public K2Entity newK2Entity();
	public K2Entity fetchK2Entity(Long id);
	public K2Entity fetchForName(String name);

}
