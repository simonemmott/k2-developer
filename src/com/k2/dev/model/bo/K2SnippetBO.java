package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetContainerService;
import com.k2.dev.service.K2SnippetParameterService;
import com.k2.dev.service.K2SnippetService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2SnippetBO extends GenericServiceModel implements ServiceModel, K2Snippet {

	@Autowired(required=true)
	protected K2SnippetService snippetService;
	@Override
	public EntityService getService() { return snippetService; }
	
	@Autowired(required=true)
	public K2SnippetContainerService snippetContainerService;
	
	@Autowired(required=true)
	private K2SnippetParameterService snippetParameterService;
	
	private K2SnippetENT entity;
	@Override
	public K2SnippetENT getEntity() { return entity; }
	
	
	public K2SnippetBO() { super(null); }
	public K2SnippetBO(PersistenceState state) { super(state); }
	public K2SnippetBO(K2SnippetENT entity, PersistenceState state) { super(state); this.entity = entity; }

	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.SNIPPET; }
		
	public static K2Snippet NULL = new K2SnippetBO();
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Snippet Null() { return NULL; }
	
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
	public String getClassName() { if (isNull()) { return null; } return entity.getClassName(); }
	@Override
	public void setClassName(String className) { if (isNull()) { return; } entity.setClassName(className); changed(); }

	@Override
	public String getDescription() { if (isNull()) { return null; } return entity.getDescription(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } entity.setDescription(description); changed(); }

	@Override
	public ServiceList<K2SnippetContainer> getContainers() { return snippetContainerService.listForSnippet(this); }

	@Override
	public K2SnippetContainer getContainerForAlias(String alias) { return snippetContainerService.fetchForSnippetAndAlias(this, alias); }

	@Override
	public ServiceList<K2SnippetParameter> getParameters() { return snippetParameterService.listForSnippet(this); }

	@Override
	public K2SnippetParameter getParameterForName(String name) { return snippetParameterService.fetchForSnippetAndName(this, name); }

}
