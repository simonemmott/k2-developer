package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ExpressionENT;

public interface ExpressionService extends EntityService<ExpressionENT, Long, Expression>{
	
	public Expression newExpression();
	public Expression fetchExpression(Long id);
	public ServiceList<Expression> listOfNativeOrServiceOrEntityExpressions(K2Service k2Service, K2Entity k2Entity);

}
