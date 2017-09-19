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
import com.k2.common.util.StringUtil;

@Entity
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



	
	
	

}
