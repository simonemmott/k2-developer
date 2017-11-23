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
import com.k2.dev.dao.ExpressionParameterDAO;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.bo.ExpressionParameterBO;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ExpressionParameterENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ExpressionParameterService;

@Service("ExpressionParameterService")
@Transactional
public class ExpressionParameterServiceImpl extends GenericEntityService<ExpressionParameterENT, Long, ExpressionParameter> implements ExpressionParameterService {

	public static class Lists {
		
		private static abstract class ExpressionParameterServiceList extends GenericServiceList<ExpressionParameterENT, ExpressionParameter> implements ServiceList<ExpressionParameter> {
			protected ExpressionParameterDAO dao;
			protected ExpressionParameterService service;
			public ExpressionParameterServiceList(ExpressionParameterService service, ExpressionParameterDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.EXPRESSION_PARAMETER; }
		}

		public static class All extends ExpressionParameterServiceList implements ServiceList<ExpressionParameter> {
			public All(ExpressionParameterService service, ExpressionParameterDAO dao) { super(service, dao); }
			@Override public ExpressionParameter newBO() { return service.newBO(); }
			@Override public ExpressionParameter newBO(Long id) { return service.newBO(id); }
			@Override protected List<ExpressionParameterENT> getList() { return dao.list(); }
			@Override protected ExpressionParameter getBO(ExpressionParameterENT entity) { return service.getBO(entity); }
		}

		public static class ForExpression extends ExpressionParameterServiceList implements ServiceList<ExpressionParameter> {
			private Expression expression;
			public ForExpression(ExpressionParameterService service, ExpressionParameterDAO dao, Expression expression) { 
				super(service, dao);
				this.expression = expression;
			}
			@Override public ExpressionParameter newBO() { return service.newBO(null, new Init.ListForExpression(expression.getEntity())); }
			@Override public ExpressionParameter newBO(Long id) { return service.newBO(id, new Init.ListForExpression(expression.getEntity())); }
			@Override protected List<ExpressionParameterENT> getList() { return dao.ListForExpression(expression.getEntity()); }
			@Override protected ExpressionParameter getBO(ExpressionParameterENT entity) { return service.getBO(entity); }
		}

		public static class ForExpressionOrBoundParameterExpression extends ExpressionParameterServiceList implements ServiceList<ExpressionParameter> {
			private Expression expression;
			private Expression boundToParameterExpression;
			public ForExpressionOrBoundParameterExpression(ExpressionParameterService service, ExpressionParameterDAO dao, Expression expression, Expression boundToParameterExpression) { 
				super(service, dao);
				this.expression = expression;
				this.boundToParameterExpression = boundToParameterExpression;
			}
			@Override public ExpressionParameter newBO() { return service.newBO(null, new Init.ListForExpressionOrBoundParameterExpression()); }
			@Override public ExpressionParameter newBO(Long id) { return service.newBO(id, new Init.ListForExpressionOrBoundParameterExpression()); }
			@Override protected List<ExpressionParameterENT> getList() { return dao.ListForExpressionOrBoundParameterExpression(expression.getEntity(), boundToParameterExpression.getEntity()); }
			@Override protected ExpressionParameter getBO(ExpressionParameterENT entity) { return service.getBO(entity); }
		}

	}
	public static class Init {
		
		public static class ListForExpression extends EntityInitialValues<ExpressionParameterENT> {
			private ExpressionENT expression;
			public ListForExpression(ExpressionENT expression) {
				this.expression = expression;
			}
			@Override public void initialize(ExpressionParameterENT entity) { entity.setExpression(expression); }
		}
		
		public static class ListForExpressionOrBoundParameterExpression extends EntityInitialValues<ExpressionParameterENT> {
			public ListForExpressionOrBoundParameterExpression() {
			}
			@Override public void initialize(ExpressionParameterENT entity) {}
		}
		
	}


	public ExpressionParameterServiceImpl() {}
	
	@Autowired
	ExpressionParameterDAO dao;
	@Override
	protected ExpressionParameterDAO getDAO() { return dao; }

	
	@Override
	public ExpressionParameter nullBO() { return ExpressionParameterBO.NULL; }
	@Override
	public ExpressionParameter getBO(ExpressionParameterENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (ExpressionParameter) serviceContext.getBO(entity); }
		return (ExpressionParameter) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public ExpressionParameter newBO(Long id, EntityInitialValues<ExpressionParameterENT> init) { 
		if (id == null) {
			return (ExpressionParameter) serviceContext.putBO(new ExpressionParameterBO(prepareNewEntity(new ExpressionParameterENT(), "ExpressionParameter.ID", init), PersistenceState.NEW)); 
		} else {
			return (ExpressionParameter) serviceContext.putBO(new ExpressionParameterBO(prepareNewEntity(new ExpressionParameterENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public ExpressionParameter newExpressionParameter() { return super.newBO(); }
	@Override
	public ExpressionParameter fetchExpressionParameter(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<ExpressionParameter> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<ExpressionParameter> listForExpression(Expression expression) { return new Lists.ForExpression(this, dao, expression); }


	@Override
	public ServiceList<ExpressionParameter> listForExpressionOrBoundToParameterExpression(ImplementedExpression expression, ImplementedExpression boundToParameterExpression) { 
		return new Lists.ForExpressionOrBoundParameterExpression(this, dao, expression, boundToParameterExpression); 
	}


}
