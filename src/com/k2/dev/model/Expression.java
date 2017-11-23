package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ExpressionENT.Types;
import com.k2.dev.model.types.ExpressionType;

@SuppressWarnings({"rawtypes"})
public interface Expression  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public ExpressionENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public Expression Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// ReturnType field
	public Types.ReturnType getReturnType();
	public void setReturnType(Types.ReturnType returnType);

	// Alias field
	public String getAlias();
	public void setAlias(String alias);

	// ExpressionType discriminator field
	public Types.ExpressionType getExpressionType();
	
	// Lists -------
	// Parameters list
	public ServiceList<ExpressionParameter> getParameters();
	
}