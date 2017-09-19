package com.k2.dev.model.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="Templates")
@PrimaryKeyJoinColumn(name="SnippetID")
public class TemplateENT extends K2SnippetENT {

	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetENT.class, optional=false)
	@JoinColumn(name="PopulatesSnippetID", nullable=false)
	@Expose(serialize=false)
	protected K2SnippetENT populatesSnippet;
	public K2SnippetENT getPopulatesSnippet() { 
		return populatesSnippet; 
	}
	public TemplateENT setPopulatesSnippet(K2SnippetENT populatesSnippet) { 
		this.populatesSnippet = populatesSnippet; 
		return this; 
	}
}
