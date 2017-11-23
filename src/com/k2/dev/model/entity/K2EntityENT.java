package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.util.K2Type;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.bo.K2EntityBO;

@Entity(name="K2Entity")
@Table(name="Entities")
@PrimaryKeyJoinColumn(name="ComponentID")
@DiscriminatorValue(value = "K2ENTITY")
public class K2EntityENT extends ComponentENT {
	
    public static class Types {
                
    }

	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2EntityBO(this, state); }
	
	@Column(name="EntityName", nullable=true, length=50)
	protected String entityName;
	public String getEntityName() { return entityName; }
	public void setEntityName(String entityName) { this.entityName = entityName; }
	
	@Column(name="TableName", nullable=false, length=30)
	protected String tableName;
	public String getTableName() { return tableName; }
	public void setTableName(String tableName) { this.tableName = tableName; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=true)
	@JoinColumn(name="ExtendsEntityID", nullable=true)
	protected K2EntityENT extendsEntity;
	public K2EntityENT getExtendsEntity() { return extendsEntity; }
	public void setExtendsEntity(K2EntityENT extendsEntity) { this.extendsEntity = extendsEntity; }
	
	@Column(name="InheritanceJoinColumn")
	protected String inheritanceJoinColumn;
	public String getInheritanceJoinColumn() { return inheritanceJoinColumn; }
	public void setInheritanceJoinColumn(String inheritanceJoinColumn) { this.inheritanceJoinColumn = inheritanceJoinColumn; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2TypeDefENT.class, optional=true)
	@JoinColumn(name="DiscriminatorType", nullable=true)
	protected K2TypeDefENT discriminatorType;
	public K2TypeDefENT getDiscriminatorType() { return discriminatorType; }
	public void setDiscriminatorType(K2TypeDefENT discriminatorType) { this.discriminatorType = discriminatorType; }
	
	@Column(name="DiscriminatorColumn", nullable=false, length=30)
	protected String discriminatorColumn;
	public String getDiscriminatorColumn() { return discriminatorColumn; }
	public void setDiscriminatorColumn(String discriminatorColumn) { this.discriminatorColumn = discriminatorColumn; }
	
	@Column(name="IsAbstract", nullable=false, length=30)
	protected Boolean isAbstract;
	public Boolean getIsAbstract() { return isAbstract; }
	public void setIsAbsract(Boolean isAbstract) { this.isAbstract = isAbstract; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2TypeValueENT.class, optional=true)
	@JoinColumn(name="DiscriminatorValue", nullable=true)
	protected K2TypeValueENT discriminatorValue;
	public K2TypeValueENT getDiscriminatorValue() { return discriminatorValue; }
	public void setDiscriminatorValue(K2TypeValueENT discriminatorValue) { this.discriminatorValue = discriminatorValue; }
	
	@Column(name="IsExtended", nullable=false, length=30)
	protected Boolean isExtended;
	public Boolean getIsExtended() { return isExtended; }
	public void setIsExtended(Boolean isExtended) { this.isExtended = isExtended; }

	@Column(name="PluralName", nullable=false, length=50)
	protected String pluralName;
	public String getPluralName() { return pluralName; }
	public void setPluralName(String pluralName) { this.pluralName = pluralName; }
	
	@Column(name="Description", nullable=false, length=250)
	protected String description;
	public String getDescrition() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (K2Entity.class.isAssignableFrom(source.getClass())) {
			K2Entity clone = (K2Entity)source;
			entityName = clone.getEntityName();
			tableName = clone.getTableName();
			extendsEntity = clone.getExtendsEntity().getEntity();
			inheritanceJoinColumn = clone.getInheritanceJoinColumn();
			discriminatorType = clone.getDiscriminatorType().getEntity();
			discriminatorColumn = clone.getDiscriminatorColumn();
			isAbstract = clone.getIsAbstract();
			discriminatorValue = clone.getDiscriminatorValue().getEntity();
			isExtended = clone.getIsExtended();
		}
	}



}
