package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.TemplateContentDAO;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.bo.TemplateBindingBO;
import com.k2.dev.model.bo.TemplateContentBO;
import com.k2.dev.model.entity.TemplateBindingENT;
import com.k2.dev.model.entity.TemplateContentENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.TemplateContentService;

@Service("TemplateContentService")
@Transactional
public class TemplateContentServiceImpl extends GenericEntityService<TemplateContentENT, Long, TemplateContent> implements TemplateContentService{

	public static class Lists {
		
		private static abstract class TemplateContentServiceList extends GenericServiceList<TemplateContentENT, TemplateContent> implements ServiceList<TemplateContent> {
			protected TemplateContentDAO dao;
			protected TemplateContentService service;
			public TemplateContentServiceList(TemplateContentService service, TemplateContentDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.TEMPLATECONTENT; }
		}

		public static class All extends TemplateContentServiceList implements ServiceList<TemplateContent> {
			public All(TemplateContentService service, TemplateContentDAO dao) { super(service, dao); }
			@Override public TemplateContent newBO() { return service.newBO(); }
			@Override public TemplateContent newBO(Long id) { return service.newBO(id); }
			@Override protected List<TemplateContentENT> getList() { return dao.list(); }
			@Override protected TemplateContent getBO(TemplateContentENT entity) { return service.getBO(entity); }
		}

		public static class ForTemplate extends TemplateContentServiceList implements ServiceList<TemplateContent> {
			private Template template;
			public ForTemplate(TemplateContentService service, TemplateContentDAO dao, Template template) { 
				super(service, dao); 
				this.template = template;
				}
			@Override public TemplateContent newBO() { return service.newBO(); }
			@Override public TemplateContent newBO(Long id) { return service.newBO(id); }
			@Override protected List<TemplateContentENT> getList() { return dao.listForTemplate(template.getEntity()); }
			@Override protected TemplateContent getBO(TemplateContentENT entity) { return service.getBO(entity); }
		}

		public static class ForTemplateContent extends TemplateContentServiceList implements ServiceList<TemplateContent> {
			private TemplateContent templateContent;
			public ForTemplateContent(TemplateContentService service, TemplateContentDAO dao, TemplateContent templateContent) { 
				super(service, dao); 
				this.templateContent = templateContent;
				}
			@Override public TemplateContent newBO() { return service.newBO(); }
			@Override public TemplateContent newBO(Long id) { return service.newBO(id); }
			@Override protected List<TemplateContentENT> getList() { return dao.listForTemplateContent(templateContent.getEntity()); }
			@Override protected TemplateContent getBO(TemplateContentENT entity) { return service.getBO(entity); }
		}

	}

	public TemplateContentServiceImpl() {}

	@Autowired
	private TemplateContentDAO dao;
	@Override
	protected TemplateContentDAO getDAO() { return dao; }
	@Override
	public TemplateContent nullBO() { return TemplateContentBO.NULL; }
	
	@Override
	public ServiceList<TemplateContent> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public TemplateContent newBO(Long id, EntityInitialValues<TemplateContentENT> init) { 
		if (id == null) {
			return (TemplateContent) serviceContext.putBO(new TemplateContentBO(prepareNewEntity(new TemplateContentENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (TemplateContent) serviceContext.putBO(new TemplateContentBO(prepareNewEntity(new TemplateContentENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public TemplateContent getBO(TemplateContentENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (TemplateContent) serviceContext.getBO(entity); }
		return (TemplateContent) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public ServiceList<TemplateContent> listForTemplate(Template template) { return new Lists.ForTemplate(this, dao, template); }
	@Override
	public ServiceList<TemplateContent> listForContent(TemplateContent templateContent) { return new Lists.ForTemplateContent(this, dao, templateContent); }
	@Override
	public TemplateContent newTemplateContent() { return super.newBO(); }
	@Override
	public TemplateContent fetchTemplateContent(Long id) { return super.fetch(id); }


}
