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
@Table(name="PermittedContents")
public class K2PermittedContentENT implements ID {

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final K2PermittedContentENT other = (K2PermittedContentENT) obj;
        return 	Objects.equal(this.getContainer(), other.getContainer()) &&
        		Objects.equal(this.getName(), other.getName());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getContainer(), this.getName());
    }
    
	
	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
	@Override
    public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id=id; }

//	@Column(name="ContainerID", insertable=false, updatable=false)
//	@Expose(serialize=true)
//	protected Long containerId;
//	public Long getContainerId() { return this.containerId; }
//	public void setContainerId(Long containerId) { this.containerId = containerId; }
	
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetContainerENT.class, optional=true)
	@JoinColumn(name="ContainerID", nullable=true)
	protected K2SnippetContainerENT container;
	public K2SnippetContainerENT getContainer() { return container; }
	public void setContainer(K2SnippetContainerENT container) { 
		this.container = container; 
//		this.containerId = container.getID();
	}
	
	@Column(name="Name", nullable=false, length=50)
	@Expose(serialize = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

}
