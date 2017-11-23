package com.k2.dev.model;

import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.entity.K2EntityENT.Types;;

@SuppressWarnings("rawtypes")
public interface ImplementedExpression  extends ServiceModel, Expression {

	// Service methods -----
	@Override public ImplementedExpressionENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public ImplementedExpression Null();
	
	// Fields ------
	// Implements field
	public Expression getImplementsExpression();
	public void setImplementsExpression(Expression implementsExpression);
	
	// K2Service field
	public K2Service getK2Service();
	public void setK2Service(K2Service k2Service);
	
	// K2Entity field
	public K2Entity getK2Entity();
	public void setK2Entity(K2Entity k2Entity);

	// BoundExpressionParameter field
	public BoundExpressionParameter getBoundToParameter();
	public void setBoundToParameter(BoundExpressionParameter boundExpressionParameter);
	
	// Lists -------
	// ImplementedExpressions list
	public ServiceList<Expression> getImplementableExpressions();
	
	// Services list
	public ServiceList<K2Service> getServices();
	
	// Entities list
	public ServiceList<K2Entity> getEntities();	
	
	// ParameterBindings list
	public ServiceList<BoundExpressionParameter> getParameterBindings();
	
	// BoundExpressionParameters list
	public ServiceList<BoundExpressionParameter> getBoundExpressionParameters();
	
	// Expressions ---------
	// ImplementedExpressionTypeAlias expression
	public String getImplementedExpressionTypeAlias();
	
	// JavaDataType expression
	public String getJavaDataType();

}