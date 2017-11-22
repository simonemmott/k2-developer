package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.NativeExpression;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.NativeExpressionENT;

public interface NativeExpressionService extends EntityService<NativeExpressionENT, Long, NativeExpression>  {

	public NativeExpression newNativeExpression();
	public NativeExpression fetchNativeExpression(Long id);


}
