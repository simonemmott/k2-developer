package com.k2.dev.model.bo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.LiteralService;

@SuppressWarnings("rawtypes")
@Configurable
public class LiteralBO extends GenericServiceModel implements ServiceModel, Literal {
	
	@Autowired(required=true)
	protected LiteralService service;
	@Override
	public EntityService getService() { return service; }
	
	public static Literal NULL = new LiteralBO();
	public LiteralBO() { super(null); }
    public LiteralBO(PersistenceState state) { super(state); }
    public LiteralBO(LiteralENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.LITERAL; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Literal Null() { return NULL; }
	
	private LiteralENT entity;
	@Override
	public LiteralENT getEntity() { return entity; }
	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public LiteralENT.Types.DataType getDataType() { if (isNull()) { return null; } return entity.getDataType(); }
	@Override
	public void setDataType(LiteralENT.Types.DataType dataType) { if (isNull()) { return; } entity.setDataType(dataType); changed(); }
	
	@Override
	public Integer getNumericValue() { if (isNull()) { return null; } return entity.getNumericValue(); }
	@Override
	public void setNumericValue(Integer numericValue) { if (isNull()) { return; } entity.setNumericValue(numericValue); changed(); }

	@Override
	public Float getDecimalValue() { if (isNull()) { return null; } return entity.getDecimalValue(); }
	@Override
	public void setDecimalValue(Float decimalValue) { if (isNull()) { return; } entity.setDecimalValue(decimalValue); changed(); }
	
	@Override
	public Boolean getBooleanValue() { if (isNull()) { return null; } return entity.getBooleanValue(); }
	@Override
	public void setBooleanValue(Boolean booleanValue) { if (isNull()) { return; } entity.setBooleanValue(booleanValue); changed(); }
	
	@Override
	public Date getDateValue() { if (isNull()) { return null; } return entity.getDateValue(); }
	@Override
	public void setDateValue(Date dateValue) { if (isNull()) { return; } entity.setDateValue(dateValue); changed(); }
	
	@Override
	public String getTextValue() { if (isNull()) { return null; } return entity.getTextValue(); }
	@Override
	public void setTextValue(String textValue) { if (isNull()) { return; } entity.setTextValue(textValue); changed(); }
	
	@Override
	public Integer toNumber() { return entity.toNumber(); }
	@Override
	public Float toDecimal() { return entity.toDecimal(); }
	@Override
	public Boolean toBoolean() { return entity.toBoolean(); }
	@Override
	public Date toDate() { return entity.toDate(); }
	@Override
	public String toString() { return entity.toString(); }
	

	
}
