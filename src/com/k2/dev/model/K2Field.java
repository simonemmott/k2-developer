package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2FieldENT;

@SuppressWarnings("rawtypes")
public interface K2Field  extends ServiceModel {
	
	@Override
	public K2FieldENT getEntity();

	@Override
	public boolean isNull();

	@Override
	public K2Field Null();

	public Long getID();

	public void setID(Long id);

	public String getName();

	public void setName(String name);

	public Integer getColumnLength();
	public void setColumnLength(Integer length);

	public String getDataType();
	public void setDataType(String dataType);

	public String getColumnName();
	public void setColumName(String columnName);

	public Boolean getNullable();
	public void setNullable(Boolean nullable);

}