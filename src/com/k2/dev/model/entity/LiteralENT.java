package com.k2.dev.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.FloatUtil;
import com.k2.common.util.IntegerUtil;
import com.k2.common.util.K2Type;
import com.k2.common.util.DateUtil;
import com.k2.common.util.StringUtil;

@Entity
@Table(name="Literals")
public class LiteralENT implements ID {
	
	public static class Types {
		public static enum DataType implements K2Type {
			NUMERIC(0, "Numeric", "This literal value is a numeric (integer) value."),
			DECIMAL(1, "Decimal", "This literal value is a decimal value."),
			BOOLEAN(2, "Boolean", "This literal value is a boolean value."),
			DATE(3, "Date", "This literal value is a date value."),
			TEXT(4, "Text", "This literal value is a text value.");
			
			private int id;
			private String name;
			private String description;
			
			DataType(int id, String name, String description) { 
				this.id = id; 
				this.name = name;
				this.description = description;
			}
			
			@Override
			public int getId() { return this.id; }
			@Override
			public String getName() { return this.name; }
			@Override
			public String getDescription() { return this.description; }
		}
	}

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final LiteralENT other = (LiteralENT) obj;
        return Objects.equal(this.id, other.getID());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
        
	@Id
	@Expose(serialize=true)
	@Column(name="ID")
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }

	@Expose(serialize=true)
	@Column(name="DataType")
	protected Types.DataType dataType;
	public Types.DataType getDataType() { return dataType; }
	public void setDataType(Types.DataType dataType) { this.dataType = dataType; }

	@Expose(serialize=true)
	@Column(name="NumericValue")
	protected Integer numericValue;
	public Integer getNumericValue() { return numericValue; }
	public void setNumericValue(Integer numericValue) { this.numericValue = numericValue; }

	@Expose(serialize=true)
	@Column(name="DecimalValue")
	protected Float decimalValue;
	public Float getDecimalValue() { return decimalValue; }
	public void setDecimalValue(Float decimalValue) { this.decimalValue = decimalValue; }

	@Expose(serialize=true)
	@Column(name="BooleanValue")
	protected Boolean booleanValue;
	public Boolean getBooleanValue() { return booleanValue; }
	public void setBooleanValue(Boolean booleanValue) { this.booleanValue = booleanValue; }

	@Expose(serialize=true)
	@Column(name="DateValue")
	protected Date dateValue;
	public Date getDateValue() { return dateValue; }
	public void setDateValue(Date dateValue) { this.dateValue = dateValue; }

	@Expose(serialize=true)
	@Column(name="TextValue")
	protected String textValue;
	public String getTextValue() { return textValue; }
	public void setTextValue(String textValue) { this.textValue = textValue; }

	public Integer toNumber() {
		switch(dataType) {
		case NUMERIC:
			return numericValue;
		case DECIMAL:
			return FloatUtil.toIntger(decimalValue);
		case BOOLEAN:
			return BooleanUtil.toIntger(booleanValue);
		case DATE:
			return DateUtil.toIntger(dateValue);
		case TEXT:
			return StringUtil.toInteger(textValue);
		}
		return null;
	}

	public Float toDecimal() {
		switch(dataType) {
		case NUMERIC:
			return IntegerUtil.toFloat(numericValue);
		case DECIMAL:
			return decimalValue;
		case BOOLEAN:
			return BooleanUtil.toFloat(booleanValue);
		case DATE:
			return DateUtil.toFloat(dateValue);
		case TEXT:
			return StringUtil.toFloat(textValue);
		}
		return null;
	}

	public Boolean toBoolean() {
		switch(dataType) {
		case NUMERIC:
			return IntegerUtil.toBoolean(numericValue);
		case DECIMAL:
			return FloatUtil.toBoolean(decimalValue);
		case BOOLEAN:
			return booleanValue;
		case DATE:
			return DateUtil.toBoolean(dateValue);
		case TEXT:
			return StringUtil.toBoolean(textValue);
		}
		return null;
	}

	public Date toDate() {
		switch(dataType) {
		case NUMERIC:
			return IntegerUtil.toDate(numericValue);
		case DECIMAL:
			return FloatUtil.toDate(decimalValue);
		case BOOLEAN:
			return BooleanUtil.toDate(booleanValue);
		case DATE:
			return dateValue;
		case TEXT:
			return StringUtil.toDate(textValue);
		}
		return null;
	}

	@Override
	public String toString() {
		switch(dataType) {
		case NUMERIC:
			return IntegerUtil.toString(numericValue);
		case DECIMAL:
			return FloatUtil.toString(decimalValue);
		case BOOLEAN:
			return BooleanUtil.toString(booleanValue);
		case DATE:
			return DateUtil.toString(dateValue);
		case TEXT:
			return textValue;
		}
		return null;
	}
}
