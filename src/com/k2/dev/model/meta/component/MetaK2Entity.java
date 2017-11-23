package com.k2.dev.model.meta.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.common.fieldSet.FieldSet;
import com.k2.common.interaction.FieldHandler;
import com.k2.common.interaction.ListHandler;
import com.k2.common.interaction.MethodHandler;
import com.k2.common.meta.MetaBooleanField;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.common.meta.MetaLinkedField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaListHandlers;
import com.k2.common.meta.MetaMethod;
import com.k2.common.meta.MetaMethodHandlers;
import com.k2.common.meta.MetaMethodParameter;
import com.k2.common.meta.MetaMethodParameters;
import com.k2.common.meta.MetaTextField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlLinkedField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2List;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.Project;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaComponent.FieldHandlers;
import com.k2.dev.model.meta.component.MetaComponent.Fields;
import com.k2.dev.model.meta.component.MetaComponent.Lists;
import com.k2.dev.model.meta.component.MetaComponent.Methods;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.service.K2TypeValueService;
import com.k2.dev.service.ProjectService;
import com.k2.dev.web.stateless.ImplementedExpressionController;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.K2ListController;
import com.k2.dev.web.stateless.K2MethodController;
import com.k2.dev.web.stateless.K2TypeDefController;
import com.k2.dev.web.stateless.K2TypeValueController;
import com.k2.dev.web.stateless.ProjectController;

@SuppressWarnings({"unused"})
public class MetaK2Entity extends MetaComponent {
	
	public static class Fields extends MetaComponent.Fields {
		public static MetaField<K2Entity, String> ENTITYNAME = new MetaTextField<K2Entity, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"entityName", // Alias
				"Entity name", // Default label
				FieldHandlers.ENTITYNAME.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				50, // The maximum number of characters in the field
				30 // The displayed size of the field
				);
		public static MetaField<K2Entity, K2Entity> EXTENDS_ENTITY = new MetaLinkedField<K2Entity, K2Entity>(
				K2Entity.class,  // Data type
				false, // Required
				true, // Enabled
				"extendsEntity", // Alias
				"Entends entity", // Default label 
				FieldHandlers.EXTENDS_ENTITY.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.EXTENDABLE_ENTITIES.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2EntityService.class // The class of the service object handling the linked entities
				);
		public static MetaField<K2Entity, String> INHERITANCE_JOIN_COLUMN = new MetaTextField<K2Entity, String>(
				String.class,  // Data type
				false, // Required
				true, // Enabled
				"inheritanceJoinColumn", // Alias 
				"Join column", // Default label 
				FieldHandlers.INHERITANCE_JOIN_COLUMN.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				50, // The maximum number of characters in the field
				30 // The displayed size of the field
				);
		public static MetaField<K2Entity, String> TABLE_NAME = new MetaTextField<K2Entity, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"tableName", // Alias
				"Table name", // Default label 
				FieldHandlers.TABLE_NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				30, // The maximum number of characters in the field
				30 // The displayed size of the field
				);
		public static MetaField<K2Entity, K2TypeDef> DISCRIMINATOR_TYPE = new MetaLinkedField<K2Entity, K2TypeDef>(
				K2TypeDef.class,  // Data type
				false, // Required
				true, // Enabled
				"discriminatorType", // Alias
				"Discriminator type", // Default label 
				FieldHandlers.DISCRIMINATOR_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.ENTITY_TYPES.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2TypeDefService.class // The class of the service object handling the linked entities
				);
		public static MetaField<K2Entity, String> DISCRIMINATOR_COLUMN = new MetaTextField<K2Entity, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"discriminatorColumn", // Alias
				"Discriminator column", // Default label 
				FieldHandlers.DISCRIMINATOR_COLUMN.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				30, // The maximum number of characters in the field
				30 // The displayed size of the field
				);
		public static MetaField<K2Entity, Boolean> IS_ABSTRACT = new MetaBooleanField<K2Entity, Boolean>(
				Boolean.class, // Data type
				true, // Required
				true, // Enabled
				"isAbsract", // Alias
				"Abstract", // Default label
				FieldHandlers.IS_ABSTRACT.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null // Default right caption
				// Boolean field settings
				);
		public static MetaField<K2Entity, K2TypeValue> DISCRIMINATOR_VALUE = new MetaLinkedField<K2Entity, K2TypeValue>(
				K2TypeValue.class,  // Data type
				false, // Required
				true, // Enabled
				"discriminatorValue", // Alias
				"Discriminator", // Default label 
				FieldHandlers.DISCRIMINATOR_VALUE.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.DISCRIMINATOR_VALUES.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2TypeValueService.class // The class of the service object handling the linked entities
				);
		public static MetaField<K2Entity, Boolean> IS_EXTENDED = new MetaBooleanField<K2Entity, Boolean>(
				Boolean.class, // Data type
				true, // Required
				true, // Enabled
				"isExtended", // Alias
				"Extended", // Default label
				FieldHandlers.IS_EXTENDED.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null // Default right caption
				// Boolean field settings
				);
		public static MetaField<K2Entity, String> PLURAL_NAME = new MetaTextField<K2Entity, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"pluralName", // Alias
				"Plural name", // Default label 
				FieldHandlers.PLURAL_NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				50, // The maximum number of characters in the field
				30 // The displayed size of the field
				);
		public static MetaField<K2Entity, String> DESCRIPTION = new MetaTextField<K2Entity, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"description", // Alias
				"Description", // Default label 
				FieldHandlers.DESCRIPTION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				250, // The maximum number of characters in the field
				50 // The displayed size of the field
				);
		@SuppressWarnings("rawtypes")
		private static Map<String, MetaField> fields; 
		@SuppressWarnings("rawtypes")
		private static List<MetaField> orderedFields;
		@SuppressWarnings("rawtypes")
		public static List<MetaField> getFields() {
			if (fields == null) {
				fields =  new HashMap<String, MetaField>();
				orderedFields = new ArrayList<MetaField>();
				for (MetaField metaField : MetaComponent.Fields.getFields()) {
					fields.put(metaField.alias, metaField); orderedFields.add(metaField);
				}
				fields.put(ENTITYNAME.alias, ENTITYNAME); orderedFields.add(ENTITYNAME);
				fields.put(EXTENDS_ENTITY.alias, EXTENDS_ENTITY); orderedFields.add(EXTENDS_ENTITY);
				fields.put(INHERITANCE_JOIN_COLUMN.alias, INHERITANCE_JOIN_COLUMN); orderedFields.add(INHERITANCE_JOIN_COLUMN);
				fields.put(TABLE_NAME.alias, TABLE_NAME); orderedFields.add(TABLE_NAME);
				fields.put(DISCRIMINATOR_TYPE.alias, DISCRIMINATOR_TYPE); orderedFields.add(DISCRIMINATOR_TYPE);
				fields.put(DISCRIMINATOR_COLUMN.alias, DISCRIMINATOR_COLUMN); orderedFields.add(DISCRIMINATOR_COLUMN);
				fields.put(IS_ABSTRACT.alias, IS_ABSTRACT); orderedFields.add(IS_ABSTRACT);
				fields.put(DISCRIMINATOR_VALUE.alias, DISCRIMINATOR_VALUE); orderedFields.add(DISCRIMINATOR_VALUE);
				fields.put(IS_EXTENDED.alias, IS_EXTENDED); orderedFields.add(IS_EXTENDED);
				fields.put(PLURAL_NAME.alias, PLURAL_NAME); orderedFields.add(PLURAL_NAME);
				fields.put(DESCRIPTION.alias, DESCRIPTION); orderedFields.add(DESCRIPTION);
			}
			return orderedFields;
		}
		@SuppressWarnings("rawtypes")
		public static MetaField getMetaField(String alias) { getFields(); return fields.get(alias); }

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public MetaField getMetaField(String alias) { return Fields.getMetaField(alias); }
	@SuppressWarnings("rawtypes")
	@Override
	public List<MetaField> getMetaFields() { return Fields.getFields(); }
	
	public static class FieldHandlers extends MetaComponent.FieldHandlers {
		
		public static class ENTITYNAME extends FieldHandler<K2Entity, String> {
			public ENTITYNAME() { super(Fields.ENTITYNAME); }
			@Override public String get() { return entity.getEntityName(); }
			@Override public void set(String value) { entity.setEntityName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class EXTENDS_ENTITY extends FieldHandler<K2Entity, K2Entity> {
			public EXTENDS_ENTITY() { super(Fields.EXTENDS_ENTITY); }
			@Override public K2Entity get() { return entity.getExtendsEntity(); }
			@Override public void set(K2Entity value) { entity.setExtendsEntity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class INHERITANCE_JOIN_COLUMN extends FieldHandler<K2Entity, String> {
			public INHERITANCE_JOIN_COLUMN() { super(Fields.INHERITANCE_JOIN_COLUMN); }
			@Override public String get() { return entity.getInheritanceJoinColumn(); }
			@Override public void set(String value) { entity.setInheritanceJoinColumn(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class TABLE_NAME extends FieldHandler<K2Entity, String> {
			public TABLE_NAME() { super(Fields.TABLE_NAME); }
			@Override public String get() { return entity.getTableName(); }
			@Override public void set(String value) { entity.setTableName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class DISCRIMINATOR_TYPE extends FieldHandler<K2Entity, K2TypeDef> {
			public DISCRIMINATOR_TYPE() { super(Fields.DISCRIMINATOR_TYPE); }
			@Override public K2TypeDef get() { return entity.getDiscriminatorType(); }
			@Override public void set(K2TypeDef value) { entity.setDiscriminatorType(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class DISCRIMINATOR_COLUMN extends FieldHandler<K2Entity, String> {
			public DISCRIMINATOR_COLUMN() { super(Fields.DISCRIMINATOR_COLUMN); }
			@Override public String get() { return entity.getDiscriminatorColumn(); }
			@Override public void set(String value) { entity.setDiscriminatorColumn(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class IS_ABSTRACT extends FieldHandler<K2Entity, Boolean> {
			public IS_ABSTRACT() { super(Fields.IS_ABSTRACT); }
			@Override public Boolean get() { return entity.getIsAbstract(); }
			@Override public void set(Boolean value) { entity.setIsAbsract(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toBoolean(value)); }
			@Override public String getForUI() { return BooleanUtil.toString(get()); }
		}
		
		public static class DISCRIMINATOR_VALUE extends FieldHandler<K2Entity, K2TypeValue> {
			public DISCRIMINATOR_VALUE() { super(Fields.DISCRIMINATOR_VALUE); }
			@Override public K2TypeValue get() { return entity.getDiscriminatorValue(); }
			@Override public void set(K2TypeValue value) { entity.setDiscriminatorValue(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class IS_EXTENDED extends FieldHandler<K2Entity, Boolean> {
			public IS_EXTENDED() { super(Fields.IS_EXTENDED); }
			@Override public Boolean get() { return entity.getIsExtended(); }
			@Override public void set(Boolean value) { entity.setIsExtended(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toBoolean(value)); }
			@Override public String getForUI() { return BooleanUtil.toString(get()); }
		}
		
		public static class PLURAL_NAME extends FieldHandler<K2Entity, String> {
			public PLURAL_NAME() { super(Fields.PLURAL_NAME); }
			@Override public String get() { return entity.getPluralName(); }
			@Override public void set(String value) { entity.setPluralName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class DESCRIPTION extends FieldHandler<K2Entity, String> {
			public DESCRIPTION() { super(Fields.DESCRIPTION); }
			@Override public String get() { return entity.getDescription(); }
			@Override public void set(String value) { entity.setDescription(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
	}

	public static FieldSet defaultFieldSet = new FieldSet(Fields.PACKAGE_NAME, Fields.ENTITYNAME, Fields.EXTENDS_ENTITY, Fields.NAME);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }
		
	public static class Lists extends MetaComponent.Lists {
		public static MetaList<K2Entity, K2Field> FIELDS = new MetaList<K2Entity, K2Field>(
				true, // Enabled
				"fields", // Alias
				"Fields", // Default label
				ListHandlers.FIELDS.class, // List handler class
				K2FieldController.class, // Stateless controller class
				MetaK2Field.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, K2Entity> EXTENDABLE_ENTITIES = new MetaList<K2Entity, K2Entity>(
				false, // Enabled
				"extendableEntities", // Alias
				"Extendable entities", // Default label
				ListHandlers.EXTENDABLE_ENTITIES.class, // List handler class
				K2EntityController.class, // Stateless controller class
				defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, Project> PROJECTS = new MetaList<K2Entity, Project>(
				false, // Enabled
				"projects", // Alias
				"Projects", // Default label
				ListHandlers.PROJECTS.class, // List handler class
				ProjectController.class, // Stateless controller class
				MetaProject.defaultFieldSet, // Default field set
				false // Allow new
				);
		public static MetaList<K2Entity, K2TypeDef> ENTITY_TYPES = new MetaList<K2Entity, K2TypeDef>(
				true, // Enabled
				"entityTypes", // Alias
				"Entity types", // Default label
				ListHandlers.ENTITY_TYPES.class, // List handler class
				K2TypeDefController.class, // Stateless controller class
				MetaK2TypeDef.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, K2TypeValue> DISCRIMINATOR_VALUES = new MetaList<K2Entity, K2TypeValue>(
				false, // Enabled
				"discriminatorValues", // Alias
				"Discriminator values", // Default label
				ListHandlers.DISCRIMINATOR_VALUES.class, // List handler class
				K2TypeValueController.class, // Stateless controller class
				MetaK2TypeValue.defaultFieldSet, // Default field set
				false // Allow new
				);
		public static MetaList<K2Entity, K2List> LISTS = new MetaList<K2Entity, K2List>(
				true, // Enabled
				"lists", // Alias
				"Lists", // Default label
				ListHandlers.LISTS.class, // List handler class
				K2ListController.class, // Stateless controller class
				MetaK2List.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, K2Method> METHODS = new MetaList<K2Entity, K2Method>(
				true, // Enabled
				"methods", // Alias
				"Methods", // Default label
				ListHandlers.METHODS.class, // List handler class
				K2MethodController.class, // Stateless controller class
				MetaK2Method.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, ImplementedExpression> EXPRESSIONS = new MetaList<K2Entity, ImplementedExpression>(
				true, // Enabled
				"expressions", // Alias
				"Expressions", // Default label
				ListHandlers.EXPRESSIONS.class, // List handler class
				ImplementedExpressionController.class, // Stateless controller class
				MetaImplementedExpression.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, K2Entity> SUB_ENTITIES = new MetaList<K2Entity, K2Entity>(
				true, // Enabled
				"subEntities", // Alias
				"Sub entities", // Default label
				ListHandlers.SUB_ENTITIES.class, // List handler class
				K2EntityController.class, // Stateless controller class
				MetaK2Entity.defaultFieldSet, // Default field set
				true // Allow new
				);
		@SuppressWarnings("rawtypes")
		private static Map<String, MetaList> lists; 
		@SuppressWarnings("rawtypes")
		private static List<MetaList> orderedLists;
		@SuppressWarnings("rawtypes")
		public static List<MetaList> getLists() {
			if (lists == null) {
				lists =  new HashMap<String, MetaList>();
				orderedLists = new ArrayList<MetaList>();
				for (MetaList metaList : MetaComponent.Lists.getLists()) {
					lists.put(metaList.alias, metaList); orderedLists.add(metaList);
				}
				lists.put(FIELDS.alias, FIELDS); orderedLists.add(FIELDS);
				lists.put(EXTENDABLE_ENTITIES.alias, EXTENDABLE_ENTITIES); orderedLists.add(EXTENDABLE_ENTITIES);
				lists.put(PROJECTS.alias, PROJECTS); orderedLists.add(PROJECTS);
				lists.put(ENTITY_TYPES.alias, ENTITY_TYPES); orderedLists.add(ENTITY_TYPES);
				lists.put(LISTS.alias, LISTS); orderedLists.add(LISTS);
				lists.put(METHODS.alias, METHODS); orderedLists.add(METHODS);
				lists.put(EXPRESSIONS.alias, EXPRESSIONS); orderedLists.add(EXPRESSIONS);
				lists.put(SUB_ENTITIES.alias, SUB_ENTITIES); orderedLists.add(SUB_ENTITIES);
			}
			return orderedLists;
		}
		@SuppressWarnings("rawtypes")
		public static MetaList getMetaList(String alias) { getLists(); return lists.get(alias); }

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public MetaList getMetaList(String alias) { return Lists.getMetaList(alias); }
	@SuppressWarnings("rawtypes")
	@Override
	public List<MetaList> getMetaLists() { return Lists.getLists(); }	
	
	public static class ListHandlers extends MetaComponent.ListHandlers {
		
		public static class FIELDS extends ListHandler<K2Entity, K2Field> {
			public FIELDS() { super(Lists.FIELDS); }
			@Override
			public ServiceList<K2Field> getServiceList() { return entity.getFields(); }
		}
		
		public static class EXTENDABLE_ENTITIES extends ListHandler<K2Entity, K2Entity> {
			public EXTENDABLE_ENTITIES() { super(Lists.EXTENDABLE_ENTITIES); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getExtendableEntities(); }
		}
		
		public static class PROJECTS extends ListHandler<K2Entity, Project> {
			public PROJECTS() { super(Lists.PROJECTS); }
			@Override
			public ServiceList<Project> getServiceList() { return entity.getProjects(); }
		}
		
		public static class ENTITY_TYPES extends ListHandler<K2Entity, K2TypeDef> {
			public ENTITY_TYPES() { super(Lists.ENTITY_TYPES); }
			@Override
			public ServiceList<K2TypeDef> getServiceList() { return entity.getEntityTypes(); }
		}
		
		public static class DISCRIMINATOR_VALUES extends ListHandler<K2Entity, K2TypeValue> {
			public DISCRIMINATOR_VALUES() { super(Lists.DISCRIMINATOR_VALUES); }
			@Override
			public ServiceList<K2TypeValue> getServiceList() { return entity.getDiscriminatorValues(); }
		}
		
		public static class LISTS extends ListHandler<K2Entity, K2List> {
			public LISTS() { super(Lists.LISTS); }
			@Override
			public ServiceList<K2List> getServiceList() { return entity.getLists(); }
		}
		
		public static class METHODS extends ListHandler<K2Entity, K2Method> {
			public METHODS() { super(Lists.METHODS); }
			@Override
			public ServiceList<K2Method> getServiceList() { return entity.getMethods(); }
		}
		
		public static class EXPRESSIONS extends ListHandler<K2Entity, ImplementedExpression> {
			public EXPRESSIONS() { super(Lists.EXPRESSIONS); }
			@Override
			public ServiceList<ImplementedExpression> getServiceList() { return entity.getExpressions(); }
		}
		
		public static class SUB_ENTITIES extends ListHandler<K2Entity, K2Entity> {
			public SUB_ENTITIES() { super(Lists.SUB_ENTITIES); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getSubEntities(); }
		}
		
	}
	
	public static class Methods extends MetaComponent.Methods {
		public static MetaMethod<K2Entity> TEST = new MetaMethod<K2Entity>(
				"test", // Alias
				"Test", // Default label
				"This is a method for testing only!", // Method description
				MethodHandlers.TEST.class, // Method handler class
				new MetaMethodParameters(
						new MetaMethodParameter(
								"test", // Parameter alias
								"Test", // Parameter name
								String.class, // Data type class
								HtmlTextField.class // Parameter field snippet class
								),
						new MetaMethodParameter(
								"field", // Parameter alias
								"Field", // Parameter name
								Project.class, // Data type class
								HtmlLinkedField.class, // Parameter field snippet class
								ListHandlers.FIELDS.class, // List handler class
								K2FieldService.class // Service class
								)
						) // Meta method parameters
				);
		public static MetaMethod<K2Entity> BUILD_CONROLLER = new MetaMethod<K2Entity>(
				"buildController", // Alias
				"Build Controller", // Default label
				"This method builds the stateless conroller for this entity in the working directory of the selected project", // Method description
				MethodHandlers.BUILD_CONROLLER.class, // Method handler class
				new MetaMethodParameters(
						new MetaMethodParameter(
								"project", // Parameter alias
								"Project", // Parameter name
								Project.class, // Data type class
								HtmlLinkedField.class, // Parameter field snippet class
								ListHandlers.PROJECTS.class, // List handler class
								ProjectService.class // Service class
								)
						) // Meta method parameters
				);
		public static MetaMethod<K2Entity> BUILD_ENTITY = new MetaMethod<K2Entity>(
				"buildEntity", // Alias
				"Build Entity", // Default label
				"This method builds the entity class for this entity in the working directory of the selected project", // Method description
				MethodHandlers.BUILD_ENTITY.class, // Method handler class
				new MetaMethodParameters(
						new MetaMethodParameter(
								"project", // Parameter alias
								"Project", // Parameter name
								Project.class, // Data type class
								HtmlLinkedField.class, // Parameter field snippet class
								ListHandlers.PROJECTS.class, // List handler class
								ProjectService.class // Service class
								)
						) // Meta method parameters
				);
		public static MetaMethod<K2Entity> BUILD_ENTITY_INTERFACE = new MetaMethod<K2Entity>(
				"buildEntityInterface", // Alias
				"Build Entity Interface", // Default label
				"This method builds the entity interface for this entity in the working directory of the selected project", // Method description
				MethodHandlers.BUILD_ENTITY_INTERFACE.class, // Method handler class
				new MetaMethodParameters(
						new MetaMethodParameter(
								"project", // Parameter alias
								"Project", // Parameter name
								Project.class, // Data type class
								HtmlLinkedField.class, // Parameter field snippet class
								ListHandlers.PROJECTS.class, // List handler class
								ProjectService.class // Service class
								)
						) // Meta method parameters
				);

		@SuppressWarnings("rawtypes")
		private static Map<String, MetaMethod> methods; 
		@SuppressWarnings("rawtypes")
		private static List<MetaMethod> orderedMethods;
		@SuppressWarnings("rawtypes")
		public static List<MetaMethod> getMethods() {
			if (methods == null) {
				methods =  new HashMap<String, MetaMethod>();
				orderedMethods = new ArrayList<MetaMethod>();
				for (MetaMethod metaMethod : MetaComponent.Methods.getMethods()) {
					methods.put(metaMethod.alias, metaMethod); orderedMethods.add(metaMethod);
				}
				methods.put(TEST.alias, TEST); orderedMethods.add(TEST);
				methods.put(BUILD_CONROLLER.alias, BUILD_CONROLLER); orderedMethods.add(BUILD_CONROLLER);
				methods.put(BUILD_ENTITY.alias, BUILD_ENTITY); orderedMethods.add(BUILD_ENTITY);
				methods.put(BUILD_ENTITY_INTERFACE.alias, BUILD_ENTITY_INTERFACE); orderedMethods.add(BUILD_ENTITY_INTERFACE);
			}
			return orderedMethods;
		}
		@SuppressWarnings("rawtypes")
		public static MetaMethod getMetaMethod(String alias) { getMethods(); return methods.get(alias); }

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public MetaMethod getMetaMethod(String alias) { return Methods.getMetaMethod(alias); }
	@SuppressWarnings("rawtypes")
	@Override
	public List<MetaMethod> getMetaMethods() { return Methods.getMethods(); }
	
	public static class MethodHandlers extends MetaComponent.MethodHandlers {
		
		public static class TEST extends MethodHandler<K2Entity> {
			public TEST() { super(Methods.TEST); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.test((String)methodParms.get("test"), (K2Field)methodParms.get("field"));
				} else {
					String test = "Not set"; if (args.length > 0) { test = (String)args[0]; }
					K2Field field = null; if (args.length > 1) { field = (K2Field)args[1]; }
					entity.test(test, field); 
				}
			}
		}
		public static class BUILD_CONROLLER extends MethodHandler<K2Entity> {
			public BUILD_CONROLLER() { super(Methods.BUILD_CONROLLER); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.buildController((Project)methodParms.get("project"));
				} else {
					Project project = null; if (args.length > 0) { project = (Project)args[0]; }
					entity.buildController(project); 
				}
			}
		}
		public static class BUILD_ENTITY extends MethodHandler<K2Entity> {
			public BUILD_ENTITY() { super(Methods.BUILD_ENTITY); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.buildEntity((Project)methodParms.get("project"));
				} else {
					Project project = null; if (args.length > 0) { project = (Project)args[0]; }
					entity.buildEntity(project); 
				}
			}
		}
		public static class BUILD_ENTITY_INTERFACE extends MethodHandler<K2Entity> {
			public BUILD_ENTITY_INTERFACE() { super(Methods.BUILD_ENTITY_INTERFACE); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.buildEntityInterface((Project)methodParms.get("project"));
				} else {
					Project project = null; if (args.length > 0) { project = (Project)args[0]; }
					entity.buildEntityInterface(project); 
				}
			}
		}
		
	}
	



}
