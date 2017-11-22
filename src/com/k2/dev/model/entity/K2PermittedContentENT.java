package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.bo.K2NativeFieldBO;
import com.k2.dev.model.bo.K2PermittedContentBO;

@Entity(name="K2PermittedContent")
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
    
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2PermittedContentBO(this, state); }
	
	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
	@Override
    public Long getId() { return id; }
	@Override
	public void setId(Long id) { this.id=id; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetContainerENT.class, optional=false)
	@JoinColumn(name="ContainerID", nullable=false)
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

	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		if (K2PermittedContent.class.isAssignableFrom(source.getClass())) {
			K2PermittedContent clone = (K2PermittedContent)source;
			id = clone.getId();
			container = clone.getContainer().getEntity();
			name = clone.getName();
		}
	}


}
