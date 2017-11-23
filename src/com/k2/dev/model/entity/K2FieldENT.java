package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.k2.common.identity.ID;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.ServiceModel;
import com.k2.common.util.K2Type;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.bo.K2FieldBO;

@Entity(name="K2Field")
@Table(name="K2Fields")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="FieldType")
@DiscriminatorValue("UNDEFINED")
public class K2FieldENT implements ID {
	
	public static class Types {
		public static enum FieldType implements K2Type {
			UNDEFINED(0, "Unset", "The type of this field is not defined."),
			NATIVE(1, "Native", "This field is a native field. i.e a number, text, boolean etc."),
			EXPRESSION(2, "Expression", "This field is an expression."),
			LOOKUP(3, "Lookup", "This field is a lookup of a field over a link."),
			TYPE(4, "Type", "This field is a type."),
			LINKED(5, "Linked", "This field is a link to another entity.");
			
			private int id;
			private String name;
			private String description;
			
			FieldType(int id, String name, String description) { 
				this.id = id;
				this.name = name;
				this.description = description;
			}
			
			@Override
			public int getId() { return id; }
			@Override
			public String getName() { return this.name; }
			@Override
			public String getDescription() { return this.description; }

		}
	}
	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2FieldBO(this, state); }


	@Id
	@Column(name="ID", nullable=false)
	protected Long id;
	@Override
	public Long getId() { return id; }
	@Override
	public void setId(Long id) { this.id = id; }

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

	@Column(name="ColumnName")
	protected String columnName;
	public String getColumnName() { return columnName; }
	public void setColumnName(String columnName) { this.columnName = columnName; }

	@Column(name="Nullable")
	protected Boolean nullable;
	public Boolean getNullable() { return nullable; }
	public void setNullable(Boolean nullable) { this.nullable = nullable; }
	
	@Column(name="Alias")
	protected String alias;
	public String getAlias() { return alias; }
	public void setAlias(String alias) { this.alias = alias; }
	
	@Column(name="FieldType", insertable=false, updatable=false, nullable=false)
	@Enumerated(EnumType.STRING)
	protected Types.FieldType fieldType;
	public Types.FieldType getFieldType() { return fieldType; }


	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		if (K2Field.class.isAssignableFrom(source.getClass())) {
			K2Field clone = (K2Field)source;
			id = clone.getId();
			name = clone.getName();
			k2Entity = clone.getK2Entity().getEntity();
			columnLength = clone.getColumnLength();
			columnName = clone.getColumnName();
			nullable = clone.getNullable();
			alias = clone.getAlias();
		}
	}




}
