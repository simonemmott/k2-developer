package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2PermittedContentService;
import com.k2.dev.service.K2SnippetContainerService;
import com.k2.dev.service.K2SnippetService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2SnippetContainerBO extends GenericServiceModel implements ServiceModel, K2SnippetContainer {
	
	@Autowired(required=true)
	protected K2SnippetContainerService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired
	private K2SnippetService snippetService;
	
	@Autowired
	K2PermittedContentService permittedContentsService;

	public static K2SnippetContainer NULL = new K2SnippetContainerBO();
	public K2SnippetContainerBO() { super(null); }
	public K2SnippetContainerBO(PersistenceState state) { super(state); }
	public K2SnippetContainerBO(K2SnippetContainerENT entity, PersistenceState state) { super(state); this.entity = entity; }

	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.SNIPPET_CONTAINER; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2SnippetContainer Null() { return NULL; }
	
	protected K2SnippetContainerENT entity;
	@Override
	public K2SnippetContainerENT getEntity() { return entity; }
	
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } changed(); getEntity().setId(id); }
	
	@Override
	public K2Snippet getWidget() { if (isNull()) { return K2SnippetBO.NULL; } return Nvl.nvl(snippetService.getBO(getEntity().getWidget()), K2SnippetBO.NULL); }
	@Override
	public void setWidget(K2Snippet widget) { if (isNull()) { return; } changed(); getEntity().setWidget(widget.getEntity()); changed(); } 
	
	@Override
	public String getAlias() {  if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) {  if (isNull()) { return; } changed(); getEntity().setAlias(alias); }
	
	@Override
	public String getName() {  if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) {  if (isNull()) { return; } changed(); getEntity().setName(name); }
	
	@Override
	public String getDescription() {  if (isNull()) { return null; } return getEntity().getDescription(); }
	@Override
	public void setDescription(String description) {  if (isNull()) { return; } changed(); getEntity().setDescription(description); }
	
	@Override
	public ServiceList<K2PermittedContent> getPermittedContents() { return permittedContentsService.listForSnippetContainer(this); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2SnippetContainerENT(); }
		if (K2SnippetContainer.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2SnippetContainer)source);
		}
	}



}
