package com.k2.common.writeEvents;

import com.k2.common.util.EntityUtil;

public class WriteEventEntity {
	
	public Object entity;
	public EntityState state;
	
	public WriteEventEntity(Object entity, EntityState state) {
		this.entity = entity;
		this.state = state;
	}
	
	public void setState(EntityState state) {
		this.state = state;
	}

	/*
	@Override
	public boolean equals(Object other) {
		if (other == this) { return true; }
		if (other == null) { return false; }
		if (other instanceof WriteEventEntity) {
			WriteEventEntity that = (WriteEventEntity) other;
			return that.entity.equals(this.entity);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return entity.hashCode();
	}
	*/
	@Override
	public String toString() {
		return EntityUtil.getEntityIdentity(entity)+" State: "+state;
	}
	

}
