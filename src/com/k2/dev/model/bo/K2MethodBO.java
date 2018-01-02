package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.entity.K2MethodENT;
import com.k2.dev.model.entity.K2MethodENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.DependencyService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2MethodService;


@SuppressWarnings("rawtypes")
@Configurable
public class K2MethodBO extends GenericServiceModel implements ServiceModel, K2Method {
	
	@Autowired(required=true)
	protected K2MethodService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Autowired(required=true)
	protected DependencyService dependencyService;
	@Override
	public EntityService  getService() { return service; }
	
	public static K2Method NULL = new K2MethodBO();
	public K2MethodBO() { super(null); }
    public K2MethodBO(PersistenceState state) { super(state); }
    public K2MethodBO(K2MethodENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.METHOD; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Method Null() { return NULL; }
	
	protected K2MethodENT entity;
	@Override
	public K2MethodENT getEntity() { return entity; }
	
	// Identity -------
	public String getIdentity() { return getName(); }

	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }

	@Override
	public Types.returnTypes getReturnsType() { if (isNull()) { return null; } return getEntity().getReturnsType(); }
	@Override
	public void setReturnsType(Types.returnTypes returnsType) { if (isNull()) { return; } getEntity().setReturnsType(returnsType); changed(); }

	// K2Entity field
	@Override
    public K2Entity getK2Entity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getK2Entity()), K2EntityBO.NULL); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } getEntity().setK2Entity(k2Entity.getEntity()); changed(); }
    	
	@Override
    public K2Entity getReturnsEntity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getReturnsEntity()), K2EntityBO.NULL); }
	@Override
	public void setReturnsEntity(K2Entity returnsEntity) { if (isNull()) { return; } getEntity().setReturnsEntity(returnsEntity.getEntity()); changed(); }
    	
	@Override
	public Types.nativeReturnType getNativeReturnType() { if (isNull()) { return null; } return getEntity().getNativeReturnType(); }
	@Override
	public void setNativeReturnType(Types.nativeReturnType nativeType) { if (isNull()) { return; } getEntity().setNativeReturnType(nativeType); changed(); }

	@Override
	public String getMethodBody() { if (isNull()) { return null; } return getEntity().getMethodBody(); }
	@Override
	public void setMethodBody(String methodBody) { if (isNull()) { return; } getEntity().setMethodBody(methodBody); changed(); }
	
	@Override
	public String getDependencyFields() { if (isNull()) { return null; } return getEntity().getDependencyFields(); }
	@Override
	public void setDependencyFields(String dependencyFields) { if (isNull()) { return; } getEntity().setDependencyFields(dependencyFields); changed(); }

	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2MethodENT(); }
		if (K2Method.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2Method)source);
		}
	}
	
	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listAll(); }

	@Override
	public ServiceList<Dependency> getMethodDependencies() { return dependencyService.listForEntityAndMethod(getK2Entity(), this); }


}
