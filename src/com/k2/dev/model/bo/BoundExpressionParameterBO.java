package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.BoundExpressionParameterENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.BoundExpressionParameterService;
import com.k2.dev.service.ExpressionParameterService;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.LiteralService;

@SuppressWarnings("rawtypes")
@Configurable
public class BoundExpressionParameterBO extends GenericServiceModel implements ServiceModel, BoundExpressionParameter {
	
	@Autowired(required=true)
	protected BoundExpressionParameterService service;
	@Autowired(required=true)
	protected ImplementedExpressionService implementedExpressionService;
	@Autowired(required=true)
	protected LiteralService literalService;
	@Autowired(required=true)
	protected K2FieldService k2FieldService;
	@Autowired(required=true)
	protected ExpressionParameterService expressionParameterService;
	@Override
	public EntityService getService() { return service; }
	
	public static BoundExpressionParameter NULL = new BoundExpressionParameterBO();
	public BoundExpressionParameterBO() { super(null); }
	public BoundExpressionParameterBO(PersistenceState state) { super(state); }
    public BoundExpressionParameterBO(BoundExpressionParameterENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.BOUND_EXPRESSION_PARAMETER; }
	
	public String getIdentity() { return getId().toString(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public BoundExpressionParameter Null() { return NULL; }
	
	protected BoundExpressionParameterENT entity;
	@Override
	public BoundExpressionParameterENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	// Expression field
	@Override
	public ImplementedExpression getExpression() { if (isNull()) { return ImplementedExpressionBO.NULL; } return Nvl.nvl(implementedExpressionService.getBO(getEntity().getExpression()), ImplementedExpressionBO.NULL); }
	@Override
	public void setExpression(ImplementedExpression expression) { if (isNull()) { return; } getEntity().setExpression(expression.getEntity()); changed(); }
	
	@Override
	public Types.ExpressionParameterBindingSource getBindingSource() { if (isNull()) { return null; } return getEntity().getBindingSource(); }
	@Override
	public void setBindingSource(Types.ExpressionParameterBindingSource bindingSource) { if (isNull()) { return; } getEntity().setBindingSource(bindingSource); changed(); }
	
	// BoundLiteral field
	@Override
	public Literal getBoundLiteral() { if (isNull()) { return LiteralBO.NULL; } return Nvl.nvl(literalService.getBO(getEntity().getBoundLiteral()), LiteralBO.NULL); }
	@Override
	public void setBoundLiteral(Literal boundLiteral) { if (isNull()) { return; } getEntity().setBoundLiteral(boundLiteral.getEntity()); changed(); }
	
	// BoundExpression field
	@Override
	public ImplementedExpression getBoundExpression() { if (isNull()) { return ImplementedExpressionBO.NULL; } return Nvl.nvl(implementedExpressionService.getBO(getEntity().getBoundExpression()), ImplementedExpressionBO.NULL); }
	@Override
	public void setBoundExpression(ImplementedExpression boundExpression) { if (isNull()) { return; } getEntity().setBoundExpression(boundExpression.getEntity()); changed(); }
	
	// BoundField field
	@Override
	public K2Field getBoundField() { if (isNull()) { return K2FieldBO.NULL; } return Nvl.nvl(k2FieldService.getBO(getEntity().getBoundField()), K2FieldBO.NULL); }
	@Override
	public void setBoundField(K2Field boundField) { if (isNull()) { return; } getEntity().setBoundField(boundField.getEntity()); changed(); }
	
	// BoundParameter field
	@Override
	public ExpressionParameter getBoundParameter() { if (isNull()) { return ExpressionParameterBO.NULL; } return Nvl.nvl(expressionParameterService.getBO(getEntity().getBoundParameter()), ExpressionParameterBO.NULL); }
	@Override
	public void setBoundParameter(ExpressionParameter boundParameter) { if (isNull()) { return; } getEntity().setBoundParameter(boundParameter.getEntity()); changed(); }
	
	// BoundParameter field
	@Override
	public ExpressionParameter getBindsParameter() { if (isNull()) { return ExpressionParameterBO.NULL; } return Nvl.nvl(expressionParameterService.getBO(getEntity().getBindsParameter()), ExpressionParameterBO.NULL); }
	@Override
	public void setBindsParameter(ExpressionParameter bindsParameter) { if (isNull()) { return; } getEntity().setBindsParameter(bindsParameter.getEntity()); changed(); }
	

	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new BoundExpressionParameterENT(); }
		if (BoundExpressionParameter.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((BoundExpressionParameter)source);
		}
	}
	
	// Lists ------
	// ImplementableExpressions list
	@Override
	public ServiceList<ImplementedExpression> getImplementableExpressions() { return implementedExpressionService.listAll(); }
	
	// Literals list
	@Override
	public ServiceList<Literal> getLiterals() { return literalService.listForService(getExpression().getK2Service()); }
	
	// BoundExpressions list
	@Override
	public ServiceList<ImplementedExpression> getBoundExpressions() { return implementedExpressionService.listForServiceAndBoundToParameter(getExpression().getK2Service(), this); }
	
	// BindableFields list
	@Override
	public ServiceList<K2Field> getBindableFields() { return k2FieldService.listForEntity(getExpression().getK2Entity()); }
	
	// ExpressionParameters list
	@Override
	public ServiceList<ExpressionParameter> getExpressionParameters() { return expressionParameterService.listForExpressionOrBoundToParameterExpression(getExpression(), getExpression().getBoundToParameter().getExpression()); }
	
	// ImplementedExpressionParameters list
	@Override
	public ServiceList<ExpressionParameter> getImplementedExpressionParameters() { return expressionParameterService.listForExpression(getExpression().getImplementsExpression()); }

}
