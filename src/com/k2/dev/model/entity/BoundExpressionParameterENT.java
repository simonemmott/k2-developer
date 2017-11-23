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
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.bo.BoundExpressionParameterBO;

@Entity(name="BoundExpressionParameter")
@Table(name="BoundExpressionParameters")
@Inheritance(strategy=InheritanceType.JOINED)
public class BoundExpressionParameterENT implements ID {

    
    public static class Types {
        
        public static enum ExpressionParameterBindingSource implements K2Type {
        
                LITERAL(118, "Literal", "The source of this parameter is a literal value"),
                EXPRESSION(119, "Expression", "The source of this parameter is another expression"),
                FIELD(120, "Field", "The source of this parameter is a field from the entity implementing the expression"),
                PARAMETER(121, "Parameter", "The source for this parameter is a parameter from the enclosing expression");
            
            private int id;
            private String name;
            private String description;
            
            ExpressionParameterBindingSource(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new BoundExpressionParameterBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=ImplementedExpressionENT.class, optional=false)
    @JoinColumn(name="Expression", nullable=false)
    protected ImplementedExpressionENT expression;
    public ImplementedExpressionENT getExpression() { return expression; }
    public void setExpression(ImplementedExpressionENT expression) { this.expression = expression; }
    
    
    @Column(name="BindingSource", insertable=true, updatable=true, nullable=false)
    @Enumerated(EnumType.STRING)
    protected Types.ExpressionParameterBindingSource bindingSource;
    public Types.ExpressionParameterBindingSource getBindingSource() { return bindingSource; }
    public void setBindingSource(Types.ExpressionParameterBindingSource bindingSource) { this.bindingSource = bindingSource; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=LiteralENT.class, optional=true)
    @JoinColumn(name="BoundLiteral", nullable=true)
    protected LiteralENT boundLiteral;
    public LiteralENT getBoundLiteral() { return boundLiteral; }
    public void setBoundLiteral(LiteralENT boundLiteral) { this.boundLiteral = boundLiteral; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=ImplementedExpressionENT.class, optional=true)
    @JoinColumn(name="BoundExpression", nullable=true)
    protected ImplementedExpressionENT boundExpression;
    public ImplementedExpressionENT getBoundExpression() { return boundExpression; }
    public void setBoundExpression(ImplementedExpressionENT boundExpression) { this.boundExpression = boundExpression; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2FieldENT.class, optional=true)
    @JoinColumn(name="BoundField", nullable=true)
    protected K2FieldENT boundField;
    public K2FieldENT getBoundField() { return boundField; }
    public void setBoundField(K2FieldENT boundField) { this.boundField = boundField; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=ExpressionParameterENT.class, optional=true)
    @JoinColumn(name="BoundParameter", nullable=true)
    protected ExpressionParameterENT boundParameter;
    public ExpressionParameterENT getBoundParameter() { return boundParameter; }
    public void setBoundParameter(ExpressionParameterENT boundParameter) { this.boundParameter = boundParameter; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=ExpressionParameterENT.class, optional=false)
    @JoinColumn(name="BindsParamter", nullable=false)
    protected ExpressionParameterENT bindsParamter;
    public ExpressionParameterENT getBindsParameter() { return bindsParamter; }
    public void setBindsParameter(ExpressionParameterENT bindsParamter) { this.bindsParamter = bindsParamter; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
	    	if (BoundExpressionParameter.class.isAssignableFrom(source.getClass())) {
	    		BoundExpressionParameter clone = (BoundExpressionParameter)source;
	            id = clone.getId();
	            expression = clone.getExpression().getEntity();
	            bindingSource = clone.getBindingSource();
	            boundLiteral = clone.getBoundLiteral().getEntity();
	            boundField = clone.getBoundField().getEntity();
	            boundParameter = clone.getBoundParameter().getEntity();
	    	}
    }
    
}
