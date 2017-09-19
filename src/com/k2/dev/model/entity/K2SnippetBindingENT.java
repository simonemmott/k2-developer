package com.k2.dev.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;

@Entity
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
		
	@Id
	@Column(name="ID")
	@Expose(serialize = true)
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetENT.class, optional=true)
	@JoinColumn(name="SnippetID", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetENT snippet;
	public K2SnippetENT getWidget() { 
		return snippet; 
	}
	public void setWidget(K2SnippetENT snippet) { this.snippet = snippet; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetParameterENT.class, optional=true)
	@JoinColumn(name="BoundParameterId", nullable=true)
	protected K2SnippetParameterENT boundParameter;
	public K2SnippetParameterENT getBoundParameter() { return boundParameter; }
	public void setBoundParameter(K2SnippetParameterENT boundParameter) { this.boundParameter = boundParameter; }

}
