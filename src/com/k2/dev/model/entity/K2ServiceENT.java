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
import com.google.common.base.Objects;
import com.k2.common.identity.ID;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.ServiceModel;
import com.k2.common.util.K2Type;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.K2ServiceBO;

@Entity(name="K2Service")
@Table(name="K2services")
@Inheritance(strategy=InheritanceType.JOINED)
public class K2ServiceENT implements ID {

    
    public static class Types {
    }
    
    
    @SuppressWarnings("rawtypes")
    public ServiceModel getServiceModel(PersistenceState state) { return new K2ServiceBO(this, state); }
    
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
    
    @Column(name="PackageName", nullable=false)
    protected String packageName;
    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (K2Service.class.isAssignableFrom(source.getClass())) {
    		K2Service clone = (K2Service)source;
            id = clone.getId();
            alias = clone.getAlias();
            name = clone.getName();
            packageName = clone.getPackageName();
    	}
    }
    
}
