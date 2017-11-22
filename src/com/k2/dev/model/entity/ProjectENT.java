package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.model.Literal;
import com.k2.dev.model.Project;
import com.k2.dev.model.bo.K2SnippetParameterBO;
import com.k2.dev.model.bo.ProjectBO;

@Entity(name="Project")
@Table(name="Projects")
public class ProjectENT implements ID {
	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new ProjectBO(this, state); }


	@Id
	@Column(name="ID")
	protected Long id;
	@Override
	public Long getId() { return id; }
	@Override
	public void setId(Long id) { this.id = id; }

	@Column(name="Name", length=100, nullable=false)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Column(name="Description", length=250, nullable=true)
	protected String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	@Column(name="FileSystemRoot", length=1024, nullable=false)
	protected String fileSystemRoot;
	public String getFileSystemRoot() { return fileSystemRoot; }
	public void setFileSystemRoot(String fileSystemRoot) { this.fileSystemRoot = fileSystemRoot; }
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		if (Project.class.isAssignableFrom(source.getClass())) {
			Project clone = (Project)source;
			id = clone.getId();
			name = clone.getName();
			description = clone.getDescription();
			fileSystemRoot = clone.getFileSystemRoot();
		}
	}



}
