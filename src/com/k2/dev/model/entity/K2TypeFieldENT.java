package com.k2.dev.model.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.model.K2TypeField;
import com.k2.dev.model.bo.K2TypeFieldBO;

@Entity(name="K2TYpeField")
@Table(name="K2TypeFields")
@PrimaryKeyJoinColumn(name="FieldID")
@DiscriminatorValue(value = "TYPE")
public class K2TypeFieldENT extends K2FieldENT {
	
	public static class Types extends K2FieldENT.Types {
		
	}

	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2TypeFieldBO(this, state); }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2TypeDefENT.class, optional=false)
	@JoinColumn(name="TypeDefinition", nullable=false)
	protected K2TypeDefENT typeDefintion;
	public K2TypeDefENT getTypeDefinition() { return typeDefintion; }
	public void setTypeDefinition(K2TypeDefENT typeDefintion) { this.typeDefintion = typeDefintion; }

	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (K2TypeField.class.isAssignableFrom(source.getClass())) {
			K2TypeField clone = (K2TypeField)source;
			typeDefintion = clone.getTypeDefinition().getEntity(); 
		}
	}




}
