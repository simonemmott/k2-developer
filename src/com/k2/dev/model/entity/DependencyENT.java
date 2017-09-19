package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;

@Entity
@Table(name="Dependencies")
public class DependencyENT implements ID {

	@Id
	@Column(name="ID")
	@Expose(serialize = true)	
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }
	
	@Column(name="DependsOnPackageName", nullable=false, length=250)
	@Expose(serialize = true)
	protected String dependsOnPackageName;
	public String getDependsOnPackageName() { return dependsOnPackageName; }
	public void setDependsOnPackageName(String dependsOnPackageName) { this.dependsOnPackageName = dependsOnPackageName; }

	@Column(name="DependsOnComponentName", nullable=false, length=100)
	@Expose(serialize = true)
	protected String dependsOnComponentName;
	public String getDependsOnComponentName() { return dependsOnComponentName; }
	public void setDependsOnComponentName(String dependsOnComponentName) { this.dependsOnComponentName = dependsOnComponentName; }

}
