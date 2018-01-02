package com.k2.dev.model.entity;

import javax.persistence.Column;
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
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.bo.K2LinkedFieldBO;

@Entity(name="K2LinkedField")
@Table(name="K2LinkedFields")
@PrimaryKeyJoinColumn(name="FieldID")
@DiscriminatorValue(value = "LINKED")
public class K2LinkedFieldENT extends K2FieldENT {
	
	public static class Types extends K2FieldENT.Types {
		
	}

	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2LinkedFieldBO(this, state); }
	
	@Column(name="AllowInsert")
	protected Boolean allowInsert;
	public Boolean getAllowInsert() { return allowInsert; }
	public void setAllowInsert(Boolean allowInsert) { this.allowInsert = allowInsert; }

	@Column(name="AllowNavigate")
	protected Boolean allowNavigate;
	public Boolean getAllowNavigate() { return allowNavigate; }
	public void setAllowNavigate(Boolean allowNavigate) { this.allowNavigate = allowNavigate; }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2EntityENT.class, optional=false)
	@JoinColumn(name="LinkedEntity", nullable=false)
	protected K2EntityENT linkedEntity;
	public K2EntityENT getLinkedEntity() { return linkedEntity; }
	public void setLinkedEntity(K2EntityENT linkedEntity) { this.linkedEntity = linkedEntity; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2ListENT.class, optional=true)
	@JoinColumn(name="ValuesList", nullable=true)
	protected K2ListENT valuesList;
	public K2ListENT getValuesList() { return valuesList; }
	public void setValuesList(K2ListENT valuesList) { this.valuesList = valuesList; }

	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (K2LinkedField.class.isAssignableFrom(source.getClass())) {
			K2LinkedField clone = (K2LinkedField)source;
			allowInsert = clone.getAllowInsert();
			allowNavigate = clone.getAllowNavigate();
			linkedEntity = clone.getLinkedEntity().getEntity();
			valuesList = clone.getValuesList().getEntity();
		}
	}




}
