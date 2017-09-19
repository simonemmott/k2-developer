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
import com.k2.common.util.K2Type;
import com.k2.common.util.StringUtil;

@Entity
@Table(name="TemplateContents")
public class TemplateContentENT implements ID {
	
	public static class Types {
		public static enum Type implements K2Type {
			SNIPPET(0, "Snippet", "This container contains a snippet."),
			CONTAINER(1, "Container", "This container contains the contents from the templates container identified by the 'fromContainer'.");
			
			private int id;
			private String name;
			private String description;
			
			Type(int id, String name, String description) { 
				this.id = id; 
				this.name = name;
				this.description = description;
			}
			
			@Override
			public int getId() { return this.id; }
			@Override
			public String getName() { return this.name; }
			@Override
			public String getDescription() { return this.description; }
		}
	}


    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final TemplateContentENT other = (TemplateContentENT) obj;
        return Objects.equal(this.id, other.getID());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
        
	
	@Id
	@Expose(serialize=true)
	@Column(name="Id")
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=TemplateENT.class, optional=true)
	@JoinColumn(name="TemplateID", nullable=true)
	@Expose(serialize=false)
	protected TemplateENT template;
	public TemplateENT getTemplate(){ return template; }
	public void setTemplate(TemplateENT template) { this.template = template; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetENT.class, optional=true)
	@JoinColumn(name="ContainedSnippetId", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetENT containedSnippet;
	public K2SnippetENT getContainedSnippet() { return containedSnippet; }
	public void setContainedSnippet(K2SnippetENT snippet) { this.containedSnippet = snippet; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetContainerENT.class, optional=true)
	@JoinColumn(name="InContainerId", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetContainerENT inContainer;
	public K2SnippetContainerENT getInContainer() { return inContainer; }
	public void setInContainer(K2SnippetContainerENT container) { this.inContainer = container; }


	@Expose(serialize=true)
	@Column(name="Alias")
	protected String alias = StringUtil.initialLowerCase(StringUtil.randomString(12));
	public String getAlias() { return alias; }
	public void setAlias(String alias) { this.alias = alias; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=TemplateContentENT.class, optional=true)
	@JoinColumn(name="ParentContentID", nullable=true)
	@Expose(serialize=false)
	protected TemplateContentENT parentContent;
	public TemplateContentENT getParentContent(){ return parentContent; }
	public void setParentContent(TemplateContentENT parentContent) { this.parentContent = parentContent; }
	
	@Expose(serialize=true)
	@Column(name="ContentType", length=50)
	protected Types.Type type;
	public Types.Type getType() { return type; }
	public void setType(Types.Type type) { this.type = type; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetContainerENT.class, optional=true)
	@JoinColumn(name="FromContainerId", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetContainerENT fromContainer;
	public K2SnippetContainerENT getFromContainer() { return fromContainer; } 
	public void setFromContainer(K2SnippetContainerENT container) { this.fromContainer = container; }

}
