package com.k2.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ServiceContext {
	
	private Map<Object, ServiceModel<?,?>> bos = new HashMap<Object, ServiceModel<?,?>>();
	
	public ServiceModel<?,?> getBO(Object entity) { return bos.get(entity); }
	public ServiceModel<?,?> putBO(ServiceModel<?,?> bo) { bos.put(bo.getEntity(), bo); return bo; }

}
