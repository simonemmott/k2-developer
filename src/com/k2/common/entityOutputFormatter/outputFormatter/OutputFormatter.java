package com.k2.common.entityOutputFormatter.outputFormatter;

import com.k2.common.entityOutputFormatter.EntityInteractionHandlerFactory;
import com.k2.common.entityOutputFormatter.outputTemplate.OutputTemplate;

public interface OutputFormatter<E, O> extends OutputTemplate<O>{
	
	public void setEntity(E entity);
	public E getEntity();
	
	public EntityInteractionHandlerFactory<E> getEntityInteractionHandlerFactory();
	void setEntityInteractionHandlerFactory(EntityInteractionHandlerFactory<E> interactionHandlerFactory);

}
