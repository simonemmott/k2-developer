package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.NativeExpressionENT;
import com.k2.dev.model.entity.NativeExpressionENT.Types;;

@SuppressWarnings("rawtypes")
public interface NativeExpression  extends ServiceModel, Expression {

	// Service methods -----
	@Override public NativeExpressionENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public NativeExpression Null();
	
	// Fields ------
	// NativeExpressionType field
	public Types.NativeExpressionTypes getNativeExpressionType();
	public void setNativeExpressionType(Types.NativeExpressionTypes nativeExpressionType);

}