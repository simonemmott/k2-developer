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
import com.google.common.base.Objects;
import com.k2.common.identity.ID;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.ServiceModel;
import com.k2.common.util.K2Type;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.bo.ExpressionParameterBO;

@Entity(name="ExpressionParameter")
@Table(name="ExpressionParameters")
@Inheritance(strategy=InheritanceType.JOINED)
public class ExpressionParameterENT implements ID {

    
    public static class Types {
        
        public static enum ParameterDataType implements K2Type {
        
                BOOLEAN(111, "Boolean", "The parameter is a Boolean"),
                INTEGER(112, "Short integer", "This parameter is an Integer"),
                LONG(113, "Large integer", "This parameter is a Long"),
                FLOAT(114, "Small decimal", "This parameter is a Float"),
                DOUBLE(115, "Large decimal", "This parameter is a Double"),
                DATE(116, "Date and time", "This parameter is a Date"),
                STRING(117, "Text", "This parameter is a String"),
            		OBJECT(117, "Unknown", "This parameter is an Object");
            
            private int id;
            private String name;
            private String description;
            
            ParameterDataType(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new ExpressionParameterBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=ExpressionENT.class, optional=false)
    @JoinColumn(name="Expression", nullable=false)
    protected ExpressionENT expression;
    public ExpressionENT getExpression() { return expression; }
    public void setExpression(ExpressionENT expression) { this.expression = expression; }
    
    
    @Column(name="DataType", insertable=true, updatable=true, nullable=false)
    @Enumerated(EnumType.STRING)
    protected Types.ParameterDataType dataType;
    public Types.ParameterDataType getDataType() { return dataType; }
    public void setDataType(Types.ParameterDataType dataType) { this.dataType = dataType; }
    
    @Column(name="Alias", nullable=true)
    protected String alias;
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    
    @Column(name="Repeating", nullable=false)
    protected Boolean repeating;
    public Boolean getRepeating() { return repeating; }
    public void setRepeating(Boolean repeating) { this.repeating = repeating; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (ExpressionParameter.class.isAssignableFrom(source.getClass())) {
    		ExpressionParameter clone = (ExpressionParameter)source;
            id = clone.getId();
            expression = clone.getExpression().getEntity();
            dataType = clone.getDataType();
            alias = clone.getAlias();
            repeating = clone.getRepeating();
    	}
    }
    
}
