package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2FieldBO extends GenericServiceModel implements ServiceModel, K2Field {
	
	@Autowired(required=true)
	protected K2FieldService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;

	@Override
	public EntityService  getService() { return service; }
	
	public static K2Field NULL = new K2FieldBO();
	public K2FieldBO() { super(null); }
	public K2FieldBO(PersistenceState state) { super(state); }
	public K2FieldBO(K2FieldENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.K2FIELD; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Field Null() { return NULL; }
	
	private K2FieldENT entity;
	@Override
	public K2FieldENT getEntity() { return entity; }

	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } entity.setName(name); changed(); }

	@Override
	public K2Entity getK2Entity() { if (isNull()) { return null; } return k2EntityService.getBO(entity.getK2Entity()); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } entity.setK2Entity(k2Entity.getEntity()); changed(); }

	@Override
	public Integer getColumnLength() { if (isNull()) { return null; } return entity.getColumnLength(); }
	@Override
	public void setColumnLength(Integer length) { if (isNull()) { return; } entity.setColumnLength(length); changed(); }
	
	@Override
	public String getDataType() { if (isNull()) { return null; } return entity.getDataType(); }
	@Override
	public void setDataType(String dataType) { if (isNull()) { return; } entity.setDataType(dataType); changed(); }
	
	@Override
	public String getColumnName() { if (isNull()) { return null; } return entity.getColumnName(); }
	@Override
	public void setColumnName(String columnName) { if (isNull()) { return; } entity.setColumnName(columnName); changed(); }
	
	@Override
	public Boolean getNullable() { if (isNull()) { return null; } return entity.getNullable(); }
	@Override
	public void setNullable(Boolean nullable) { if (isNull()) { return; } entity.setNullable(nullable); changed(); }
	
	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listAll(); }



}
