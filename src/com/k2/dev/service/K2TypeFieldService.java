package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.K2TypeField;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2TypeFieldENT;

public interface K2TypeFieldService extends EntityService<K2TypeFieldENT, Long, K2TypeField> {

	public K2TypeField newK2TypeField();
	public K2TypeField fetchK2TypeField(Long id);

}
