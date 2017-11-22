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
import com.k2.dev.dao.ExpressionDAO;
import com.k2.dev.model.Expression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.ExpressionBO;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ExpressionService;

@Service("ExpressionService")
@Transactional
public class ExpressionServiceImpl extends GenericEntityService<ExpressionENT, Long, Expression> implements ExpressionService {

	public static class Lists {
		
		private static abstract class ExpressionServiceList extends GenericServiceList<ExpressionENT, Expression> implements ServiceList<Expression> {
			protected ExpressionDAO dao;
			protected ExpressionService service;
			public ExpressionServiceList(ExpressionService service, ExpressionDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.EXPRESSION; }
		}

		public static class All extends ExpressionServiceList implements ServiceList<Expression> {
			public All(ExpressionService service, ExpressionDAO dao) { super(service, dao); }
			@Override public Expression newBO() { return service.newBO(); }
			@Override public Expression newBO(Long id) { return service.newBO(id); }
			@Override protected List<ExpressionENT> getList() { return dao.list(); }
			@Override protected Expression getBO(ExpressionENT entity) { return service.getBO(entity); }
		}

		public static class OfNativeOrServiceOrEntityExpressions extends ExpressionServiceList implements ServiceList<Expression> {
			private K2Service k2Service;
			private K2Entity k2Entity;
			public OfNativeOrServiceOrEntityExpressions(ExpressionService service, ExpressionDAO dao, K2Service k2Service, K2Entity k2Entity) { 
				super(service, dao);
				this.k2Service = k2Service;
				this.k2Entity = k2Entity;
			}
			@Override public Expression newBO() { return service.newBO(null, new Init.OfNativeOrServiceOrEntityExpressions(k2Service, k2Entity)); }
			@Override public Expression newBO(Long id) { return service.newBO(id, new Init.OfNativeOrServiceOrEntityExpressions(k2Service, k2Entity)); }
			@Override protected List<ExpressionENT> getList() { return dao.OfNativeOrServiceOrEntityExpressions(k2Service.getEntity(), k2Entity.getEntity()); }
			@Override protected Expression getBO(ExpressionENT entity) { return service.getBO(entity); }
		}

	}
	
	public static class Init {
		
		public static class OfNativeOrServiceOrEntityExpressions extends EntityInitialValues<ExpressionENT> {
			private K2Service k2Service;
			private K2Entity k2Enity;
			public OfNativeOrServiceOrEntityExpressions(K2Service k2Service, K2Entity k2Entity) {
				this.k2Service = k2Service;
				this.k2Enity = k2Entity;
			}
			@Override public void initialize(ExpressionENT entity) { 
			}
		}
		
	}


	public ExpressionServiceImpl() {}
	
	@Autowired
	ExpressionDAO dao;
	@Override
	protected ExpressionDAO getDAO() { return dao; }

	
	@Override
	public Expression nullBO() { return ExpressionBO.NULL; }
	@Override
	public Expression getBO(ExpressionENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Expression) serviceContext.getBO(entity); }
		return (Expression) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public Expression newBO(Long id, EntityInitialValues<ExpressionENT> init) { 
		if (id == null) {
			return (Expression) serviceContext.putBO(new ExpressionBO(prepareNewEntity(new ExpressionENT(), "Expression.ID", init), PersistenceState.NEW)); 
		} else {
			return (Expression) serviceContext.putBO(new ExpressionBO(prepareNewEntity(new ExpressionENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public Expression newExpression() { return super.newBO(); }
	@Override
	public Expression fetchExpression(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<Expression> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<Expression> listOfNativeOrServiceOrEntityExpressions(K2Service k2Service, K2Entity k2Entity) { 
		return new Lists.OfNativeOrServiceOrEntityExpressions(this, dao, k2Service, k2Entity); 
		}


}
