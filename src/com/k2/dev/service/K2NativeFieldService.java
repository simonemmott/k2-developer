package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;

public interface K2NativeFieldService extends EntityService<K2NativeFieldENT, Long, K2NativeField> {

	public K2NativeField newK2NativeField();
	public K2NativeField fetchK2NativeField(Long id);

}
