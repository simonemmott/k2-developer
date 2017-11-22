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
import com.k2.dev.model.K2List;
import com.k2.dev.model.bo.K2ListBO;

@Entity(name="K2List")
@Table(name="K2Lists")
@Inheritance(strategy=InheritanceType.JOINED)
public class K2ListENT implements ID {

    
    public static class Types {
    }
    
    
    @SuppressWarnings("rawtypes")
    public ServiceModel getServiceModel(PersistenceState state) { return new K2ListBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    @Column(name="Alias", nullable=false)
    protected String alias;
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    
    @Column(name="Name", nullable=false)
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
    @JoinColumn(name="ListOfEntity", nullable=false)
    protected K2EntityENT listOfEntity;
    public K2EntityENT getListOfEntity() { return listOfEntity; }
    public void setListOfEntity(K2EntityENT listOfEntity) { this.listOfEntity = listOfEntity; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
    @JoinColumn(name="K2Entity", nullable=false)
    protected K2EntityENT k2Entity;
    public K2EntityENT getK2Entity() { return k2Entity; }
    public void setK2Entity(K2EntityENT k2Entity) { this.k2Entity = k2Entity; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (K2List.class.isAssignableFrom(source.getClass())) {
    		K2List clone = (K2List)source;
            id = clone.getId();
            alias = clone.getAlias();
            name = clone.getName();
            listOfEntity = clone.getListOfEntity().getEntity();
            k2Entity = clone.getK2Entity().getEntity();
    	}
    }
    
}
