package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ExpressionENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ExpressionParameterService;
import com.k2.dev.service.ExpressionService;

@SuppressWarnings("rawtypes")
@Configurable
public class ExpressionBO extends GenericServiceModel implements ServiceModel, Expression {
	
	@Autowired(required=true)
	protected ExpressionService service;
	@Autowired(required=true)
	protected ExpressionParameterService expressionParameterService;
	@Override
	public EntityService getService() { return service; }
	
	public static Expression NULL = new ExpressionBO();
	public ExpressionBO() { super(null); }
	public ExpressionBO(PersistenceState state) { super(state); }
    public ExpressionBO(ExpressionENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.EXPRESSION; }
	
	public String getIdentity() { return getAlias(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Expression Null() { return NULL; }
	
	protected ExpressionENT entity;
	@Override
	public ExpressionENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
    public Types.ReturnType getReturnType() { if (isNull()) { return null; } return getEntity().getReturnType(); }
    @Override
	public void setReturnType(Types.ReturnType returnType) { if (isNull()) { return; } getEntity().setReturnType(returnType); changed(); }

    @Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	@Override
	public Types.ExpressionType getExpressionType() { if (isNull()) { return null; } return getEntity().getExpressionType(); }

	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new ExpressionENT(); }
		if (Expression.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Expression)source);
		}
	}
	
	// Lists ----------
	// Parameters list
	@Override
	public ServiceList<ExpressionParameter> getParameters() { return expressionParameterService.listForExpression(this); }


}
