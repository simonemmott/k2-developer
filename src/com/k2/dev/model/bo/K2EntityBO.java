package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2EntityBO extends ComponentBO implements ServiceModel, K2Entity, Component {
	
	@Autowired(required=true)
	protected K2EntityService service;
	@Autowired(required=true)
	protected K2FieldService k2FieldService;
	@Override
	public EntityService  getService() { return service; }
	
	public static K2Entity NULL = new K2EntityBO();
	public K2EntityBO() { super(null); }
	public K2EntityBO(K2EntityENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.K2ENTITY; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Entity Null() { return NULL; }
	
	private K2EntityENT entity;
	@Override
	public K2EntityENT getEntity() { return entity; }
	
	public String getIdentity() { return getName(); }

	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } entity.setName(name); changed(); }

	@Override
	public String getPackageName() { if (isNull()) { return null; } return entity.getPackageName(); }
	@Override
	public void setPackageName(String packageName) { if (isNull()) { return; } entity.setPackageName(packageName); changed(); }

	@Override
	public String getEntityName() { if (isNull()) { return null; } return entity.getEntityName(); }
	@Override
	public void setEntityName(String entityName) { if (isNull()) { return; } entity.setEntityName(entityName); changed(); }
	
	@Override
	public String getTableName() { if (isNull()) { return null; } return entity.getTableName(); }
	@Override
	public void setTableName(String tableName) { if (isNull()) { return; } entity.setTableName(tableName); changed(); }

	@Override
	public K2Entity getExtendsEntity() { if (isNull()) { return null; } return service.getBO(entity.getExtendsEntity()); }
	@Override
	public void setExtendsEntity(K2Entity extendsEntity) { if (isNull()) { return; } entity.setExtendsEntity(extendsEntity.getEntity()); changed(); }

	@Override
	public String getInheritanceJoinColumn() { if (isNull()) { return null; } return entity.getInheritanceJoinColumn(); }
	@Override
	public void setInheritanceJoinColumn(String joinColumn) { if (isNull()) { return; } entity.setInheritanceJoinColumn(joinColumn); changed(); }

	@Override
	public ServiceList<K2Field> getFields() { return k2FieldService.listForEntity(this); }

	@Override
	public ServiceList<K2Entity> getExtendableEntities() { return service.listAll(); }


	
	
	
}
