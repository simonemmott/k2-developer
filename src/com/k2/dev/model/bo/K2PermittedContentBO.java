package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.entity.K2FieldENT;
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
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.PERMITTED_CONTENT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2PermittedContent Null() { return NULL; }
	
	protected K2PermittedContentENT entity;
	@Override
	public K2PermittedContentENT getEntity() { return entity; }
	
	@Override
	public Long getId() {  if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public K2SnippetContainer getContainer() { if (isNull()) { return K2SnippetContainerBO.NULL; } return Nvl.nvl(snippetContainerService.getBO(getEntity().getContainer()), K2SnippetContainerBO.NULL);}
	@Override
	public void setContainer(K2SnippetContainer container) { if (isNull()) { return; } getEntity().setContainer(container.getEntity()); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2PermittedContentENT(); }
		if (K2PermittedContent.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2PermittedContent)source);
		}
	}



	
}
