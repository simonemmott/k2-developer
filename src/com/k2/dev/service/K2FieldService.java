package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.K2FieldENT;

public interface K2FieldService extends EntityService<K2FieldENT, Long, K2Field> {

	public K2Field newK2Field();
	public K2Field fetchK2Field(Long id);
	public ServiceList<K2Field> listForEntity(K2Entity k2Entity);
	public ServiceList<K2Field> listForComponent(Component component);

}
