package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.ExpressionParameterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.model.entity.BoundExpressionParameterENT.Types;

@SuppressWarnings({"rawtypes"})
public interface BoundExpressionParameter  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public BoundExpressionParameterENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public BoundExpressionParameter Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// Expression field
	public ImplementedExpression getExpression();
	public void setExpression(ImplementedExpression expression);

	// BindingSource field
	public Types.ExpressionParameterBindingSource getBindingSource();
	public void setBindingSource(Types.ExpressionParameterBindingSource bindingSource);

	// BoundLiteral field
	public Literal getBoundLiteral();
	public void setBoundLiteral(Literal boundLiteral);

	// BoundExpression field
	public ImplementedExpression getBoundExpression();
	public void setBoundExpression(ImplementedExpression boundExpression);

	// BoundField field
	public K2Field getBoundField();
	public void setBoundField(K2Field boundField);

	// BoundParameter field
	public ExpressionParameter getBoundParameter();
	public void setBoundParameter(ExpressionParameter boundParameter);
		
	// BindsParameter field
	public ExpressionParameter getBindsParameter();
	public void setBindsParameter(ExpressionParameter bindsParameter);
	
	// Lists --------
	// ImplementableExpressions list
	public ServiceList<ImplementedExpression> getImplementableExpressions();
	
	// Literals list
	public ServiceList<Literal> getLiterals();
	
	// BoundExpressions list
	public ServiceList<ImplementedExpression> getBoundExpressions();
	
	// BindableFields list
	public ServiceList<K2Field> getBindableFields();
	
	// ExpressionParameters list
	public ServiceList<ExpressionParameter> getExpressionParameters();
	
	// ImplementedExpressionParameters list
	public ServiceList<ExpressionParameter> getImplementedExpressionParameters();

}