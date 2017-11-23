package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.entity.ComponentENT;

public interface ComponentService extends EntityService<ComponentENT, Long, Component>{
	
	public Component newComponent();
	public Component fetchComponent(Long id);
	public ServiceList<Component> listForService(K2Service k2Service);

}
