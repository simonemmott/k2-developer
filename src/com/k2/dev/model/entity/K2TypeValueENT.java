package com.k2.dev.model.entity;


import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.google.gson.annotations.Expose;
import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.util.K2Type;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.bo.K2FieldBO;
import com.k2.dev.model.bo.K2TypeDefBO;
import com.k2.dev.model.bo.K2TypeValueBO;

@Entity(name="K2TypeValue")
@Table(name="K2TypeValues")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="K2TypeValue")
public class K2TypeValueENT implements ID {
	
	public static class Types {
	}

	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new K2TypeValueBO(this, state); }

	@Id
	@Column(name="ID")
	@Expose(serialize = true)	
	protected Long id;
	@Override
	public Long getId() { return id; }
	@Override
	public void setId(Long id) { this.id = id; }

	@Column(name="Alias", nullable=false, length=50)
	@Expose(serialize = true)
	protected String alias;
	public String getAlias() { return alias; }
	public void setAlias(String alias) { this.alias = alias; }

	@Column(name="Name", nullable=false, length=50)
	@Expose(serialize = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Column(name="Description", nullable=true, length=500)
	@Expose(serialize = true)
	protected String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2TypeDefENT.class, optional=false)
	@JoinColumn(name="TypeDefinition", nullable=false )
	protected K2TypeDefENT typeDefinition;
	public K2TypeDefENT getTypeDefinition() { return typeDefinition; } 
	public void setTypeDefinition(K2TypeDefENT typeDefinition) { this.typeDefinition = typeDefinition; }
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		if (K2TypeValue.class.isAssignableFrom(source.getClass())) {
			K2TypeValue clone = (K2TypeValue)source;
			id = clone.getId();
			alias = clone.getAlias();
			name = clone.getName();
			description = clone.getDescription();
			typeDefinition = clone.getTypeDefinition().getEntity();
		}
	}

}
