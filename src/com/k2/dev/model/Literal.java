package com.k2.dev.model;

import java.util.Date;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.LiteralENT;

@SuppressWarnings("rawtypes")
public interface Literal extends ServiceModel {
	
	@Override public LiteralENT getEntity();
	@Override public Literal Null();

	public Long getID();

	public void setID(Long id);

	public LiteralENT.Types.DataType getDataType();

	public void setDataType(LiteralENT.Types.DataType dataType);

	public Integer getNumericValue();

	public void setNumericValue(Integer numericValue);

	public Float getDecimalValue();

	public void setDecimalValue(Float decimalValue);

	public Boolean getBooleanValue();

	public void setBooleanValue(Boolean booleanValue);

	public Date getDateValue();

	public void setDateValue(Date dateValue);

	public String getTextValue();

	public void setTextValue(String textValue);

	public Integer toNumber();

	public Float toDecimal();

	public Boolean toBoolean();

	public Date toDate();

	@Override
	public String toString();

}