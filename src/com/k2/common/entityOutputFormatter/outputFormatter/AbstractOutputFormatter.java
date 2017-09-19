package com.k2.common.entityOutputFormatter.outputFormatter;

import com.k2.common.entityOutputFormatter.outputTemplate.AbstractOutputTemplate;

public abstract class AbstractOutputFormatter<E, O> extends AbstractOutputTemplate<O> implements OutputFormatter<E, O> {

	protected E entity;
	@Override
	public void setEntity(E entity) { this.entity = entity; }
	@Override
	public E getEntity() { return entity; }


}
