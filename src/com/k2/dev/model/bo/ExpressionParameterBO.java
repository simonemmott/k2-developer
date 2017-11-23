package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.expressions.Ex;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.lib.Literals;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.entity.ExpressionParameterENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.ExpressionParameterENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.ExpressionParameterService;
import com.k2.dev.service.ExpressionService;
import com.k2.dev.service.K2ServiceService;

@SuppressWarnings("rawtypes")
@Configurable
public class ExpressionParameterBO extends GenericServiceModel implements ServiceModel, ExpressionParameter {
	
	@Autowired(required=true)
	protected ExpressionParameterService service;
	@Autowired(required=true)
	protected ExpressionService expressionService;
	@Override
	public EntityService getService() { return service; }
	
	public static ExpressionParameter NULL = new ExpressionParameterBO();
	public ExpressionParameterBO() { super(null); }
	public ExpressionParameterBO(PersistenceState state) { super(state); }
    public ExpressionParameterBO(ExpressionParameterENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.EXPRESSION_PARAMETER; }
	
	public String getIdentity() { return getAlias(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public ExpressionParameter Null() { return NULL; }
	
	protected ExpressionParameterENT entity;
	@Override
	public ExpressionParameterENT getEntity() { return entity; }
	
	// Fields ---------
	// ID field
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	// Expression field
	@Override
	public Expression getExpression() { if (isNull()) { return ExpressionBO.NULL; } return Nvl.nvl(expressionService.getBO(getEntity().getExpression()), ExpressionBO.NULL); }
	@Override
	public void setExpression(Expression expression){ if (isNull()) { return; } getEntity().setExpression(expression.getEntity()); changed(); }
	
	// DataType field
	@Override
	public Types.ParameterDataType getDataType() { if (isNull()) { return null; } return getEntity().getDataType(); }
	@Override
	public void setDataType(Types.ParameterDataType dataType) { if (isNull()) { return; } getEntity().setDataType(dataType); changed(); }
	
	// Alias field
	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	// Repeating field
	@Override
	public Boolean getRepeating() { if (isNull()) { return null; } return getEntity().getRepeating(); }
	@Override
	public void setRepeating(Boolean repeating) { if (isNull()) { return; } getEntity().setRepeating(repeating); changed(); }
	
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new ExpressionParameterENT(); }
		if (ExpressionParameter.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((ExpressionParameter)source);
		}
	}
	
	// Lists ---------
	// Expressions list
	@Override
	public ServiceList<Expression> getExpressions() { return expressionService.listAll(); }
	
	// Expressions ----------
	// JavaDataType expression
	@Override
	public String getJavaDataType() {
		return Ex.STRING.decode(
			getDataType(), 
			Types.ParameterDataType.BOOLEAN,
			Literals.booleanName,
			Types.ParameterDataType.DATE,
			Literals.dateName,
			Types.ParameterDataType.DOUBLE,
			Literals.doubleName,
			Types.ParameterDataType.FLOAT,
			Literals.floatName,
			Types.ParameterDataType.INTEGER,
			Literals.integerName,
			Types.ParameterDataType.LONG,
			Literals.longName,
			Types.ParameterDataType.STRING,
			Literals.stringName,
			Types.ParameterDataType.OBJECT,
			Literals.objectName
		);
	}

}
