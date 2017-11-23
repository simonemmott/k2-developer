package com.k2.dev.model.entity;


import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.bo.TemplateBO;
import com.k2.dev.model.bo.TemplateBindingBO;

@Entity(name="Template")
@Table(name="Templates")
@PrimaryKeyJoinColumn(name="SnippetID")
public class TemplateENT extends K2SnippetENT {

	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new TemplateBO(this, state); }
	
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
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (Template.class.isAssignableFrom(source.getClass())) {
			Template clone = (Template)source;
			populatesSnippet = clone.getPopulatesSnippet().getEntity();
		}
	}


}
