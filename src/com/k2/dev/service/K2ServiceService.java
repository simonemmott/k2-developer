package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.entity.K2ServiceENT;

public interface K2ServiceService extends EntityService<K2ServiceENT, Long, K2Service>{
	
	public K2Service newK2Service();
	public K2Service fetchK2Service(Long id);

}
