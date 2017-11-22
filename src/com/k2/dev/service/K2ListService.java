package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2List;
import com.k2.dev.model.entity.K2ListENT;

public interface K2ListService extends EntityService<K2ListENT, Long, K2List>{
	
	public K2List newK2List();
	public K2List fetchK2List(Long id);
	
	public ServiceList<K2List> listForEnity(K2Entity k2Entity);

}
