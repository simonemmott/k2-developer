package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ExpressionParameterENT.Types;
import com.k2.dev.model.entity.ExpressionParameterENT;

@SuppressWarnings({"rawtypes"})
public interface ExpressionParameter  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public ExpressionParameterENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public ExpressionParameter Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// Expression field
	public Expression getExpression();
	public void setExpression(Expression expression);

	// DataType field
	public Types.ParameterDataType getDataType();
	public void setDataType(Types.ParameterDataType dataType);

	// Alias field
	public String getAlias();
	public void setAlias(String alias);
	
	// Repeating field
	public Boolean getRepeating();
	public void setRepeating(Boolean repeating);
	
	// Lists ----------
	// Expressions list
	public ServiceList<Expression> getExpressions();
	
	// Expressions ------
	// JavaDataTypeExpression
	public String getJavaDataType();

	
}