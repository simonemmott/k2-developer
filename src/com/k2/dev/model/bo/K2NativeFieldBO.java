package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2NativeFieldService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2NativeFieldBO extends K2FieldBO implements ServiceModel, K2Field, K2NativeField {
	
	@Autowired(required=true)
	protected K2NativeFieldService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;

	@Override
	public EntityService  getService() { return service; }
	
	public static K2NativeField NULL = new K2NativeFieldBO();
	public K2NativeFieldBO() { super(null); }
	public K2NativeFieldBO(PersistenceState state) { super(state); }
	public K2NativeFieldBO(K2NativeFieldENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2NATIVEFIELD; }

	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2NativeField Null() { return NULL; }
	
	@Override
	public K2NativeFieldENT getEntity() { return (K2NativeFieldENT)entity; }

	@Override
	public Types.NativeDateType getNativeType() { if (isNull()) { return null; } return getEntity().getNativeType(); }
	@Override
	public void setNativeType(Types.NativeDateType nativeType) { if (isNull()) { return; } getEntity().setNativeType(nativeType); changed(); }

	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2NativeFieldENT(); }
		if (K2NativeField.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2NativeField)source);
		} else {
			super.clone(source);
		}
	}
	
	@Override
	public String getDataType() {
 		switch (getNativeType()) {
		case BOOLEAN:
			return "Boolean";
		case DATE:
			return "Date";
		case DOUBLE:
			return "Double";
		case FLOAT:
			return "Float";
		case INTEGER:
			return "Integer";
		case LONG:
			return "Long";
		case STRING:
			return "String";
 		}
 		return "";
	}

	@Override
	public String getCononicalDataType() {
 		switch (getNativeType()) {
		case BOOLEAN:
			return "java.lang.Boolean";
		case DATE:
			return "java.util.Date";
		case DOUBLE:
			return "java.lang.Double";
		case FLOAT:
			return "java.lang.Float";
		case INTEGER:
			return "java.lang.Integer";
		case LONG:
			return "java.lang.Long";
		case STRING:
			return "java.util.String";
 		}
 		return "";
	}



}
