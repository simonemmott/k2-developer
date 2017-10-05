package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.k2.common.identity.ID;

@Entity
@Table(name="K2Fields")
public class K2FieldENT implements ID {

	@Id
	@Column(name="ID", nullable=false)
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }

	@Column(name="Name", nullable=false, length=50)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
	@JoinColumn(name="EntityID", nullable=false)
	protected K2EntityENT k2Entity;
	public K2EntityENT getK2Entity() { return k2Entity; } 
	public void setK2Entity(K2EntityENT k2Entity) { this.k2Entity = k2Entity; }
	
	@Column(name="ColumnLength")
	protected Integer columnLength;
	public Integer getColumnLength() { return columnLength; }
	public void setColumnLength(Integer columnLength) { this.columnLength = columnLength; }

	@Column(name="DataType")
	protected String dataType;
	public String getDataType() { return dataType; }
	public void setDataType(String dataType) { this.dataType = dataType; }

	@Column(name="ColumnName")
	protected String columnName;
	public String getColumnName() { return columnName; }
	public void setColumnName(String columnName) { this.columnName = columnName; }

	@Column(name="Nullable")
	protected Boolean nullable;
	public Boolean getNullable() { return nullable; }
	public void setNullable(Boolean nullable) { this.nullable = nullable; }



}
