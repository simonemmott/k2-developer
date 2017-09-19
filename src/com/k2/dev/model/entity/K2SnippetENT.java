package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;

@Entity
@Table(name="Snippets")
@Inheritance(strategy=InheritanceType.JOINED)
public class K2SnippetENT implements ID {
	
    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        final K2SnippetENT other = (K2SnippetENT) obj;
        return Objects.equal(this.name, other.getName());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
        
	
	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
	@Override
    public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id=id; }

	
	
	@Column(name="Name", nullable=false, length=50)
	@Expose(serialize = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Column(name="PackageName", nullable=false, length=250)
	@Expose(serialize=true)
	protected String packageName;
	public String getPackageName() { return packageName; }
	public void setPackageName(String packageName) { this.packageName = packageName; }

	@Column(name="ClassName", nullable=false, length=100)
	@Expose(serialize=true)
	protected String className;
	public String getClassName() { return className; }
	public void setClassName(String className) { this.className = className; }

	@Column(name="Description", nullable=true, length=1000)
	@Expose(serialize = true)
	protected String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }


}
