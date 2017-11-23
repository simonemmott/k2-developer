package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeField;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2TypeFieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2LinkedFieldService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.service.K2TypeFieldService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2TypeFieldBO extends K2FieldBO implements ServiceModel, K2TypeField {
	
	@Autowired(required=true)
	protected K2TypeFieldService service;
	@Autowired(required=true)
	protected K2TypeDefService k2TypeDefService;

	@Override
	public EntityService  getService() { return service; }
	
	public static K2TypeField NULL = new K2TypeFieldBO();
	public K2TypeFieldBO() { super(null); }
	public K2TypeFieldBO(PersistenceState state) { super(state); }
	public K2TypeFieldBO(K2TypeFieldENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2TYPEFIELD; }

	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2TypeField Null() { return NULL; }
	
	@Override
	public K2TypeFieldENT getEntity() { return (K2TypeFieldENT)entity; }
	
	@Override
	public K2TypeDef getTypeDefinition() { if (isNull()) { return K2TypeDefBO.NULL; } return Nvl.nvl(k2TypeDefService.getBO(getEntity().getTypeDefinition()), K2TypeDefBO.NULL); }
	@Override
	public void setTypeDefinition(K2TypeDef typeDefinition) { if (isNull()) { return; } getEntity().setTypeDefinition(typeDefinition.getEntity()); changed(); }

	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2TypeFieldENT(); }
		if (K2TypeField.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2TypeField)source);
		} else {
			super.clone(source);
		}
	}
	@Override
	public ServiceList<K2TypeDef> getEntityTypes() { return k2TypeDefService.listForEntity(getK2Entity()); }

	@Override
	public Boolean getIsDiscriminator() {
		return getK2Entity().getDiscriminatorType().equals(getTypeDefinition());
	}

	@Override
	public String getDataType() { return "Types."+getTypeDefinition().getAlias(); };

	@Override
	public String getCanonicalDataType() { return getK2Entity().getENTPackageName()+"."+getK2Entity().getENTClassName()+"."+getDataType(); };

}
