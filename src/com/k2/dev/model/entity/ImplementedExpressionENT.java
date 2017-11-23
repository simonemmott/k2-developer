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
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.bo.ImplementedExpressionBO;

@Entity(name="ImplementedExpression")
@Table(name="ImplementedExpressions")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ExpressionID")//, foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
@DiscriminatorValue(value = "IMPLEMENTED")
public class ImplementedExpressionENT extends ExpressionENT {

    
    public static class Types {
    }
    
    
    @SuppressWarnings("rawtypes")
    public ServiceModel getServiceModel(PersistenceState state) { return new ImplementedExpressionBO(this, state); }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=ExpressionENT.class, optional=false)
    @JoinColumn(name="Implements", nullable=false)//, foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected ExpressionENT implementsExpression;
    public ExpressionENT getImplementsExpression() { return implementsExpression; }
    public void setImplementsExpression(ExpressionENT implementsExpression) { this.implementsExpression = implementsExpression; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2ServiceENT.class, optional=false)
    @JoinColumn(name="Service", nullable=false)//, foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected K2ServiceENT k2Service;
    public K2ServiceENT getK2Service() { return k2Service; }
    public void setK2Service(K2ServiceENT k2Service) { this.k2Service = k2Service; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=true)
    @JoinColumn(name="Entity", nullable=true)//, foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected K2EntityENT k2Entity;
    public K2EntityENT getK2Entity() { return k2Entity; }
    public void setK2Entity(K2EntityENT k2Entity) { this.k2Entity = k2Entity; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=BoundExpressionParameterENT.class, optional=true)
    @JoinColumn(name="BoundToParameter", nullable=true)//, foreignKey=@ForeignKey(name="FK_BoundToParameter"))
    protected BoundExpressionParameterENT boundToParameter;
    public BoundExpressionParameterENT getBoundToParameter() { return boundToParameter; }
    public void setBoundToParameter(BoundExpressionParameterENT boundToParameter) { this.boundToParameter = boundToParameter; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (ImplementedExpression.class.isAssignableFrom(source.getClass())) {
    		ImplementedExpression clone = (ImplementedExpression)source;
    			implementsExpression = clone.getImplementsExpression().getEntity();
    			k2Service = clone.getK2Service().getEntity();
            k2Entity = clone.getK2Entity().getEntity();
            boundToParameter = clone.getBoundToParameter().getEntity();
    	}
    }
    
}
