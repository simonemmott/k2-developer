package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.entity.EntityFormatterENT;

public interface EntityFormatterService extends EntityService<EntityFormatterENT, Long, EntityFormatter> {

	public EntityFormatter newEntityFormatter();
	public EntityFormatter fetchEntityFormatter(Long id);

}
