package com.k2.dev.model.entity;

import java.util.Date;
import javax.persistence.Column;
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
import com.k2.dev.model.FieldSetMember;
import com.k2.dev.model.bo.FieldSetMemberBO;

@Entity(name="FieldSetMember")
@Table(name="FieldSetMembers")
@Inheritance(strategy=InheritanceType.JOINED)
public class FieldSetMemberENT implements ID {

    
    public static class Types {
    }
    
    
    @SuppressWarnings("rawtypes")
    public ServiceModel getServiceModel(PersistenceState state) { return new FieldSetMemberBO(this, state); }
    
    @Id
    @Column(name="ID", nullable=false)
    protected Long Id;
    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = Id; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2FieldSetENT.class, optional=false)
    @JoinColumn(name="FieldSet", nullable=false)
    protected K2FieldSetENT fieldSet;
	public K2FieldSetENT getFieldSet() { return fieldSet; }
	public void setFieldSet(K2FieldSetENT fieldSet) { this.fieldSet = fieldSet; }
    
    @Column(name="OrderInSet", nullable=false)
    protected Integer orderInSet;
    public Integer getOrderInSet() { return orderInSet; }
    public void setOrderInSet(Integer orderInSet) { this.orderInSet = orderInSet; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2FieldENT.class, optional=false)
    @JoinColumn(name="Member", nullable=false)
    protected K2FieldENT member;
    public K2FieldENT getMember() { return member; }
    public void setMember(K2FieldENT member) { this.member = member; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (FieldSetMember.class.isAssignableFrom(source.getClass())) {
    		FieldSetMember clone = (FieldSetMember)source;
            Id = clone.getId();
            fieldSet = clone.getFieldSet().getEntity();
            orderInSet = clone.getOrderInSet();
            member = clone.getMember().getEntity();
    	}
    }
}
