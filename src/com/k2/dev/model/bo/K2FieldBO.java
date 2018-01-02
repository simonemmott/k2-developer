package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.util.StringUtil;
import com.k2.common.expressions.Ex;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2FieldENT.Types;
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
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2FIELD; }

	public String getIdentity() { return getName(); }

	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Field Null() { return NULL; }
	
	protected K2FieldENT entity;
	@Override
	public K2FieldENT getEntity() { return (K2FieldENT)entity; }

	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }

	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }

	@Override
	public K2Entity getK2Entity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getK2Entity()), K2EntityBO.NULL); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } getEntity().setK2Entity(k2Entity.getEntity()); changed(); }

	@Override
	public Integer getColumnLength() { if (isNull()) { return null; } return getEntity().getColumnLength(); }
	@Override
	public void setColumnLength(Integer length) { if (isNull()) { return; } getEntity().setColumnLength(length); changed(); }
	
	@Override
	public String getColumnName() { if (isNull()) { return null; } return getEntity().getColumnName(); }
	@Override
	public void setColumnName(String columnName) { if (isNull()) { return; } getEntity().setColumnName(columnName); changed(); }
	
	@Override
	public Boolean getNullable() { if (isNull()) { return null; } return getEntity().getNullable(); }
	@Override
	public void setNullable(Boolean nullable) { if (isNull()) { return; } getEntity().setNullable(nullable); changed(); }
	
	@Override
	public Types.FieldType getFieldType() { if (isNull()) { return null; } return getEntity().getFieldType(); }
	
	@Override
	public Boolean getEnabled() { if (isNull()) { return null; } return getEntity().getEnabled(); }
	@Override
	public void setEnabled(Boolean enabled) { if (isNull()) { return; } getEntity().setEnabled(enabled); changed(); }
	
	@Override
	public String getTopCaption() { if (isNull()) { return null; } return getEntity().getTopCaption(); }
	@Override
	public void setTopCaption(String topCaption) { if (isNull()) { return; } getEntity().setTopCaption(topCaption); changed(); }
	
	@Override
	public String getLeftCaption() { if (isNull()) { return null; } return getEntity().getLeftCaption(); }
	@Override
	public void setLeftCaption(String leftCaption) { if (isNull()) { return; } getEntity().setLeftCaption(leftCaption); changed(); }
	
	@Override
	public String getRightCaption() { if (isNull()) { return null; } return getEntity().getRightCaption(); }
	@Override
	public void setRightCaption(String rightCaption) { if (isNull()) { return; } getEntity().setRightCaption(rightCaption); changed(); }
	
	@Override
	public Integer getDisplayedSize() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDisplayedSize(Integer displayedSize) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2FieldENT(); }
		if (K2Field.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2Field)source);
		}
	}


	
	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listAll(); }

	@Override
	public Boolean getIsLinked() {
		return (getFieldType() == Types.FieldType.LINKED);
	}
	
	@Override
	public String getInitialUpperAlias() {
		return StringUtil.initialUpperCase(getAlias());
	}
	
	@Override
	public String getDataType() { return ""; };

	@Override
	public String getCanonicalDataType() { return ""; };

	@Override
	public Boolean getRequired() { return Ex.BOOLEAN.not(getNullable());
	}
	@Override
	public Boolean getIsDiscriminator() {
		// TODO Auto-generated method stub
		return null;
	}



}
