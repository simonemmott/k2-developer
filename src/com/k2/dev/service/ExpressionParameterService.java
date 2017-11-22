package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.bo.ExpressionBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ExpressionParameterENT;

public interface ExpressionParameterService extends EntityService<ExpressionParameterENT, Long, ExpressionParameter>{
	
	public ExpressionParameter newExpressionParameter();
	public ExpressionParameter fetchExpressionParameter(Long id);
	public ServiceList<ExpressionParameter> listForExpression(Expression expression);
	public ServiceList<ExpressionParameter> listForExpressionOrBoundToParameterExpression(ImplementedExpression expression, ImplementedExpression boundToParameterExpression);

}
