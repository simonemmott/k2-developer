package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Entities")
@PrimaryKeyJoinColumn(name="ComponentID")
public class K2EntityENT extends ComponentENT {
	
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


}
