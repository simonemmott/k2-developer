package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.TemplateBindingDAO;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.bo.TemplateBindingBO;
import com.k2.dev.model.entity.TemplateBindingENT;
import com.k2.dev.service.TemplateBindingService;

@Service("templateBindingService")
@Transactional
public class TemplateBindingServiceImpl extends GenericEntityService<TemplateBindingENT, Long, TemplateBinding> implements TemplateBindingService{

	public static class Lists {
		
		private static abstract class TemplateBindingServiceList extends GenericServiceList<TemplateBindingENT, TemplateBinding> implements ServiceList<TemplateBinding> {
			protected TemplateBindingDAO dao;
			protected TemplateBindingService service;
			public TemplateBindingServiceList(TemplateBindingService service, TemplateBindingDAO dao) { this.service = service; this.dao = dao; }
		}

		public static class All extends TemplateBindingServiceList implements ServiceList<TemplateBinding> {
			public All(TemplateBindingService service, TemplateBindingDAO dao) { super(service, dao); }
			@Override public TemplateBinding newBO() { return service.newBO(); }
			@Override protected List<TemplateBindingENT> getList() { return dao.list(); }
			@Override protected TemplateBinding getBO(TemplateBindingENT entity) { return service.getBO(entity); }
		}

		public static class ForTemplate extends TemplateBindingServiceList implements ServiceList<TemplateBinding> {
			private Template template;
			public ForTemplate(TemplateBindingService service, TemplateBindingDAO dao, Template template) { 
				super(service, dao); 
				this.template = template;
			}
			@Override public TemplateBinding newBO() { return service.newBO(); }
			@Override protected List<TemplateBindingENT> getList() { return dao.listForTemplate(template.getEntity()); }
			@Override protected TemplateBinding getBO(TemplateBindingENT entity) { return service.getBO(entity); }
		}

	}

	public TemplateBindingServiceImpl() {}

	@Autowired
	private TemplateBindingDAO dao;
	@Override
	protected TemplateBindingDAO getDAO() { return dao; }
	@Override
	protected TemplateBinding nullBO() { return TemplateBindingBO.NULL; }
	
	@Override
	public ServiceList<TemplateBinding> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public TemplateBinding newBO(EntityInitialValues<TemplateBindingENT> init) {
		return (TemplateBinding) serviceContext.putBO(new TemplateBindingBO(prepareNewEntity(new TemplateBindingENT(), "TemplateBinding.ID", init), PersistenceState.NEW)); 
	}
	@Override
	public TemplateBinding getBO(TemplateBindingENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (TemplateBinding) serviceContext.getBO(entity); }
		return (TemplateBinding) serviceContext.putBO(new TemplateBindingBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public ServiceList<TemplateBinding> listForTemplate(Template template) { return new Lists.ForTemplate(this, dao, template); }
	@Override
	public TemplateBinding newTemplateBinding() { return super.newBO(); }
	@Override
	public TemplateBinding fetchTemplateBinding(Long id) { return super.fetch(id); }


}