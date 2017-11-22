package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.BoundExpressionParameterBO;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.ImplementedExpressionENT;

public interface ImplementedExpressionService extends EntityService<ImplementedExpressionENT, Long, ImplementedExpression>  {

	public ImplementedExpression newImplementedExpression();
	public ImplementedExpression fetchImplementedExpression(Long id);
	public ServiceList<ImplementedExpression> listForServiceOnly(K2Service k2Service);
	public ServiceList<ImplementedExpression> listForServiceAndEntity(K2Service k2Service, K2Entity k2Entity);
	public ServiceList<ImplementedExpression> listForService(K2Service k2Service);
	public ServiceList<ImplementedExpression> listForServiceAndBoundToParameter(K2Service k2Service, BoundExpressionParameter boundExpressionParameter);


}
