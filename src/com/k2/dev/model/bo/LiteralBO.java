package com.k2.dev.model.bo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.expressions.Ex;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.lib.Literals;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.LiteralENT.Types;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2ServiceService;
import com.k2.dev.service.LiteralService;

@SuppressWarnings("rawtypes")
@Configurable
public class LiteralBO extends GenericServiceModel implements ServiceModel, Literal {
	
	@Autowired(required=true)
	protected LiteralService service;
	@Autowired(required=true)
	protected K2ServiceService k2ServiceService;
	@Override
	public EntityService getService() { return service; }
	
	public static Literal NULL = new LiteralBO();
	public LiteralBO() { super(null); }
    public LiteralBO(PersistenceState state) { super(state); }
    public LiteralBO(LiteralENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.LITERAL; }
	
	@Override
	public String getIdentity() { return getAlias(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Literal Null() { return NULL; }
	
	protected LiteralENT entity;
	@Override
	public LiteralENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public K2Service getK2Service() { if (isNull()) { return K2ServiceBO.NULL; } return Nvl.nvl(k2ServiceService.getBO(getEntity().getK2Service()), K2ServiceBO.NULL); }
	@Override
	public void setK2Service(K2Service k2Service) { if (isNull()) { return; } getEntity().setK2Service(k2Service.getEntity()); changed(); }

	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	@Override
	public Types.LiteralDataType getDataType() { if (isNull()) { return null; } return getEntity().getDataType(); }
	@Override
	public void setDataType(Types.LiteralDataType dataType) { if (isNull()) { return; } getEntity().setDataType(dataType); changed(); }
	
	@Override
	public Long getNumericValue() { if (isNull()) { return null; } return getEntity().getNumericValue(); }
	@Override
	public void setNumericValue(Long numericValue) { if (isNull()) { return; } getEntity().setNumericValue(numericValue); changed(); }

	@Override
	public Double getDecimalValue() { if (isNull()) { return null; } return getEntity().getDecimalValue(); }
	@Override
	public void setDecimalValue(Double decimalValue) { if (isNull()) { return; } getEntity().setDecimalValue(decimalValue); changed(); }
	
	@Override
	public Boolean getBooleanValue() { if (isNull()) { return null; } return getEntity().getBooleanValue(); }
	@Override
	public void setBooleanValue(Boolean booleanValue) { if (isNull()) { return; } getEntity().setBooleanValue(booleanValue); changed(); }
	
	@Override
	public Date getDateValue() { if (isNull()) { return null; } return getEntity().getDateValue(); }
	@Override
	public void setDateValue(Date dateValue) { if (isNull()) { return; } getEntity().setDateValue(dateValue); changed(); }
	
	@Override
	public String getTextValue() { if (isNull()) { return null; } return getEntity().getTextValue(); }
	@Override
	public void setTextValue(String textValue) { if (isNull()) { return; } getEntity().setTextValue(textValue); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new LiteralENT(); }
		if (Literal.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Literal)source);
		}
	}
	
	// Lists ----------
	// Services list
	@Override
	public ServiceList<K2Service> getServices() { return k2ServiceService.listAll(); }
	
	// Expressions --------
	// JavaDataType expression
	@Override
	public String getJavaDataType() {
		return Ex.STRING.decode(
			this.getDataType(), 
			Types.LiteralDataType.BOOLEAN,
			Literals.booleanName,
			Types.LiteralDataType.DATE,
			Literals.dateName,
			Types.LiteralDataType.DECIMAL,
			Literals.doubleName,
			Types.LiteralDataType.NUMERIC,
			Literals.longName,
			Types.LiteralDataType.TEXT,
			Literals.stringName
		);
	}
	
	
}
