package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.entity.EntityBindingENT;

public interface EntityBindingService extends EntityService<EntityBindingENT, Long, EntityBinding>  {

	public EntityBinding newEntityBinding();
	public EntityBinding fetchEntityBinding(Long id);

}
