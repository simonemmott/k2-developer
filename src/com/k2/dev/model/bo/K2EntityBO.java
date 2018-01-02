package com.k2.dev.model.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.util.StringUtil;
import com.k2.core.formatters.ControllerWriter;
import com.k2.core.formatters.ENTWriter;
import com.k2.core.formatters.EntityInterfaceWriter;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.expressions.Ex;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2List;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2ListService;
import com.k2.dev.service.K2MethodService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.service.K2TypeValueService;
import com.k2.dev.service.ProjectService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2EntityBO extends ComponentBO implements ServiceModel, K2Entity, Component {
	
	@Autowired(required=true)
	protected K2EntityService service;
	@Autowired(required=true)
	protected K2FieldService k2FieldService;
	@Autowired(required=true)
	protected ProjectService projectService;
	@Autowired(required=true)
	protected K2TypeDefService k2TypeDefService;
	@Autowired(required=true)
	protected K2TypeValueService k2TypeValueService;
	@Autowired(required=true)
	protected K2ListService k2ListService;
	@Autowired(required=true)
	protected K2MethodService k2MethodService;
	@Autowired(required=true)
	protected ImplementedExpressionService implementedExpressionService;

	@Override
	public EntityService  getService() { return service; }
	
	// Entity methods ------
	public static K2Entity NULL = new K2EntityBO();
	public K2EntityBO() { super(null); }
	public K2EntityBO(K2EntityENT entity, PersistenceState state) { super(state); this.entity = entity; }
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2ENTITY; }
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Entity Null() { return NULL; }
	@Override
	public K2EntityENT getEntity() { return (K2EntityENT)entity; }
	
	// Identity -------
	public String getIdentity() { return getName(); }

	// Fields -----------
	// EntityName field
	@Override
	public String getEntityName() { if (isNull()) { return null; } return getEntity().getEntityName(); }
	@Override
	public void setEntityName(String entityName) { if (isNull()) { return; } getEntity().setEntityName(entityName); changed(); }
	
	// TableName field
	@Override
	public String getTableName() { if (isNull()) { return null; } return getEntity().getTableName(); }
	@Override
	public void setTableName(String tableName) { if (isNull()) { return; } getEntity().setTableName(tableName); changed(); }

	// ExtendsEntity field
	@Override
	public K2Entity getExtendsEntity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(service.getBO(getEntity().getExtendsEntity()), K2EntityBO.NULL); }
	@Override
	public void setExtendsEntity(K2Entity extendsEntity) { if (isNull()) { return; } getEntity().setExtendsEntity(extendsEntity.getEntity()); changed(); }

	// InheritanceJoinCoumn field
	@Override
	public String getInheritanceJoinColumn() { if (isNull()) { return null; } return getEntity().getInheritanceJoinColumn(); }
	@Override
	public void setInheritanceJoinColumn(String joinColumn) { if (isNull()) { return; } getEntity().setInheritanceJoinColumn(joinColumn); changed(); }
	
	// DiscriminatorType field
	@Override
	public K2TypeDef getDiscriminatorType() { if (isNull()) { return K2TypeDefBO.NULL; } return Nvl.nvl(k2TypeDefService.getBO(getEntity().getDiscriminatorType()), K2TypeDefBO.NULL); }
	@Override
	public void setDiscriminatorType(K2TypeDef discriminatorType) { if (isNull()) { return; } getEntity().setDiscriminatorType(discriminatorType.getEntity()); changed(); }
	
	// DiscriminatorColumn field
	@Override
	public String getDiscriminatorColumn() { if (isNull()) { return null; } return getEntity().getDiscriminatorColumn(); }
	@Override
	public void setDiscriminatorColumn(String discriminatorColumn) { if (isNull()) { return; } getEntity().setDiscriminatorColumn(discriminatorColumn); changed(); }
	
	// IsAbstract field
	@Override
	public Boolean getIsAbstract() { if (isNull()) { return null; } return getEntity().getIsAbstract(); }
	@Override
	public void setIsAbsract(Boolean isAbsract) { if (isNull()) { return; } getEntity().setIsAbsract(isAbsract); changed(); }
	
	// DiscriminatorValue field
	@Override
	public K2TypeValue getDiscriminatorValue() { if (isNull()) { return K2TypeValueBO.NULL; } return Nvl.nvl(k2TypeValueService.getBO(getEntity().getDiscriminatorValue()), K2TypeValueBO.NULL); }
	@Override
	public void setDiscriminatorValue(K2TypeValue discriminatorValue) { if (isNull()) { return; } getEntity().setDiscriminatorValue(discriminatorValue.getEntity()); changed(); }
	
	// IsExtended field
	@Override
	public Boolean getIsExtended() { if (isNull()) { return null; } return getEntity().getIsExtended(); }
	@Override
	public void setIsExtended(Boolean isExtended) { if (isNull()) { return; } getEntity().setIsExtended(isExtended); changed(); }
	
	// PluralName field
	@Override
	public String getPluralName() { if (isNull()) { return null; } return getEntity().getPluralName(); }
	@Override
	public void setPluralName(String pluralName) { if (isNull()) { return; } getEntity().setPluralName(pluralName); changed(); }

	// Description field
	@Override
	public String getDescription() { if (isNull()) { return null; } return getEntity().getDescrition(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } getEntity().setDescription(description); changed(); }
	// Clone -----
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2EntityENT(); }
		if (K2Entity.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2Entity)source);
		} else {
			super.clone(source);
		}
	}

	// Lists ----------
	// Fields list
	@Override
	public ServiceList<K2Field> getFields() { return k2FieldService.listForEntity(this); }

	// EntityTypes list
	@Override
	public ServiceList<K2TypeDef> getEntityTypes() { return k2TypeDefService.listForEntity(this); }

	// ExtendableEntities list
	@Override
	public ServiceList<K2Entity> getExtendableEntities() { return service.listAll(); }
	
	// Projects list
	@Override
	public ServiceList<Project> getProjects() { return projectService.listAll(); }
	
	// DiscriminatorValues list
	@Override
	public ServiceList<K2TypeValue> getDiscriminatorValues() { return k2TypeValueService.listForType(getExtendsEntity().getDiscriminatorType()); }
	
	// DiscriminatorValues list
	@Override
	public ServiceList<K2List> getLists() { return k2ListService.listForEnity(this); }
	
	// DiscriminatorValues list
	@Override
	public ServiceList<K2Method> getMethods() { return k2MethodService.listForEntity(this); }
	
	// Expressions list
	@Override
	public ServiceList<ImplementedExpression> getExpressions() { return implementedExpressionService.listForServiceAndEntity(getK2Service(), this); }
	
	// SubEntities list
	@Override
	public ServiceList<K2Entity> getSubEntities() { return service.listSubEntitiesForEntity(this); }

	// Methods ----------
	// Test method
	@Override
	public void test(String test, K2Field field) {
		System.out.println("In test with: "+getIdentity()+" and message: "+test+" And field: "+field.getName());
	}
	
	// Hmmm not sure how to do this automatically
	@Autowired
	SnippetFactory<PrintWriter> snippetFactory;
	@Autowired
	OutputFormatterContextFactory formatterContextFactory;
	
	// buildController method
	@SuppressWarnings("unchecked")
	@Override
	public void buildController(Project project) {
		
		OutputFormatterContext<?, PrintWriter> context = (OutputFormatterContext<?, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetFactory);
		context.setVerbose();	
		
		File controller = project.getJavaFile(getControllerPackageName(), getControllerClassName());
		PrintWriter pw;
		try {
			pw = new PrintWriter(controller);
			
			ControllerWriter<PrintWriter> cw = context.getSnippet(ControllerWriter.class);
			cw.setEntity(this);
			
			cw.output(pw);

		} catch (FileNotFoundException e) {
			// This shouldn't happen
			e.printStackTrace();
		}
	}
	
	// buildEntity method
	@SuppressWarnings("unchecked")
	@Override
	public void buildEntity(Project project) {
		
		OutputFormatterContext<?, PrintWriter> context = (OutputFormatterContext<?, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetFactory);
		context.setVerbose();	
		
		File out = project.getJavaFile(getENTPackageName(), getENTClassName());
		PrintWriter pw;
		try {
			pw = new PrintWriter(out);
			
			ENTWriter<PrintWriter> ew = (ENTWriter<PrintWriter>) context.getFormatter(this, ENTWriter.class);
			
			ew.output(pw);
		
		} catch (FileNotFoundException e) {
			// This shouldn't happen
			e.printStackTrace();
		}
	}
	
	// buildEntityInterface method
	@SuppressWarnings("unchecked")
	@Override
	public void buildEntityInterface(Project project) {
		
		OutputFormatterContext<?, PrintWriter> context = (OutputFormatterContext<?, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetFactory);
		context.setVerbose();	
		
		File out = project.getJavaFile(getModelPackageName(), getEntityName());
		PrintWriter pw;
		try {
			pw = new PrintWriter(out);
			
			EntityInterfaceWriter<PrintWriter> eiw = (EntityInterfaceWriter<PrintWriter>) context.getFormatter(this, EntityInterfaceWriter.class);
			
			eiw.output(pw);
		
		} catch (FileNotFoundException e) {
			// This shouldn't happen
			e.printStackTrace();
		}
	}
	
	// Expressions ----------
	// Alias expression
	@Override
	public String getAlias() { return StringUtil.initialLowerCase(getEntityName()); }
	@Override
	public String getInterfaceExtends() {
		if (this.getExtendsEntity().isNull()) {
			return "ServiceModel";
		} else {
			return "ServiceModel, "+this.getExtendsEntity().getEntityName();
		}
	}
	
	// ControllerPackageName expression
	@Override
	public String getControllerPackageName() { return Ex.STRING.concatenate(getPackageName(), ".web.stateless"); }
	
	// ControllerPackageName expression
	@Override
	public String getControllerClassName() { return Ex.STRING.concatenate(getEntityName(), "Controller"); }
	
	// ControllerExtendsClassName expression
	@Override
	public String getControllerExtendsClassName() { return Ex.STRING.concatenate("AbstractStatelessEntityController<", getEntityName(), ">"); }

	// ModelPackageName expression
	@Override
	public String getModelPackageName() { return Ex.STRING.concatenate(getPackageName(), ".model"); }
	
	// BOPackageName expression
	@Override
	public String getBOPackageName() { return Ex.STRING.concatenate(getPackageName(), ".model.bo"); }
	
	// BOClassName expression
	@Override
	public String getBOClassName() { return Ex.STRING.concatenate(getEntityName(), "BO"); }
	
	// ENTPackageName expression
	@Override
	public String getENTPackageName() { return Ex.STRING.concatenate(getPackageName(), ".model.entity"); }
	
	// ENTClassName expression
	@Override
	public String getENTClassName() { return Ex.STRING.concatenate(getEntityName(), "ENT"); }
	
	// MetaPackageName expression
	@Override
	public String getMetaPackageName() { return Ex.STRING.concatenate(getPackageName(), ".model.meta"); }
	
	// MetaDataPackageName expression
	@Override
	public String getMetaDataPackageName() { return Ex.STRING.concatenate(getPackageName(), ".model.meta.component"); }
	
	// MetaModelClassName expression
	@Override
	public String getMetaModelClassName() { return Ex.STRING.concatenate("Meta", getEntityName()); }
	
	// ServicePackageName expression
	@Override
	public String getServicePackageName() { return Ex.STRING.concatenate(getPackageName(), ".service"); }
	
	// ServiceClassName expression
	@Override
	public String getServiceClassName() { return Ex.STRING.concatenate(getEntityName(), "Service"); }
	
	// ServiceImplementationPackageName expression
	@Override
	public String getServiceImplementationPackageName() { return Ex.STRING.concatenate(getPackageName(), ".service.impl"); }
	
	private Integer test() {
		return Ex.INTEGER.add(
			10,
			test2(
				10,
				20
			),
			test3(),
			test4()
		);		
	}
	
	private Integer test2(Integer value1, Integer value2) {
		return Ex.INTEGER.multiply(
			value2,
			test5(value1)
		);
	}
	
	private Double test3() {
		return Ex.DOUBLE.divide(
			10,
			30,
			1
		);
	}

	private Integer test4() {
		return Ex.INTEGER.multiply(
			10,
			30,
			1
		);
	}
	
	private Integer test5(Integer value1) {
		return Ex.INTEGER.subtract(
			value1,
			10
		);
	}
}
