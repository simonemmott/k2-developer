package com.k2.dev.model.bo;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.util.ClassUtil;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Literal;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.model.entity.ProjectENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ProjectService;

@SuppressWarnings("rawtypes")
@Configurable
public class ProjectBO extends GenericServiceModel implements ServiceModel, Project {
	
	@Autowired(required=true)
	protected ProjectService service;
	@Override
	public EntityService getService() { return service; }
	
	public static Project NULL = new ProjectBO();
	public ProjectBO() { super(null); }
    public ProjectBO(PersistenceState state) { super(state); }
    public ProjectBO(ProjectENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.PROJECT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Project Null() { return NULL; }
	
	protected ProjectENT entity;
	@Override
	public ProjectENT getEntity() { return entity; }
	
	@Override
	public String getIdentity() { return getName(); }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public String getDescription() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } getEntity().setDescription(description); changed(); }
	
	@Override
	public String getFileSystemRoot() { if (isNull()) { return null; } return getEntity().getFileSystemRoot(); }
	@Override
	public void setFileSystemRoot(String fileSystemRoot) { if (isNull()) { return; } getEntity().setFileSystemRoot(fileSystemRoot); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new ProjectENT(); }
		if (Project.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Project)source);
		}
	}


	
	@Override
	public boolean verifyProjectRoot() {
		if (this.isNull()) { return false; }
		if ((null==this.getFileSystemRoot())||("".equals(this.getFileSystemRoot()))) { return false; }
		File root = new File(this.getFileSystemRoot());
		if (!root.exists()) { return false; }
		if (!root.isDirectory()) { return false; }
		if (root.canRead()&&root.canWrite()) { return true; }
		return false;
	}
	
	@Override
	public void createProjectRoot() {
		if (this.isNull()) { return; }
		if ((null==this.getFileSystemRoot())||("".equals(this.getFileSystemRoot()))) { return; }
		if (verifyProjectRoot()) { return; }
		File root = new File(this.getFileSystemRoot());
		root.mkdirs();
	}	
	
	@Override
	public boolean verifyPackageDirectory(String packageName) {
		if (this.isNull()) { return false; }
		if ((null==this.getFileSystemRoot())||("".equals(this.getFileSystemRoot()))) { return false; }
		if ((null==packageName)||("".equals(packageName))) { return false; }
		String path = getPackagePath(packageName);
		File packageDir = new File(path);
		if (!packageDir.exists()) { return false; }
		if ((packageDir.isDirectory())&&(packageDir.canWrite())&&(packageDir.canRead())) { return true; }
		return false;
	}
	
	private String getPackagePath(String packageName) {
		return this.getFileSystemRoot()+File.separatorChar+"src"+File.separatorChar+ClassUtil.packageNameToPath(packageName);
	}
	
	@Override
	public void createPackageDirectory(String packageName) {

	
		if (this.isNull()) { return; }
		if ((null==this.getFileSystemRoot())||("".equals(this.getFileSystemRoot()))) { return; }
		if ((null==packageName)||("".equals(packageName))) { return; }
		String path = getPackagePath(packageName);
		File packageDir = new File(path);
		if (!packageDir.exists()) {
			packageDir.mkdirs();
		}
	}
	
	@Override
	public File getPackageDirectory(String packageName) {
		if (!verifyProjectRoot()) { createProjectRoot(); }
		if (!verifyPackageDirectory(packageName)) { createPackageDirectory(packageName); }
		return new File(getPackagePath(packageName));

	}

	@Override
	public File getJavaFile(String packageName, String component) {
		if (!verifyProjectRoot()) { createProjectRoot(); }
		if (!verifyPackageDirectory(packageName)) { createPackageDirectory(packageName); }
		return new File(getPackagePath(packageName)+File.separatorChar+component+".java");

	}

	

	
}
