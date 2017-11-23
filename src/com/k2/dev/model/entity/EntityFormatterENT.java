package com.k2.dev.model.entity;


import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.bo.EntityBindingBO;
import com.k2.dev.model.bo.EntityFormatterBO;

@Entity(name="EntityFormatter")
@Table(name="EntityFormatters")
@PrimaryKeyJoinColumn(name="SnippetID")
public class EntityFormatterENT extends TemplateENT {
	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new EntityFormatterBO(this, state); }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
	@JoinColumn(name="ContainerID", nullable=false)
	protected K2EntityENT entityToFormat;
	public K2EntityENT getEntityToFormat() { return entityToFormat; }
	public void setEntityToFormat(K2EntityENT entityToFormat) { this.entityToFormat = entityToFormat; }
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (EntityFormatter.class.isAssignableFrom(source.getClass())) {
			EntityFormatter clone = (EntityFormatter)source;
			entityToFormat = clone.getEntityToFormat().getEntity();
		}
	}


	

}
