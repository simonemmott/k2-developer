package com.k2.dev.model.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="EntityFormatters")
@PrimaryKeyJoinColumn(name="SnippetID")
public class EntityFormatterENT extends TemplateENT {
	

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
	@JoinColumn(name="ContainerID", nullable=false)
	protected K2EntityENT entityToFormat;
	public K2EntityENT getEntityToFormat() { return entityToFormat; }
	public void setEntityToFormat(K2EntityENT entityToFormat) { this.entityToFormat = entityToFormat; }
	

}
