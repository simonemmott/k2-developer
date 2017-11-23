package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.entity.BoundExpressionParameterENT;

public interface BoundExpressionParameterService extends EntityService<BoundExpressionParameterENT, Long, BoundExpressionParameter>{
	
	public BoundExpressionParameter newBoundExpressionParameter();
	public BoundExpressionParameter fetchBoundExpressionParameter(Long id);
	public ServiceList<BoundExpressionParameter> listImplementedExpression(ImplementedExpression implementedExpression);

}
