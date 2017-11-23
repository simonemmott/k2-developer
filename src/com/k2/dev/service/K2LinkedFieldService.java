package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;

public interface K2LinkedFieldService extends EntityService<K2LinkedFieldENT, Long, K2LinkedField> {

	public K2LinkedField newK2LinkedField();
	public K2LinkedField fetchK2LinkedField(Long id);

}
