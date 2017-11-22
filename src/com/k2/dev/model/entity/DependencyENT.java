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
import com.k2.dev.model.Dependency;
import com.k2.dev.model.bo.DependencyBO;

@Entity(name="Dependency")
@Table(name="Dependecies")
@Inheritance(strategy=InheritanceType.JOINED)
public class DependencyENT implements ID {

    
    public static class Types {
    }
    
    
    @SuppressWarnings("rawtypes")
    public ServiceModel getServiceModel(PersistenceState state) { return new DependencyBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
    @JoinColumn(name="Entity", nullable=false)
    protected K2EntityENT k2Entity;
    public K2EntityENT getK2Entity() { return k2Entity; }
    public void setK2Entity(K2EntityENT k2Entity) { this.k2Entity = k2Entity; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2MethodENT.class, optional=true)
    @JoinColumn(name="Method", nullable=true)
    protected K2MethodENT k2Method;
    public K2MethodENT getK2Method() { return k2Method; }
    public void setK2Method(K2MethodENT k2Method) { this.k2Method = k2Method; }
    
    @Column(name="DependsOnPackageName", nullable=false)
    protected String dependsOnPackageName;
    public String getDependsOnPackageName() { return dependsOnPackageName; }
    public void setDependsOnPackageName(String dependsOnPackageName) { this.dependsOnPackageName = dependsOnPackageName; }
    
    @Column(name="DependsOnComponentName", nullable=false)
    protected String dependsOnComponentName;
    public String getDependsOnComponentName() { return dependsOnComponentName; }
    public void setDependsOnComponentName(String dependsOnComponentName) { this.dependsOnComponentName = dependsOnComponentName; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (Dependency.class.isAssignableFrom(source.getClass())) {
    		Dependency clone = (Dependency)source;
            id = clone.getId();
            k2Entity = clone.getK2Entity().getEntity();
            k2Method = clone.getK2Method().getEntity();
            dependsOnPackageName = clone.getDependsOnPackageName();
            dependsOnComponentName = clone.getDependsOnComponentName();
    	}
    }
    
}
