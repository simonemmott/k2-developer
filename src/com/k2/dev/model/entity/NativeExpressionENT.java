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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.google.common.base.Objects;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.ServiceModel;
import com.k2.common.util.K2Type;
import com.k2.dev.model.NativeExpression;
import com.k2.dev.model.bo.NativeExpressionBO;
import com.k2.dev.model.entity.K2NativeFieldENT.Types;

@Entity(name="NativeExpression")
@Table(name="NativeExpressions")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ExpressionID")
@DiscriminatorValue(value = "NATIVE")
public class NativeExpressionENT extends ExpressionENT {

    
    public static class Types {
        
        public static enum NativeExpressionTypes implements K2Type {
        
                BOOL_NVL(101, "Boolean Nvl", "Returns the value or the default if the value is null."),
                BOOL_IS_NULL(102, "Boolean is null", "Returns true if the value is null"),
                BOOL_IS_NOT_NULL(103, "Boolean is not null", "Returns true if the value is not null"),
                BOOL_NOT(104, "Boolean Not", "Returns true if the value is false"),
                BOOL_GT(105, "Boolean greater than", "Returns true if the first value is greater than the second value"),
                BOOL_GTE(106, "Boolean greater than or equal to", "Returns true if the first value is greater than or equal to the second value."),
                BOOL_LT(107, "Boolean less than", "Returns true if the first value is less than the second value"),
                BOOL_TLE(108, "Boolean less than or equal to", "Returns true if the first value is less than or equal to the second value."),
                BOOL_EQ(109, "Boolean equals", "Returns true if the first value is equal to the second value"),
                BOOL_NEQ(110, "Boolean not equal", "Returns true if the first value is not equal to the second value"),
        			INT_MULTIPLY(110, "Integer multiply", "Multiply all the parameters together"),
        			STR_CONCAT(111, "Concatenate", "Concatenate all the parameters together"),
				LNG_SUBTRACT(112, "Long subtract", "Subtract from the first parameter all the subsequent parameters");
            		
            
            private int id;
            private String name;
            private String description;
            
            NativeExpressionTypes(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new NativeExpressionBO(this, state); }
    
    
	@Column(name="NativeExpressionType", nullable=false)
	@Enumerated(EnumType.STRING)
	protected Types.NativeExpressionTypes nativeExpressionType;
	public Types.NativeExpressionTypes getNativeExpressionType() { return nativeExpressionType; }
	public void setNativeExpressionType(Types.NativeExpressionTypes nativeExpressionType) { this.nativeExpressionType = nativeExpressionType; }
	
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
	    	if (NativeExpression.class.isAssignableFrom(source.getClass())) {
	    		NativeExpression clone = (NativeExpression)source;
	    	}
    }
    
}
