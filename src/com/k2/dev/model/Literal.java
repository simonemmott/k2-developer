package com.k2.dev.model;

import java.util.Date;

import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.model.entity.LiteralENT.Types;

@SuppressWarnings({"rawtypes"})
public interface Literal extends ServiceModel {

    // Service methods --------
    @Override
    public LiteralENT getEntity();
    @Override
    public boolean isNull();
    @Override
    public Literal Null();
    
    // Fields ---------
    // Id field
    public Long getId();
    public void setId(Long id);
    
    // K2Service field
    public K2Service getK2Service();
    public void setK2Service(K2Service k2Service);
    
    // Alias field
	public String getAlias();
	public void setAlias(String alias);
	
    // DataType field
    public Types.LiteralDataType getDataType();
    public void setDataType(Types.LiteralDataType dataType);
    
    // NumericValue field
    public Long getNumericValue();
    public void setNumericValue(Long numericValue);
    
    // DecimalValue field
    public Double getDecimalValue();
    public void setDecimalValue(Double decimalValue);
    
    // BooleanValue field
    public Boolean getBooleanValue();
    public void setBooleanValue(Boolean booleanValue);
    
    // DateValue field
    public Date getDateValue();
    public void setDateValue(Date dateValue);
    
    // TextValue field
    public String getTextValue();
    public void setTextValue(String textValue);
    
    // Lists ---------
    // Services list
	public ServiceList<K2Service> getServices();
	
	// Expressions ----------
	// JavaDataType expression
	public String getJavaDataType();
    
}
