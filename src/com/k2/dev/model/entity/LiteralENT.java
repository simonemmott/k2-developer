package com.k2.dev.model.entity;

import java.util.Date;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.google.common.base.Objects;
import com.k2.common.identity.ID;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.ServiceModel;
import com.k2.common.util.K2Type;
import com.k2.dev.model.Literal;
import com.k2.dev.model.bo.LiteralBO;

@Entity(name="Literal")
@Table(name="Literals")
@Inheritance(strategy=InheritanceType.JOINED)
public class LiteralENT implements ID {

    
    public static class Types {
        
        public static enum LiteralDataType implements K2Type {
        
                NUMERIC(131, "Numeric", "This literal value is a numeric (integer) value."),
                DECIMAL(132, "Decimal", "This literal value is a decimal value."),
                BOOLEAN(133, "Boolean", "This literal value is a boolean value."),
                DATE(134, "Date", "This literal value is a date value."),
                TEXT(135, "Text", "This literal value is a text value.");
            
            private int id;
            private String name;
            private String description;
            
            LiteralDataType(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new LiteralBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2ServiceENT.class, optional=false)
    @JoinColumn(name="K2Service", nullable=false)
    protected K2ServiceENT k2Service;
    public K2ServiceENT getK2Service() { return k2Service; }
    public void setK2Service(K2ServiceENT k2Service) { this.k2Service = k2Service; }
    
    
    @Column(name="DataType", insertable=true, updatable=true, nullable=false)
    @Enumerated(EnumType.STRING)
    protected Types.LiteralDataType dataType;
    public Types.LiteralDataType getDataType() { return dataType; }
    public void setDataType(Types.LiteralDataType dataType) { this.dataType = dataType; }
    
    @Column(name="NumericValue", nullable=true)
    protected Long numericValue;
    public Long getNumericValue() { return numericValue; }
    public void setNumericValue(Long numericValue) { this.numericValue = numericValue; }
    
    @Column(name="DecimalValue", nullable=true)
    protected Double decimalValue;
    public Double getDecimalValue() { return decimalValue; }
    public void setDecimalValue(Double decimalValue) { this.decimalValue = decimalValue; }
    
    @Column(name="BooleanValue", nullable=true)
    protected Boolean booleanValue;
    public Boolean getBooleanValue() { return booleanValue; }
    public void setBooleanValue(Boolean booleanValue) { this.booleanValue = booleanValue; }
    
    @Column(name="DateValue", nullable=true)
    protected Date dateValue;
    public Date getDateValue() { return dateValue; }
    public void setDateValue(Date dateValue) { this.dateValue = dateValue; }
    
    @Column(name="TextValue", nullable=true)
    protected String textValue;
    public String getTextValue() { return textValue; }
    public void setTextValue(String textValue) { this.textValue = textValue; }
    
    @Column(name="Alias", nullable=false)
    protected String alias;
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (Literal.class.isAssignableFrom(source.getClass())) {
    		Literal clone = (Literal)source;
            id = clone.getId();
            k2Service = clone.getK2Service().getEntity();
            dataType = clone.getDataType();
            numericValue = clone.getNumericValue();
            decimalValue = clone.getDecimalValue();
            booleanValue = clone.getBooleanValue();
            dateValue = clone.getDateValue();
            textValue = clone.getTextValue();
            alias = clone.getAlias();
    	}
    }
    
}
