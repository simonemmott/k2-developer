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
import com.k2.dev.dao.TemplateDAO;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.bo.TemplateBO;
import com.k2.dev.model.bo.TemplateContentBO;
import com.k2.dev.model.entity.TemplateContentENT;
import com.k2.dev.model.entity.TemplateENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.TemplateService;

@Service("TemplateService")
@Transactional
public class TemplateServiceImpl extends GenericEntityService<TemplateENT, Long, Template> implements TemplateService{

	public static class Lists {
		
		private static abstract class TemplateServiceList extends GenericServiceList<TemplateENT, Template> implements ServiceList<Template> {
			protected TemplateDAO dao;
			protected TemplateService service;
			public TemplateServiceList(TemplateService service, TemplateDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.TEMPLATE; }
		}

		public static class All extends TemplateServiceList implements ServiceList<Template> {
			public All(TemplateService service, TemplateDAO dao) { super(service, dao); }
			@Override public Template newBO() { return service.newBO(); }
			@Override public Template newBO(Long id) { return service.newBO(id); }
			@Override protected List<TemplateENT> getList() { return dao.list(); }
			@Override protected Template getBO(TemplateENT entity) { return service.getBO(entity); }
		}

	}

	public TemplateServiceImpl() {}

	@Autowired
	private TemplateDAO dao;
	@Override
	protected TemplateDAO getDAO() { return dao; }
	@Override
	public Template nullBO() { return TemplateBO.NULL; }
	
	@Override
	public ServiceList<Template> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public Template newBO(Long id, EntityInitialValues<TemplateENT> init) { 
		if (id == null) {
			return (Template) serviceContext.putBO(new TemplateBO(prepareNewEntity(new TemplateENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (Template) serviceContext.putBO(new TemplateBO(prepareNewEntity(new TemplateENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public Template getBO(TemplateENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Template) serviceContext.getBO(entity); }
		return (Template) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public Template newTemplate() { return super.newBO(); }
	@Override
	public Template fetchTemplate(Long id) { return super.fetch(id); }
	@Override
	public Template fetchForName(String name) { return getBO(dao.fetchForName(name)); }


}
