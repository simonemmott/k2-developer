package com.k2.dev.model;

import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT.Types;

@SuppressWarnings("rawtypes")
public interface K2NativeField  extends ServiceModel, K2Field {
	
	@Override
	public K2NativeFieldENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2NativeField Null();

	// Fields ---------
	// NativeType field
	public Types.NativeDateType getNativeType();
	public void setNativeType(Types.NativeDateType nativeType);
	
	// MaxNumericValue field
	public Integer getMaxNumericValue();
	public void setMaxNumericValue(Integer maxNumericValue);

	// MinNumericValue field
	public Integer getMinNumericValue();
	public void setMinNumericValue(Integer minNumericValue);


	public String getDataType();
	
	public String getCononicalDataType();


	

}