package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.k2.common.identity.ID;

@Entity
@Table(name="Projects")
public class ProjectENT implements ID {

	@Id
	@Column(name="ID")
	protected Long id;
	@Override
	public Long getID() { return id; }
	@Override
	public void setID(Long id) { this.id = id; }

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

}
