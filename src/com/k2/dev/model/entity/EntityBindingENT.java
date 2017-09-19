package com.k2.dev.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.k2.dev.model.entity.K2SnippetBindingENT;

@Entity
@Table(name="EntityBindings")
@PrimaryKeyJoinColumn(name="SnippetBindingID")
public class EntityBindingENT extends K2SnippetBindingENT {

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2FieldENT.class, optional=true)
	@JoinColumn(name="FieldID", nullable=true)
	@Expose(serialize=false)
	protected K2FieldENT bindingField;
	public K2FieldENT getBindingField() { return bindingField; }
	public void setBindingField(K2FieldENT field) { this.bindingField = field; }
	
}
