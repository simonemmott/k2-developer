package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetParameter;
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
	public MetaEntity getMetaEntity() { return MetaModel.Entities.SNIPPET_PARAMETER; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2SnippetParameter Null() { return NULL; }
	
	private K2SnippetParameterENT entity;
	@Override
	public K2SnippetParameterENT getEntity() { return entity; }
	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } entity.setName(name); changed(); }
	
	@Override
	public K2Snippet getWidget() { if (isNull()) { return K2SnippetBO.NULL; } return snippetService.getBO(entity.getWidget()); }
	@Override
	public void setWidget(K2Snippet widget) { if (isNull()) { return; } entity.setWidget(widget.getEntity()); changed(); }

	@Override
	public String getSetterMethod() { if (isNull()) { return null; } return entity.getSetterMethod(); }
	@Override
	public void setSetterMethod(String setterMethod) { if (isNull()) { return; } entity.setSetterMethod(setterMethod); changed(); }
	
	@Override
	public String getGetterMethod() { if (isNull()) { return null; } return entity.getGetterMethod(); }
	@Override
	public void setGetterMethod(String getterMethod) { if (isNull()) { return; } entity.setGetterMethod(getterMethod); changed(); }
	
	@Override
	public String getDataType() { if (isNull()) { return null; } return entity.getDataType(); }
	@Override
	public void setDataType(String dataType) { if (isNull()) { return; } entity.setDataType(dataType); changed(); }
	
	@Override
	public String getDescription() { if (isNull()) { return null; } return entity.getDescription(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } entity.setDescription(description); changed(); }
	

	
}
