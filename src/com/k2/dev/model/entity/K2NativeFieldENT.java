package com.k2.dev.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.util.K2Type;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.bo.K2NativeFieldBO;

@Entity(name="K2NativeField")
@Table(name="K2NativeFields")
@PrimaryKeyJoinColumn(name="FieldID")
@DiscriminatorValue(value = "NATIVE")
public class K2NativeFieldENT extends K2FieldENT {
	
	public static class Types extends K2FieldENT.Types {
		public static enum NativeDateType implements K2Type {
			BOOLEAN(0, "Boolean", "The data type of this field is a boolean value."),
			INTEGER(1, "Small integer", "The data type of this field is a boolean value."),
			LONG(2, "Large integer", "The data type of this field is a long."),
			FLOAT(3, "Small decimal", "The data type of this field is a float."),
			DOUBLE(4, "Large decimal", "The data type of this field is a double."),
			DATE(5, "Date and time", "The data type of this field is a date."),
			STRING(6, "Text", "The data type of this field is a string.");
			
			private int id;
			private String name;
			private String description;
			
			NativeDateType(int id, String name, String description) { 
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
	public ServiceModel getServiceModel(PersistenceState state) { return new K2NativeFieldBO(this, state); }

	@Column(name="NativeType", nullable=false)
	@Enumerated(EnumType.STRING)
	protected Types.NativeDateType nativeType;
	public Types.NativeDateType getNativeType() { return nativeType; }
	public void setNativeType(Types.NativeDateType nativeType) { this.nativeType = nativeType; }
	
	@Column(name="MaxNumericValue", nullable=true)
	protected Integer maxNumericValue;
	public Integer getMaxNumericValue() { return maxNumericValue; }
	public void setMaxNumericValue(Integer maxNumericValue) { this.maxNumericValue = maxNumericValue; }
	
	@Column(name="MinNumericValue", nullable=true)
	protected Integer minNumericValue;
	public Integer getMinNumericValue() { return minNumericValue; }
	public void setMinNumericValue(Integer minNumericValue) { this.minNumericValue = minNumericValue; }

	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (K2NativeField.class.isAssignableFrom(source.getClass())) {
			K2NativeField clone = (K2NativeField)source;
			nativeType = clone.getNativeType();
			maxNumericValue = clone.getMaxNumericValue();
			minNumericValue = clone.getMinNumericValue();
		}
	}




}
