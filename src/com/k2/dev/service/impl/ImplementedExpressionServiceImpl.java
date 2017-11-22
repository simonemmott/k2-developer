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
import com.k2.dev.dao.ImplementedExpressionDAO;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.ImplementedExpressionBO;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.impl.DependencyServiceImpl.Lists;

@Service("ImplementedExpressionService")
@Transactional
public class ImplementedExpressionServiceImpl extends GenericEntityService<ImplementedExpressionENT, Long, ImplementedExpression> implements ImplementedExpressionService{

	public static class Lists {
		private static abstract class ImplementedExpressionServiceList extends GenericServiceList<ImplementedExpressionENT, ImplementedExpression> implements ServiceList<ImplementedExpression> {
			protected ImplementedExpressionDAO dao;
			protected ImplementedExpressionService service;
			public ImplementedExpressionServiceList(ImplementedExpressionService service, ImplementedExpressionDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.IMPLEMENTED_EXPRESSION; }
		}

		public static class All extends ImplementedExpressionServiceList implements ServiceList<ImplementedExpression> {
			public All(ImplementedExpressionService service, ImplementedExpressionDAO dao) { super(service, dao); }
			@Override public ImplementedExpression newBO() { return service.newBO(); }
			@Override public ImplementedExpression newBO(Long id) { return service.newBO(id); }
			@Override protected List<ImplementedExpressionENT> getList() { return dao.list(); }
			@Override protected ImplementedExpression getBO(ImplementedExpressionENT entity) { return service.getBO(entity); }
		}

		public static class ForServiceOnly extends ImplementedExpressionServiceList implements ServiceList<ImplementedExpression> {
			private K2Service k2Service;
			public ForServiceOnly(ImplementedExpressionService service, ImplementedExpressionDAO dao, K2Service k2Service) { 
				super(service, dao);
				this.k2Service = k2Service;
			}
			@Override public ImplementedExpression newBO() { return service.newBO(null, new Init.ForServiceOnly(k2Service)); }
			@Override public ImplementedExpression newBO(Long id) { return service.newBO(id, new Init.ForServiceOnly(k2Service)); }
			@Override protected List<ImplementedExpressionENT> getList() { return dao.listForServiceOnly(k2Service.getEntity()); }
			@Override protected ImplementedExpression getBO(ImplementedExpressionENT entity) { return service.getBO(entity); }
		}

		public static class ForServiceAndEntity extends ImplementedExpressionServiceList implements ServiceList<ImplementedExpression> {
			private K2Service k2Service;
			private K2Entity k2Entity;
			public ForServiceAndEntity(ImplementedExpressionService service, ImplementedExpressionDAO dao, K2Service k2Service, K2Entity k2Entity) { 
				super(service, dao);
				this.k2Service = k2Service;
				this.k2Entity = k2Entity;
			}
			@Override public ImplementedExpression newBO() { return service.newBO(null, new Init.ForServiceAndEntity(k2Service, k2Entity)); }
			@Override public ImplementedExpression newBO(Long id) { return service.newBO(id, new Init.ForServiceAndEntity(k2Service, k2Entity)); }
			@Override protected List<ImplementedExpressionENT> getList() { return dao.listForServiceAndEntity(k2Service.getEntity(), k2Entity.getEntity()); }
			@Override protected ImplementedExpression getBO(ImplementedExpressionENT entity) { return service.getBO(entity); }
		}

		public static class ForServiceAndBoundToParameter extends ImplementedExpressionServiceList implements ServiceList<ImplementedExpression> {
			private K2Service k2Service;
			private BoundExpressionParameter boundToParameter;
			public ForServiceAndBoundToParameter(ImplementedExpressionService service, ImplementedExpressionDAO dao, K2Service k2Service, BoundExpressionParameter boundToParameter) { 
				super(service, dao);
				this.k2Service = k2Service;
				this.boundToParameter = boundToParameter;
			}
			@Override public ImplementedExpression newBO() { return service.newBO(null, new Init.ForServiceAndBoundToParameter(k2Service, boundToParameter)); }
			@Override public ImplementedExpression newBO(Long id) { return service.newBO(id, new Init.ForServiceAndBoundToParameter(k2Service, boundToParameter)); }
			@Override protected List<ImplementedExpressionENT> getList() { return dao.listForServiceAndBoundToParameter(k2Service.getEntity(), boundToParameter.getEntity()); }
			@Override protected ImplementedExpression getBO(ImplementedExpressionENT entity) { return service.getBO(entity); }
		}

		public static class ForService extends ImplementedExpressionServiceList implements ServiceList<ImplementedExpression> {
			private K2Service k2Service;
			public ForService(ImplementedExpressionService service, ImplementedExpressionDAO dao, K2Service k2Service) { 
				super(service, dao);
				this.k2Service = k2Service;
			}
			@Override public ImplementedExpression newBO() { return service.newBO(null, new Init.ForService(k2Service)); }
			@Override public ImplementedExpression newBO(Long id) { return service.newBO(id, new Init.ForService(k2Service)); }
			@Override protected List<ImplementedExpressionENT> getList() { return dao.listForService(k2Service.getEntity()); }
			@Override protected ImplementedExpression getBO(ImplementedExpressionENT entity) { return service.getBO(entity); }
		}

	}

	public static class Init {
		
		public static class ForServiceOnly extends EntityInitialValues<ImplementedExpressionENT> {
			private K2Service k2Service;
			public ForServiceOnly(K2Service k2Service) {
				this.k2Service = k2Service;
			}
			@Override public void initialize(ImplementedExpressionENT entity) { 
				entity.setK2Service(k2Service.getEntity()); 
				// entity.setK2Entity(null); 
			}
		}
		
		public static class ForServiceAndEntity extends EntityInitialValues<ImplementedExpressionENT> {
			private K2Service k2Service;
			private K2Entity k2Entity;
			public ForServiceAndEntity(K2Service k2Service, K2Entity k2Entity) {
				this.k2Service = k2Service;
				this.k2Entity = k2Entity;
			}
			@Override public void initialize(ImplementedExpressionENT entity) { 
				entity.setK2Service(k2Service.getEntity()); 
				entity.setK2Entity(k2Entity.getEntity()); 
			}
		}
		
		public static class ForServiceAndBoundToParameter extends EntityInitialValues<ImplementedExpressionENT> {
			private K2Service k2Service;
			private BoundExpressionParameter boundToParameter;
			public ForServiceAndBoundToParameter(K2Service k2Service, BoundExpressionParameter boundToParameter) {
				this.k2Service = k2Service;
				this.boundToParameter = boundToParameter;
			}
			@Override public void initialize(ImplementedExpressionENT entity) { 
				entity.setK2Service(k2Service.getEntity()); 
				entity.setBoundToParameter(boundToParameter.getEntity()); 
			}
		}
		
		public static class ForService extends EntityInitialValues<ImplementedExpressionENT> {
			private K2Service k2Service;
			public ForService(K2Service k2Service) {
				this.k2Service = k2Service;
			}
			@Override public void initialize(ImplementedExpressionENT entity) { 
				entity.setK2Service(k2Service.getEntity()); 
			}
		}
		
	}

	public ImplementedExpressionServiceImpl() {}

	@Autowired
	private ImplementedExpressionDAO dao;
	@Override
	protected ImplementedExpressionDAO getDAO() { return dao; }
	@Override
	public ImplementedExpression nullBO() { return ImplementedExpressionBO.NULL; }
	
	@Override
	public ServiceList<ImplementedExpression> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public ImplementedExpression newBO(Long id, EntityInitialValues<ImplementedExpressionENT> init) { 
		if (id == null) {
			return (ImplementedExpression) serviceContext.putBO(new ImplementedExpressionBO(prepareNewEntity(new ImplementedExpressionENT(), "Expression.ID", init), PersistenceState.NEW)); 
		} else {
			return (ImplementedExpression) serviceContext.putBO(new ImplementedExpressionBO(prepareNewEntity(new ImplementedExpressionENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	@Override
	public ImplementedExpression getBO(ImplementedExpressionENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (ImplementedExpression) serviceContext.getBO(entity); }
		return (ImplementedExpression) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public ImplementedExpression newImplementedExpression() { return super.newBO(); }
	@Override
	public ImplementedExpression fetchImplementedExpression(Long id) { return super.fetch(id); }
	@Override
	public ServiceList<ImplementedExpression> listForServiceOnly(K2Service k2Service) { return new Lists.ForServiceOnly(this, dao, k2Service); }
	@Override
	public ServiceList<ImplementedExpression> listForServiceAndEntity(K2Service k2Service, K2Entity k2Entity) { return new Lists.ForServiceAndEntity(this, dao, k2Service, k2Entity); }
	@Override
	public ServiceList<ImplementedExpression> listForService(K2Service k2Service) { return new Lists.ForService(this, dao, k2Service); }
	@Override
	public ServiceList<ImplementedExpression> listForServiceAndBoundToParameter(K2Service k2Service, BoundExpressionParameter boundExpressionParameter) { return new Lists.ForServiceAndBoundToParameter(this, dao, k2Service, boundExpressionParameter); }

}
