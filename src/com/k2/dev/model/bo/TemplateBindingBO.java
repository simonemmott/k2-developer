package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.Literal;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.TemplateBindingENT;
import com.k2.dev.model.entity.TemplateBindingENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetParameterService;
import com.k2.dev.service.K2SnippetService;
import com.k2.dev.service.LiteralService;
import com.k2.dev.service.TemplateBindingService;
import com.k2.dev.service.TemplateService;

@SuppressWarnings("rawtypes")
@Configurable
public class TemplateBindingBO extends K2SnippetBindingBO implements ServiceModel, TemplateBinding {
	
	@Autowired(required=true)
	protected TemplateBindingService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired
	K2SnippetService snippetService;
	
	@Autowired
	TemplateService templateService;
	
	@Autowired
	K2SnippetParameterService snippetParameterService;
	
	@Autowired
	LiteralService literalService;
	
	public static TemplateBinding NULL = new TemplateBindingBO();
	public TemplateBindingBO() { super(null); }
    public TemplateBindingBO(PersistenceState state) { super(state); }
    public TemplateBindingBO(TemplateBindingENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.TEMPLATE_BINDING; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public TemplateBinding Null() { return NULL; }
	
	@Override
	public TemplateBindingENT getEntity() { return (TemplateBindingENT)entity; }

	@Override
	public Types.BindingSource getBindingSource() { if (isNull()) { return null; } return getEntity().getBindingSource(); }
	@Override
	public void setBindingSource(Types.BindingSource bindingSource) { if (isNull()) { return; } getEntity().setBindingSource(bindingSource); changed(); }

	@Override
	public Template getBindingTemplate() { if (isNull()) { return TemplateBO.NULL; } return Nvl.nvl(templateService.getBO(getEntity().getBindingTemplate()), TemplateBO.NULL); }
	@Override
	public void setBindingTemplate(Template template) { if (isNull()) { return; } getEntity().setBindingTemplate(template.getEntity()); changed(); }

	@Override
	public K2SnippetParameter getBindingParameter() { if (isNull()) { return K2SnippetParameterBO.NULL; } return Nvl.nvl(snippetParameterService.getBO(getEntity().getBindingParameter()), K2SnippetParameterBO.NULL); }
	@Override
	public void setBindingParameter(K2SnippetParameter bindingParameter) { if (isNull()) { return; } getEntity().setBindingParameter(bindingParameter.getEntity()); changed(); }
	
	@Override
	public Literal getLiteral() { if (isNull()) { return LiteralBO.NULL; } return Nvl.nvl(literalService.getBO(getEntity().getLiteral()), LiteralBO.NULL); }
	@Override
	public void setLiteral(Literal literal) { if (isNull()) { return; } getEntity().setLiteral(literal.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new TemplateBindingENT(); }
		if (TemplateBinding.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((TemplateBinding)source);
		} else {
			super.clone(source);
		}
	}


	
	
}
