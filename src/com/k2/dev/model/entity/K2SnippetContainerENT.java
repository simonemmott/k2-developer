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
import com.k2.common.util.StringUtil;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.bo.K2SnippetBindingBO;
import com.k2.dev.model.bo.K2SnippetContainerBO;

@Entity(name="K2SnippetContainer")
@Table(name="SnippetContainers")
public class K2SnippetContainerENT implements ID {

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final K2SnippetContainerENT other = (K2SnippetContainerENT) obj;
        return Objects.equal(this.snippet, other.getWidget())
        		&&Objects.equal(this.alias, other.getAlias());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.snippet, this.alias);
    }
    
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2SnippetContainerBO(this, state); }

	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
    @Override
	public Long getId() { return id; }
	@Override
	public void setId(Long id) { this.id=id; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetENT.class, optional=false)
	@JoinColumn(name="SnippetID", nullable=false)
	@Expose(serialize=false)
	protected K2SnippetENT snippet;
	public K2SnippetENT getWidget() { return snippet; }
	public void setWidget(K2SnippetENT snippet) { this.snippet = snippet; }
	
	@Column(name="Alias", length=50)
	@Expose(serialize = true)
	protected String alias;
	public String getAlias() { return alias; }
	public void setAlias(String alias) {
		if (alias == null) { return; }
		this.alias = StringUtil.initialLowerCase(alias); 
		if ((name==null)||("".equals(name))) {
			name = StringUtil.initialUpperCase(this.alias);
		}
	}

	@Column(name="Name", length=50)
	@Expose(serialize = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { 
		if (name == null) { return; }
		this.name = StringUtil.initialUpperCase(name); 
		if ((alias==null)||("".equals(alias))) {
			alias = StringUtil.initialLowerCase(this.name);
		}
	}

	@Column(name="Description", length=500)
	@Expose(serialize = true)
	protected String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }


	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		if (K2SnippetContainer.class.isAssignableFrom(source.getClass())) {
			K2SnippetContainer clone = (K2SnippetContainer)source;
			id = clone.getId();
			snippet = clone.getWidget().getEntity();
			alias = clone.getAlias();
			name = clone.getName();
			description = clone.getDescription();
		}
	}


	
	
	

}
