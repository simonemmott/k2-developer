package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2FieldSetENT;

public interface K2FieldSetService extends EntityService<K2FieldSetENT, Long, K2FieldSet>{
	
	public K2FieldSet newFieldSet();
	public K2FieldSet fetchFieldSet(Long id);
	public ServiceList<K2FieldSet> listForComponent(Component component);

}
