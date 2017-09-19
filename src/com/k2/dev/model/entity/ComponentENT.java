package com.k2.dev.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;

@Entity
@Table(name="Components")
@Inheritance(strategy=InheritanceType.JOINED)
public class ComponentENT implements ID {

	@Id
	@Column(name="ID")
	@Expose(serialize = true)	
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }

	@Column(name="Name", nullable=false, length=50)
	@Expose(serialize = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Column(name="PackageName", nullable=false, length=50)
	@Expose(serialize = true)
	protected String packageName;
	public String getPackageName() { return packageName; }
	public void setPackageName(String packageName) { this.packageName = packageName; }


}
