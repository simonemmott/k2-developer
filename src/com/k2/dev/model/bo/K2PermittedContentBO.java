package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.entity.K2PermittedContentENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2PermittedContentService;
import com.k2.dev.service.K2SnippetContainerService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2PermittedContentBO extends GenericServiceModel implements ServiceModel, K2PermittedContent {
	
	@Autowired(required=true)
	protected K2PermittedContentService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired(required=true)
	private K2SnippetContainerService snippetContainerService;
	
	public static K2PermittedContent NULL = new K2PermittedContentBO();
	public K2PermittedContentBO() { super(null); }
    public K2PermittedContentBO(PersistenceState state) { super(state); }
    public K2PermittedContentBO(K2PermittedContentENT entity, PersistenceState state) { super(state); this.entity = entity; }

	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.PERMITTED_CONTENT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2PermittedContent Null() { return NULL; }
	
	private K2PermittedContentENT entity;
	@Override
	public K2PermittedContentENT getEntity() { return entity; }
	
	@Override
	public Long getID() {  if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } entity.setName(name); changed(); }
	
	@Override
	public K2SnippetContainer getContainer() { if (isNull()) { return K2SnippetContainerBO.NULL; } return snippetContainerService.getBO(entity.getContainer());}
	@Override
	public void setContainer(K2SnippetContainer container) { if (isNull()) { return; } entity.setContainer(container.getEntity()); }

	
}
