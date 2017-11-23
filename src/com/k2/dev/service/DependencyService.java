package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.entity.DependencyENT;

public interface DependencyService extends EntityService<DependencyENT, Long, Dependency>  {

	public Dependency newDependency();
	public Dependency fetchDependency(Long id);
	
	public ServiceList<Dependency> listForEntityAndMethod(K2Entity k2Enity, K2Method k2Method);

}
