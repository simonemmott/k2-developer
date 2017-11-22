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
import com.k2.dev.model.Component;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.entity.ComponentENT.Types;

@Entity(name="Component")
@Table(name="Components")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="ComponentType")
public class ComponentENT implements ID {

    
    public static class Types {
        
        public static enum ComponentType implements K2Type {
        
                UNTYPED(51, "Untyped", "The type of this component is not defined."),
                K2ENTITY(52, "K2Enity", "This component is an entity.");
            
            private int id;
            private String name;
            private String description;
            
            ComponentType(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new ComponentBO(this, state); }
    
    @Id
    @Column(name="ID")
    protected Long id;
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id=id; }
    
    @Column(name="Name", nullable=false)
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    @Column(name="PackageName", nullable=false)
    protected String packageName;
    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2ServiceENT.class, optional=false)
    @JoinColumn(name="Service", nullable=false)
    protected K2ServiceENT k2Service;
    public K2ServiceENT getK2Service() { return k2Service; }
    public void setK2Service(K2ServiceENT k2Service) { this.k2Service = k2Service; }
    
	@Column(name="ComponentType", insertable=false, updatable=false, nullable=false)
	@Enumerated(EnumType.STRING)
	protected Types.ComponentType componentType;
	public Types.ComponentType getComponentType() { return componentType; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
	    	if (Component.class.isAssignableFrom(source.getClass())) {
	    		Component clone = (Component)source;
	            id = clone.getId();
	            name = clone.getName();
	            packageName = clone.getPackageName();
	            k2Service = clone.getK2Service().getEntity();
	    	}
    }
}
