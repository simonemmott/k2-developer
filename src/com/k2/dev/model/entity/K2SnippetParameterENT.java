package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;

@Entity
@Table(name="SnippetParameters")
public class K2SnippetParameterENT implements ID {

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final K2SnippetParameterENT other = (K2SnippetParameterENT) obj;
        return Objects.equal(this.snippet, other.getWidget())
        		&&Objects.equal(this.name, other.getName());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.snippet, this.name);
    }
        
	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
	@Override
    public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id=id; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetENT.class, optional=true)
	@JoinColumn(name="SnippetID", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetENT snippet;
	public K2SnippetENT getWidget() { return snippet; }
	public void setWidget(K2SnippetENT snippet) { this.snippet = snippet; }
	
	@Column(name="Name", nullable=false, length=50)
	@Expose(serialize = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Column(name="SetterMethod", nullable=true, length=250)
	@Expose(serialize = true)
	protected String setterMethod;
	public String getSetterMethod() { return setterMethod; }
	public void setSetterMethod(String setterMethod) { this.setterMethod = setterMethod;}

	@Column(name="GetterMethod", nullable=true, length=250)
	@Expose(serialize = true)
	protected String getterMethod;
	public String getGetterMethod() { return getterMethod; }
	public void setGetterMethod(String getterMethod) { this.getterMethod = getterMethod; }

	@Column(name="DataType", nullable=true, length=500)
	@Expose(serialize = true)
	protected String dataType;
	public String getDataType() { return dataType; }
	public void setDataType(String dataType) { this.dataType = dataType; }

	@Column(name="Description", nullable=true, length=500)
	@Expose(serialize = true)
	protected String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }


}
