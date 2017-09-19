package com.k2.common.service;

public interface ServiceList<B> {
	
	public boolean hasNext();
	public B next();
	public int count();
	public B newBO();
	public void reset();

}
