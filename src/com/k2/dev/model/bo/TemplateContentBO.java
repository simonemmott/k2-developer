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
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.entity.TemplateContentENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2PermittedContentService;
import com.k2.dev.service.K2SnippetContainerService;
import com.k2.dev.service.K2SnippetService;
import com.k2.dev.service.TemplateBindingService;
import com.k2.dev.service.TemplateContentService;
import com.k2.dev.service.TemplateService;

@SuppressWarnings("rawtypes")
@Configurable
public class TemplateContentBO extends GenericServiceModel implements ServiceModel, TemplateContent {
	
	@Autowired(required=true)
	protected TemplateContentService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired(required=true)
	TemplateService templateService;
	
	@Autowired(required=true)
	K2SnippetService snippetService;
	
	@Autowired(required=true)
	K2SnippetContainerService snippetContainerService;
	
	@Autowired(required=true)
	K2PermittedContentService permittedContentService;
	
	@Autowired
	TemplateBindingService templateBindingService;
	
	public static TemplateContent NULL = new TemplateContentBO();
	public TemplateContentBO() { super(null); }
    public TemplateContentBO(PersistenceState state) { super(state); }
    public TemplateContentBO(TemplateContentENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.TEMPLATE_CONTENT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public TemplateContent Null() { return NULL; }
	
	private TemplateContentENT entity;
	@Override
	public TemplateContentENT getEntity() { return entity; }
	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getAlias() { if (isNull()) { return null; } return entity.getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } entity.setAlias(alias); changed(); }
		
	@Override
	public Template getTemplate() { if (isNull()) { return null; } return templateService.getBO(entity.getTemplate()); }
	@Override
	public void setTemplate(Template template) { if (isNull()) { return; } entity.setTemplate(template.getEntity()); changed(); }
	
	@Override
	public K2Snippet getContainedSnippet() { if (isNull()) { return null; } return snippetService.getBO(entity.getContainedSnippet()); }
	@Override
	public void setContainedSnippet(K2Snippet snippet) { if (isNull()) { return; } entity.setContainedSnippet(snippet.getEntity()); changed(); }
	
	@Override
	public K2SnippetContainer getInContainer() { if (isNull()) { return K2SnippetContainerBO.NULL; } return snippetContainerService.getBO(entity.getInContainer()); }
	@Override
	public void setInContainer(K2SnippetContainer inContainer) { if (isNull()) { return; } entity.setInContainer(inContainer.getEntity()); changed(); }
	
	@Override
	public K2SnippetContainer getFromContainer() { if (isNull()) { return K2SnippetContainerBO.NULL; } return snippetContainerService.getBO(entity.getFromContainer()); }
	@Override
	public void setFromContainer(K2SnippetContainer fromContainer) { if (isNull()) { return; } entity.setFromContainer(fromContainer.getEntity()); changed(); }

	@Override
	public TemplateContentENT.Types.Type getType() { if (isNull()) { return null; } return entity.getType(); }
	@Override
	public void setType(TemplateContentENT.Types.Type type) {  if (isNull()) { return; } entity.setType(type); changed(); }
	
	@Override
	public ServiceList<TemplateContent> getContents() { return service.listForContent(this); }
	
	@Override
	public TemplateContent getParentContent() { return service.getBO(entity.getParentContent()); }
	@Override
	public void setParentContent(TemplateContent parentContent) { if (isNull()) { return ; } entity.setParentContent(parentContent.getEntity()); changed(); }
	
	@Override
	public ServiceList<TemplateBinding> getBindings() { return templateBindingService.listForTemplate(getTemplate()); }
	
	
}
