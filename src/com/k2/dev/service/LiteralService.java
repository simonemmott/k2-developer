package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.LiteralENT;

public interface LiteralService extends EntityService<LiteralENT, Long, Literal> {

	public Literal newLiteral();
	public Literal fetchLiteral(Long id);

}
