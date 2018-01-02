package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2List;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2LinkedFieldService;
import com.k2.dev.service.K2ListService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2LinkedFieldBO extends K2FieldBO implements ServiceModel, K2Field, K2LinkedField {
	
	@Autowired(required=true)
	protected K2LinkedFieldService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Autowired(required=true)
	protected K2ListService k2ListService;

	@Override
	public EntityService  getService() { return service; }
	
	public static K2LinkedField NULL = new K2LinkedFieldBO();
	public K2LinkedFieldBO() { super(null); }
	public K2LinkedFieldBO(PersistenceState state) { super(state); }
	public K2LinkedFieldBO(K2LinkedFieldENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.LINKED_FIELD; }

	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2LinkedField Null() { return NULL; }
	
	@Override
	public K2LinkedFieldENT getEntity() { return (K2LinkedFieldENT)entity; }
	
	@Override
	public Boolean getAllowInsert() { if (isNull()) { return null; } return getEntity().getAllowInsert(); }
	@Override
	public void setAllowInsert(Boolean allowInsert) { if (isNull()) { return; } getEntity().setAllowInsert(allowInsert); changed(); }
	
	@Override
	public Boolean getAllowNavigate() { if (isNull()) { return null; } return getEntity().getAllowNavigate(); }
	@Override
	public void setAllowNavigate(Boolean allowNavigate) { if (isNull()) { return; } getEntity().setAllowNavigate(allowNavigate); changed(); }
	
	@Override
	public K2Entity getLinkedEntity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getLinkedEntity()), K2EntityBO.NULL); }
	@Override
	public void setLinkedEntity(K2Entity linkedEntity) { if (isNull()) { return; } getEntity().setLinkedEntity(linkedEntity.getEntity()); changed(); }

	@Override
	public K2List getValuesList() { if (isNull()) { return K2ListBO.NULL; } return Nvl.nvl(k2ListService.getBO(getEntity().getValuesList()), K2ListBO.NULL); }
	@Override
	public void setValuesList(K2List valuesList) { if (isNull()) { return; } getEntity().setValuesList(valuesList.getEntity()); changed(); }

	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2LinkedFieldENT(); }
		if (K2LinkedField.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2LinkedField)source);
		} else {
			super.clone(source);
		}
	}

	@Override
	public String getDataType() { return getLinkedEntity().getAlias(); };

	@Override
	public String getCanonicalDataType() { return getLinkedEntity().getModelPackageName()+"."+getLinkedEntity().getAlias(); }

	@Override
	public String getValuesListName() { return getValuesList().getName(); };



}
