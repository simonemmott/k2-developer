package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.dev.model.Component;
import com.k2.dev.model.entity.ComponentENT;

public interface ComponentService extends EntityService<ComponentENT, Long, Component>{
	
	public Component newComponent();
	public Component fetchComponent(Long id);

}
