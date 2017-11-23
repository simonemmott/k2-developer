package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2ServiceENT;

@SuppressWarnings({"rawtypes"})
public interface K2Service  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public K2ServiceENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public K2Service Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// Alias field
	public String getAlias();
	public void setAlias(String alias);

	// Name field
	public String getName();
	public void setName(String name);

	// PackageName field
	public String getPackageName();
	public void setPackageName(String packageName);
	
	// Lists --------
	// Components list
	public ServiceList<Component> getComponents();
	
	// ServiceExpressions list
	public ServiceList<ImplementedExpression> getServiceExpressions();
	
	// Literals list
	public ServiceList<Literal> getLiterals();

	// Projects list
	public ServiceList<Project> getProjects();

	// RootEntities list
	public ServiceList<K2Entity> getRootEntities();

	// Expressions ---------
	// LibPacakgeName expression
	public String getLibPackageName();

	// MetaPackageName expression
	public String getMetaPackageName();

	// BoPackageName expression
	public String getBoPackageName();
	
	// MetaComponenetPackageName expression
	public String getMetaComponentPackageName();
	
	// WebStatelessPackageName expression
	public String getWebStatelessPackageName();
	
	// Methods ----------
	// BuildLiterals method
	void buildLiterals(Project project);
	
	// BuildExpressions method
	void buildExpressions(Project project);
	
	// BuildMetaModel method
	void buildMetaModel(Project project);
	
}