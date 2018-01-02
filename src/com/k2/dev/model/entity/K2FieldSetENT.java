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
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.bo.K2FieldSetBO;

@Entity(name="K2FieldSet")
@Table(name="K2FIELDSETS")
@Inheritance(strategy=InheritanceType.JOINED)
public class K2FieldSetENT implements ID {

    
    public static class Types {
    }
    
    
    @SuppressWarnings("rawtypes")
    public ServiceModel getServiceModel(PersistenceState state) { return new K2FieldSetBO(this, state); }
    
    @Id
    @Column(name="ID", nullable=false)
    protected Long Id;
    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = Id; }
    
    @Column(name="Name", nullable=false)
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2FieldENT.class, optional=false)
    @JoinColumn(name="Component", nullable=false)
    protected ComponentENT component;
    public ComponentENT getComponent() { return component; }
    public void setComponent(ComponentENT component) { this.component = component; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (K2FieldSet.class.isAssignableFrom(source.getClass())) {
    		K2FieldSet clone = (K2FieldSet)source;
            Id = clone.getId();
            name = clone.getName();
            component = clone.getComponent().getEntity();
    	}
    }
    
}
