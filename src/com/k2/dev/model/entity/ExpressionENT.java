package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.google.common.base.Objects;
import com.k2.common.identity.ID;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.ServiceModel;
import com.k2.common.util.K2Type;
import com.k2.dev.model.Expression;
import com.k2.dev.model.bo.ExpressionBO;
import com.k2.dev.model.entity.ComponentENT.Types;

@Entity(name="Expression")
@Table(name="Expressions")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="ExpressionType")
public class ExpressionENT implements ID {
	    
    public static class Types {
        
        public static enum ReturnType implements K2Type {
        
                BOOLEAN(91, "Boolean", "This expression returns a boolean value."),
                INTEGER(92, "Small integer", "This expression returns an Integer"),
                LONG(93, "Large integer", "This expression returns a Long."),
                FLOAT(94, "Small decimal", "This expression returns a Float."),
                DOUBLE(95, "Large decimal", "This expression returns a Double."),
                DATE(96, "Date and time", "This expression returns a Date."),
                STRING(97, "Text", "This expression returns a String.");
            
            private int id;
            private String name;
            private String description;
            
            ReturnType(int id, String name, String description) { 
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
        
        
        public static enum ExpressionType implements K2Type {
        
                NATIVE(98, "Native", "This expression is a native expression defined by K2."),
                IMPLEMENTED(99, "Implemented", "This expression implements another expression, either Native or Implemented");
            
            private int id;
            private String name;
            private String description;
            
            ExpressionType(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new ExpressionBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    
    @Column(name="ReturnType", insertable=true, updatable=true, nullable=false)
    @Enumerated(EnumType.STRING)
    protected Types.ReturnType returnType;
    public Types.ReturnType getReturnType() { return returnType; }
    public void setReturnType(Types.ReturnType returnType) { this.returnType = returnType; }
    
    @Column(name="Alias", nullable=false)
    protected String alias;
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    
	@Column(name="ExpressionType", insertable=false, updatable=false, nullable=false)
	@Enumerated(EnumType.STRING)
	protected Types.ExpressionType expressionType;
	public Types.ExpressionType getExpressionType() { return expressionType; }
        
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (Expression.class.isAssignableFrom(source.getClass())) {
    		Expression clone = (Expression)source;
            id = clone.getId();
            returnType = clone.getReturnType();
            alias = clone.getAlias();
    	}
    }
    
}
