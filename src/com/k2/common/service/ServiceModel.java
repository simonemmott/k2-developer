package com.k2.common.service;

import com.k2.common.writeEvents.ValidationException;

public interface ServiceModel<E, B extends ServiceModel<E,B>> {
	
	public boolean isNull();
	public B Null();
	public boolean isNew();
	public boolean isPersisted();
	public boolean isDeleted();
	public E getEntity();
	
	public void write() throws ValidationException;
	
	public void delete() throws ValidationException;

}
