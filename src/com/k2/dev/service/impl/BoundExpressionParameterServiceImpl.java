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
import com.k2.dev.dao.BoundExpressionParameterDAO;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.bo.BoundExpressionParameterBO;
import com.k2.dev.model.bo.ImplementedExpressionBO;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.BoundExpressionParameterService;

@Service("BoundExpressionParameterServiceService")
@Transactional
public class BoundExpressionParameterServiceImpl extends GenericEntityService<BoundExpressionParameterENT, Long, BoundExpressionParameter> implements BoundExpressionParameterService {

	public static class Lists {
		
		private static abstract class BoundExpressionParameterServiceList extends GenericServiceList<BoundExpressionParameterENT, BoundExpressionParameter> implements ServiceList<BoundExpressionParameter> {
			protected BoundExpressionParameterDAO dao;
			protected BoundExpressionParameterService service;
			public BoundExpressionParameterServiceList(BoundExpressionParameterService service, BoundExpressionParameterDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.BOUND_EXPRESSION_PARAMETER; }
		}

		public static class All extends BoundExpressionParameterServiceList implements ServiceList<BoundExpressionParameter> {
			public All(BoundExpressionParameterService service, BoundExpressionParameterDAO dao) { super(service, dao); }
			@Override public BoundExpressionParameter newBO() { return service.newBO(); }
			@Override public BoundExpressionParameter newBO(Long id) { return service.newBO(id); }
			@Override protected List<BoundExpressionParameterENT> getList() { return dao.list(); }
			@Override protected BoundExpressionParameter getBO(BoundExpressionParameterENT entity) { return service.getBO(entity); }
		}

		public static class ForExpression extends BoundExpressionParameterServiceList implements ServiceList<BoundExpressionParameter> {
			private ImplementedExpression implementedExpression;
			public ForExpression(BoundExpressionParameterService service, BoundExpressionParameterDAO dao, ImplementedExpression implementedExpression) { 
				super(service, dao);
				this.implementedExpression = implementedExpression;
			}
			@Override public BoundExpressionParameter newBO() { return service.newBO(null, new Init.ListForExpression(implementedExpression.getEntity())); }
			@Override public BoundExpressionParameter newBO(Long id) { return service.newBO(id, new Init.ListForExpression(implementedExpression.getEntity())); }
			@Override protected List<BoundExpressionParameterENT> getList() { return dao.listForExpression(implementedExpression.getEntity()); }
			@Override protected BoundExpressionParameter getBO(BoundExpressionParameterENT entity) { return service.getBO(entity); }
		}

	}
	public static class Init {
		
		public static class ListForExpression extends EntityInitialValues<BoundExpressionParameterENT> {
			private ImplementedExpressionENT implementedExpression;
			public ListForExpression(ImplementedExpressionENT implementedExpression) {
				this.implementedExpression = implementedExpression;
			}
			@Override public void initialize(BoundExpressionParameterENT entity) { entity.setExpression(implementedExpression); }
		}
		
	}


	public BoundExpressionParameterServiceImpl() {}
	
	@Autowired
	BoundExpressionParameterDAO dao;
	@Override
	protected BoundExpressionParameterDAO getDAO() { return dao; }

	
	@Override
	public BoundExpressionParameter nullBO() { return BoundExpressionParameterBO.NULL; }
	@Override
	public BoundExpressionParameter getBO(BoundExpressionParameterENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (BoundExpressionParameter) serviceContext.getBO(entity); }
		return (BoundExpressionParameter) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public BoundExpressionParameter newBO(Long id, EntityInitialValues<BoundExpressionParameterENT> init) { 
		if (id == null) {
			return (BoundExpressionParameter) serviceContext.putBO(new BoundExpressionParameterBO(prepareNewEntity(new BoundExpressionParameterENT(), "BoundExpressionParameter.ID", init), PersistenceState.NEW)); 
		} else {
			return (BoundExpressionParameter) serviceContext.putBO(new BoundExpressionParameterBO(prepareNewEntity(new BoundExpressionParameterENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public BoundExpressionParameter newBoundExpressionParameter() { return super.newBO(); }
	@Override
	public BoundExpressionParameter fetchBoundExpressionParameter(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<BoundExpressionParameter> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<BoundExpressionParameter> listImplementedExpression(ImplementedExpression implementedExpression) { return new Lists.ForExpression(this, dao, implementedExpression); }


}
