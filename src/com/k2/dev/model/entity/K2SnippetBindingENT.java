package com.k2.dev.model.entity;


import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.bo.K2PermittedContentBO;
import com.k2.dev.model.bo.K2SnippetBindingBO;

@Entity(name="K2SnippetBinding")
@Table(name="SnippetBindings")
@Inheritance(strategy=InheritanceType.JOINED)
public class K2SnippetBindingENT implements ID {

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final K2SnippetBindingENT other = (K2SnippetBindingENT) obj;
        return Objects.equal(this.snippet, other.getWidget())
        		&&Objects.equal(this.boundParameter, other.getBoundParameter());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.snippet, this.boundParameter);
    }
    
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2SnippetBindingBO(this, state); }
	
	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
	@Override
	public Long getId() { return id; }
	@Override
	public void setId(Long id) { this.id = id; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetENT.class, optional=true)
	@JoinColumn(name="SnippetID", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetENT snippet;
	public K2SnippetENT getWidget() { 
		return snippet; 
	}
	public void setWidget(K2SnippetENT snippet) { this.snippet = snippet; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetParameterENT.class, optional=false)
	@JoinColumn(name="BoundParameterId", nullable=false)
	protected K2SnippetParameterENT boundParameter;
	public K2SnippetParameterENT getBoundParameter() { return boundParameter; }
	public void setBoundParameter(K2SnippetParameterENT boundParameter) { this.boundParameter = boundParameter; }

	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		if (K2SnippetBinding.class.isAssignableFrom(source.getClass())) {
			K2SnippetBinding clone = (K2SnippetBinding)source;
			id = clone.getId();
			snippet = clone.getWidget().getEntity();
			boundParameter = clone.getBoundParameter().getEntity();
		}
	}


}
