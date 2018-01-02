package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2FieldENT.Types;

@SuppressWarnings("rawtypes")
public interface K2Field  extends ServiceModel, ID {
	
	// Service methods --------
	@Override
	public K2FieldENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public K2Field Null();

	// Fields ---------
	// ID field
	public Long getId();
	public void setId(Long id);

	// Alias field
	public String getAlias();
	public void setAlias(String alias);

	// Name field
	public String getName();
	public void setName(String name);
	
	// K2Entity field
	public K2Entity getK2Entity();
	public void setK2Entity(K2Entity k2Entity);

	// ColumnLength field
	public Integer getColumnLength();
	public void setColumnLength(Integer length);

	// ColumnName field
	public String getColumnName();
	public void setColumnName(String columnName);

	// Nullable field
	public Boolean getNullable();
	public void setNullable(Boolean nullable);
	
	// FieldType discriminator field
	public Types.FieldType getFieldType();

	// Enabled field
	public Boolean getEnabled();
	public void setEnabled(Boolean enabled);

	// TopCaption field
	public String getTopCaption();
	public void setTopCaption(String topCaption);
	
	// LeftCaption field
	public String getLeftCaption();
	public void setLeftCaption(String leftCaption);
	
	// RightCaption field
	public String getRightCaption();
	public void setRightCaption(String rightCaption);

	// DisplayedSize field
	public Integer getDisplayedSize();
	public void setDisplayedSize(Integer displayedSize);

	// Lists ---------
	// Entities list
	public ServiceList<K2Entity> getEntities();

	// Expressions ----------
	// IsLinked expression
	public Boolean getIsLinked();
	
	// InitialUpperAlias
	public String getInitialUpperAlias();
	
	// DataType
	public String getDataType();
	
	// CanonicalDataType
	public String getCanonicalDataType();
	
	// IsDiscriminator
	public Boolean getIsDiscriminator();
	
	// Required expression
	public Boolean getRequired();

}