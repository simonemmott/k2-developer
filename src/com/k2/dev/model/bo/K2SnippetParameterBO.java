package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetParameterService;
import com.k2.dev.service.K2SnippetService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2SnippetParameterBO extends GenericServiceModel implements ServiceModel, K2SnippetParameter {
	
	@Autowired(required=true)
	protected K2SnippetParameterService service;
	@Override
	public EntityService  getService() { return service; }
	
	@Autowired
	private K2SnippetService snippetService;
	
	public static K2SnippetParameter NULL = new K2SnippetParameterBO();
	public K2SnippetParameterBO() { super(null); }
    public K2SnippetParameterBO(PersistenceState state) { super(state); }
    public K2SnippetParameterBO(K2SnippetParameterENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.SNIPPET_PARAMETER; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2SnippetParameter Null() { return NULL; }
	
	protected K2SnippetParameterENT entity;
	@Override
	public K2SnippetParameterENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public K2Snippet getWidget() { if (isNull()) { return K2SnippetBO.NULL; } return Nvl.nvl(snippetService.getBO(getEntity().getWidget()), K2SnippetBO.NULL); }
	@Override
	public void setWidget(K2Snippet widget) { if (isNull()) { return; } getEntity().setWidget(widget.getEntity()); changed(); }

	@Override
	public String getSetterMethod() { if (isNull()) { return null; } return getEntity().getSetterMethod(); }
	@Override
	public void setSetterMethod(String setterMethod) { if (isNull()) { return; } getEntity().setSetterMethod(setterMethod); changed(); }
	
	@Override
	public String getGetterMethod() { if (isNull()) { return null; } return getEntity().getGetterMethod(); }
	@Override
	public void setGetterMethod(String getterMethod) { if (isNull()) { return; } getEntity().setGetterMethod(getterMethod); changed(); }
	
	@Override
	public String getDataType() { if (isNull()) { return null; } return getEntity().getDataType(); }
	@Override
	public void setDataType(String dataType) { if (isNull()) { return; } getEntity().setDataType(dataType); changed(); }
	
	@Override
	public String getDescription() { if (isNull()) { return null; } return getEntity().getDescription(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } getEntity().setDescription(description); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2SnippetParameterENT(); }
		if (K2SnippetParameter.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2SnippetParameter)source);
		}
	}


	
}
