package com.k2.dev.model;

import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2EntityENT.Types;;

@SuppressWarnings("rawtypes")
public interface K2Entity  extends ServiceModel, Component {

	// Service methods -----
	@Override public K2EntityENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public K2Entity Null();
	
	// Fields ------
	// EntityName field
	public String getEntityName();
	public void setEntityName(String entityName);
	
	// TableName field
	public String getTableName();
	public void setTableName(String tableName);
	
	// ExtendsEntity field
	public K2Entity getExtendsEntity();
	public void setExtendsEntity(K2Entity extendsEntity);

	// InheritanceJoinColumn field
	public String getInheritanceJoinColumn();
	public void setInheritanceJoinColumn(String joinColumn);
	
	// DiscriminatorType field
	public K2TypeDef getDiscriminatorType();
	public void setDiscriminatorType(K2TypeDef discriminatorType);

	// DiscriminatorColumn field
	public String getDiscriminatorColumn();
	public void setDiscriminatorColumn(String discriminatorColumn);
	
	// IsAbstrac field
	public Boolean getIsAbstract();
	public void setIsAbsract(Boolean isAbsract);

	// DiscriminatorValue field
	public K2TypeValue getDiscriminatorValue();
	public void setDiscriminatorValue(K2TypeValue discriminatorValue);

	// IsExtended field
	public Boolean getIsExtended();
	public void setIsExtended(Boolean isExtended);

	// PluralName field
	public String getPluralName();
	public void setPluralName(String pluralName);
	
	// Description field
	public String getDescription();
	public void setDescription(String description);
	
	// Lists -------
	// Fields list
	public ServiceList<K2Field> getFields();
	
	// ExtendableEntities list
	public ServiceList<K2Entity> getExtendableEntities();

	// Projects list
	public ServiceList<Project> getProjects();

	// EntityTypes list
	public ServiceList<K2TypeDef> getEntityTypes();
	
	// DiscriminatorValues list
	public ServiceList<K2TypeValue> getDiscriminatorValues();

	// Lists list
	public ServiceList<K2List> getLists();
	
	// Methods list
	public ServiceList<K2Method> getMethods();

	// Expressions list
	public ServiceList<ImplementedExpression> getExpressions();

	// SubEntities list
	public ServiceList<K2Entity> getSubEntities();

	// Methods ---------
	// test method
	public void test(String test, K2Field field);
	
	// buildController method
	public void buildController(Project project);

	// buildEntity method
	public void buildEntity(Project project);

	//buildEntityInterface method
	public void buildEntityInterface(Project project);
	
	// Expressions --------
	// ControllerPackageName expression
	public String getControllerPackageName();

	// ControllerClassName expression
	public String getControllerClassName();

	// ControllerExtendsClassName expression
	public String getControllerExtendsClassName();

	// ModelPackageName expression
	public String getModelPackageName();

	// MetaDataPackegeName expression
	public String getMetaDataPackageName();

	// MetaModelClassName expression
	public String getMetaModelClassName();

	// ServiceClassName expression
	public String getServiceClassName();
	
	// ServicePackageName expression
	public String getServicePackageName();
	
	// ServiceImplementationPackageName expression
	public String getServiceImplementationPackageName();

	// Alias expression
	public String getAlias();

	// InterfaceExtends expression
	public String getInterfaceExtends();

	// ENTPackageName expression
	public String getENTPackageName();

	// BOPackageName expression
	public String getBOPackageName();

	// BOClassName expression
	public String getBOClassName();

	// ENTClassName expression
	public String getENTClassName();
	
	// MetaPackageName expression
	public String getMetaPackageName();

	
	

}