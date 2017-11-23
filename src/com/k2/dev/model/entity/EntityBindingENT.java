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
import com.k2.dev.model.Dependency;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.bo.DependencyBO;
import com.k2.dev.model.bo.EntityBindingBO;
import com.k2.dev.model.entity.K2SnippetBindingENT;

@Entity(name="EntityBinding")
@Table(name="EntityBindings")
@PrimaryKeyJoinColumn(name="SnippetBindingID")
public class EntityBindingENT extends K2SnippetBindingENT {
	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new EntityBindingBO(this, state); }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2FieldENT.class, optional=true)
	@JoinColumn(name="FieldID", nullable=true)
	@Expose(serialize=false)
	protected K2FieldENT bindingField;
	public K2FieldENT getBindingField() { return bindingField; }
	public void setBindingField(K2FieldENT field) { this.bindingField = field; }
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (EntityBinding.class.isAssignableFrom(source.getClass())) {
			EntityBinding clone = (EntityBinding)source;
			bindingField = clone.getBindingField().getEntity();
		}
	}


	
}
