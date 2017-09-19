package com.k2.common.service;

import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.spring.ApplicationError;
import com.k2.common.writeEvents.ValidationException;

@SuppressWarnings("rawtypes")
@Configurable
public abstract class GenericServiceModel implements ServiceModel {
	
	public enum PersistenceState{
		NEW, PERSISTED, DELETED
	}
	
	private PersistenceState state;
	private boolean dirty;

	public GenericServiceModel(PersistenceState state) { 
		this.state = state;
		if (state != null) {
			switch(state) {
			case DELETED:
				throw new ApplicationError("Cannot create a new business object in a DELETED state!"); 
			case NEW:
				dirty = true;
				break;
			case PERSISTED:
				dirty = false;
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public abstract ServiceModel Null();
	@Override
	public boolean isNull() { return (this == Null()); }
	@Override
	public boolean isNew() { return (state.equals(PersistenceState.NEW)); }
	@Override
	public boolean isPersisted() { return (state.equals(PersistenceState.PERSISTED)); }
	@Override
	public boolean isDeleted() { return (state.equals(PersistenceState.DELETED)); }
	protected void changed() { dirty=true; }

	protected void onInsertPreValidate() {}
	protected void onInsertPostValidate() {}
	protected void onUpdatePreValidate() {}
	protected void onUpdatePostValidate() {}
	protected void onDeletePreValidate() {}
	protected void onDeletePostValidate() {}
	protected void validate() throws ValidationException {}
	
	public abstract EntityService getService();
	
	protected void writeInsert() throws ValidationException {
		onInsertPreValidate();
		validate();
		onInsertPostValidate();
		dirty = false;
		state = PersistenceState.PERSISTED;		
	}
	
	protected void writeUpdate() throws ValidationException {
		onUpdatePreValidate();
		validate();
		onUpdatePostValidate();
		dirty = false;
	}
	
	protected void writeDelete() throws ValidationException {
		onDeletePreValidate();
		validate();
		onDeletePostValidate();
		dirty = false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write() throws ValidationException { 
		switch(state) {
		case DELETED:
			return;
		case NEW:
			if (dirty) {
				writeInsert();
				getService().insert(this);
				return;		
			}
		case PERSISTED:
			if (dirty) {
				writeUpdate();
				getService().update(this);
				return;		
			}
		default:
			break;
		
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void delete() throws ValidationException { 
		writeDelete();
		getService().delete(this); 
	}


	

}
