package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.FieldSetMember;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.ComponentENT.Types;
import com.k2.dev.model.entity.FieldSetMemberENT;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2FieldSetService;
import com.k2.dev.service.K2ServiceService;

@SuppressWarnings("rawtypes")
@Configurable
public class FieldSetMemberBO extends GenericServiceModel implements ServiceModel, FieldSetMember {
	
	@Autowired(required=true)
	protected K2FieldSetService service;
	@Autowired(required=true)
	protected K2FieldService k2FieldService;
	@Autowired(required=true)
	protected K2FieldSetService k2FieldSetService;
	@Override
	public EntityService getService() { return service; }
	
	public static FieldSetMember NULL = new FieldSetMemberBO();
	public FieldSetMemberBO() { super(null); }
	public FieldSetMemberBO(PersistenceState state) { super(state); }
    public FieldSetMemberBO(FieldSetMemberENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.FIELD_SET_MEMBER; }
	
	public String getIdentity() { return getMember().getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public FieldSetMember Null() { return NULL; }
	
	protected FieldSetMemberENT entity;
	@Override
	public FieldSetMemberENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public K2FieldSet getFieldSet() { if (isNull()) { return K2FieldSetBO.NULL; } return Nvl.nvl(k2FieldSetService.getBO(getEntity().getFieldSet()), K2FieldSetBO.NULL); }
	@Override
	public void setFieldSet(K2FieldSet fieldSet) { if (isNull()) { return; } getEntity().setFieldSet(fieldSet.getEntity()); changed(); }
	
	@Override
	public Integer getOrderInSet() { if (isNull()) { return null; } return getEntity().getOrderInSet(); }
	@Override
	public void setOrderInSet(Integer orderInSet) { if (isNull()) { return; } getEntity().setOrderInSet(orderInSet); changed(); }
	
	public K2Field getMember() { if (isNull()) { return K2FieldBO.NULL; } return Nvl.nvl(k2FieldService.getBO(getEntity().getMember()), K2FieldBO.NULL); }
	@Override
	public void setMember(K2Field member) { if (isNull()) { return; } getEntity().setMember(member.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new FieldSetMemberENT(); }
		if (Component.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Component)source);
		}
	}
	
	// Lists ----------
	// ServiceComponents list
	@Override
	public ServiceList<K2Field> getComponentFields() { return k2FieldService.listForComponent(getFieldSet().getComponent()); }
	
	// FieldSets list
	@Override
	public ServiceList<K2FieldSet> getFieldSets() { return k2FieldSetService.listAll(); }

}
