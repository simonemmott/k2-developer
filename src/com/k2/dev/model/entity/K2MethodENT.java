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
import com.k2.dev.model.K2Method;
import com.k2.dev.model.bo.K2MethodBO;

@Entity(name="K2Method")
@Table(name="K2Methods")
@Inheritance(strategy=InheritanceType.JOINED)
public class K2MethodENT implements ID {

    
    public static class Types {
        
        public static enum returnTypes implements K2Type {
        
                VOID(61, "Void", "This method does not return a value."),
                NATIVE(62, "Native", "This method returns a native data type"),
                ENTITY(63, "Entity", "This method returns an entity");
            
            private int id;
            private String name;
            private String description;
            
            returnTypes(int id, String name, String description) { 
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
        
        
        public static enum nativeReturnType implements K2Type {
        
                BOOLEAN(71, "Boolean", "This method returns a boolean value."),
                INTEGER(81, "Small integer", "This method returns an Integer."),
                LONG(82, "Large integer", "This method returns a Long."),
                FLOAT(83, "Small decimal", "This method returns a Float."),
                DOUBLE(84, "Large decimal", "This method returns a Double."),
                DATE(85, "Date and time", "This method returns a Date."),
                STRING(86, "Text", "This method returns a String.");
            
            private int id;
            private String name;
            private String description;
            
            nativeReturnType(int id, String name, String description) { 
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
    public ServiceModel getServiceModel(PersistenceState state) { return new K2MethodBO(this, state); }
    
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
    
    
    @Column(name="ReturnsType", insertable=true, updatable=true, nullable=false)
    @Enumerated(EnumType.STRING)
    protected Types.returnTypes returnsType;
    public Types.returnTypes getReturnsType() { return returnsType; }
    public void setReturnsType(Types.returnTypes returnsType) { this.returnsType = returnsType; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=true)
    @JoinColumn(name="ReturnsEntity", nullable=true)
    protected K2EntityENT returnsEntity;
    public K2EntityENT getReturnsEntity() { return returnsEntity; }
    public void setReturnsEntity(K2EntityENT returnsEntity) { this.returnsEntity = returnsEntity; }
    
    
    @Column(name="NativeReturnType", insertable=true, updatable=true, nullable=true)
    @Enumerated(EnumType.STRING)
    protected Types.nativeReturnType nativeReturnType;
    public Types.nativeReturnType getNativeReturnType() { return nativeReturnType; }
    public void setNativeReturnType(Types.nativeReturnType nativeReturnType) { this.nativeReturnType = nativeReturnType; }
    
    @Column(name="MethodBody", nullable=true)
    protected String methodBody;
    public String getMethodBody() { return methodBody; }
    public void setMethodBody(String methodBody) { this.methodBody = methodBody; }
    
    @Column(name="DependencyFields", nullable=true)
    protected String dependencyFields;
    public String getDependencyFields() { return dependencyFields; }
    public void setDependencyFields(String dependencyFields) { this.dependencyFields = dependencyFields; }
    
    @ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
    @JoinColumn(name="Entity", nullable=false)
    protected K2EntityENT k2Entity;
    public K2EntityENT getK2Entity() { return k2Entity; }
    public void setK2Entity(K2EntityENT k2Entity) { this.k2Entity = k2Entity; }
    
    
    @SuppressWarnings("rawtypes")
    public void clone(ServiceModel source) {
    	if (K2Method.class.isAssignableFrom(source.getClass())) {
    		K2Method clone = (K2Method)source;
            id = clone.getId();
            alias = clone.getAlias();
            name = clone.getName();
            returnsType = clone.getReturnsType();
            returnsEntity = clone.getReturnsEntity().getEntity();
            nativeReturnType = clone.getNativeReturnType();
            methodBody = clone.getMethodBody();
            dependencyFields = clone.getDependencyFields();
            k2Entity = clone.getK2Entity().getEntity();
    	}
    }
    
}
