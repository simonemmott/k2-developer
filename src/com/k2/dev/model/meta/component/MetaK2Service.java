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
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.common.meta.MetaFields;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaLists;
import com.k2.common.meta.MetaMethod;
import com.k2.common.meta.MetaMethodHandlers;
import com.k2.common.meta.MetaMethodParameter;
import com.k2.common.meta.MetaMethodParameters;
import com.k2.common.meta.MetaMethods;
import com.k2.common.meta.MetaListHandlers;
import com.k2.common.meta.MetaNumberField;
import com.k2.common.meta.MetaTextField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlLinkedField;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.Literal;
import com.k2.dev.model.Project;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.model.meta.component.MetaK2Entity.MethodHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Methods;
import com.k2.dev.service.ProjectService;
import com.k2.dev.web.stateless.ComponentController;
import com.k2.dev.web.stateless.ImplementedExpressionController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.LiteralController;
import com.k2.dev.web.stateless.ProjectController;

@SuppressWarnings({"unused"})
public class MetaK2Service implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<K2Service, Long> ID = new MetaNumberField<K2Service, Long>(
				Long.class, // Data type
				true, // Required
				false, // Enabled
				"id", // Alias
				"ID", // Default label
				FieldHandlers.ID.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Number field settings
				null, // The displayed size of the field
				null, // Maximum value for the field
				null // Minimum value for the field
				);
		public static MetaField<K2Service, String> ALIAS = new MetaTextField<K2Service, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"alias", // Alias
				"Alias", // Default label
				FieldHandlers.ALIAS.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				50, // The maximum number of characters in the field
				40 // The displayed size of the field
				);
		public static MetaField<K2Service, String> NAME = new MetaTextField<K2Service, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"name", // Alias
				"Name", // Default label
				FieldHandlers.NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				50, // The maximum number of characters in the field
				40 // The displayed size of the field
				);
		public static MetaField<K2Service, String> PACKAGE_NAME = new MetaTextField<K2Service, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"packageName", // Alias
				"Package name", // Default label
				FieldHandlers.PACKAGE_NAME.class, // Field handler class
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
				fields.put(ID.alias, ID); orderedFields.add(ID);
				fields.put(ALIAS.alias, ALIAS); orderedFields.add(ALIAS);
				fields.put(NAME.alias, NAME); orderedFields.add(NAME);
				fields.put(PACKAGE_NAME.alias, PACKAGE_NAME); orderedFields.add(PACKAGE_NAME);
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


	
	public static class FieldHandlers extends MetaFieldHandlers {
		
		public static class ID extends FieldHandler<K2Service, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class ALIAS extends FieldHandler<K2Service, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class NAME extends FieldHandler<K2Service, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class PACKAGE_NAME extends FieldHandler<K2Service, String> {
			public PACKAGE_NAME() { super(Fields.PACKAGE_NAME); }
			@Override public String get() { return entity.getPackageName(); }
			@Override public void set(String value) { entity.setPackageName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.NAME, Fields.PACKAGE_NAME);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<K2Service, Component> COMPONENTS = new MetaList<K2Service, Component>(
				true, // Enabled
				"components", // Alias
				"Components", // Default label
				ListHandlers.COMPONENTS.class, // List handler class
				ComponentController.class, // Stateless controller class
				MetaComponent.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Service, ImplementedExpression> SERVICE_EXPRESSIONS = new MetaList<K2Service, ImplementedExpression>(
				true, // Enabled
				"serviceExpressions", // Alias
				"Service expressions", // Default label
				ListHandlers.SERVICE_EXPRESSIONS.class, // List handler class
				ImplementedExpressionController.class, // Stateless controller class
				MetaImplementedExpression.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Service, Literal> LITERALS = new MetaList<K2Service, Literal>(
				true, // Enabled
				"literals", // Alias
				"Literals", // Default label
				ListHandlers.LITERALS.class, // List handler class
				LiteralController.class, // Stateless controller class
				MetaLiteral.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<K2Service, Project> PROJECTS = new MetaList<K2Service, Project>(
				false, // Enabled
				"projects", // Alias
				"Projects", // Default label
				ListHandlers.PROJECTS.class, // List handler class
				ProjectController.class, // Stateless controller class
				MetaProject.defaultFieldSet, // Default field set
				false // Allow new
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
				lists.put(COMPONENTS.alias, COMPONENTS); orderedLists.add(COMPONENTS);
				lists.put(SERVICE_EXPRESSIONS.alias, SERVICE_EXPRESSIONS); orderedLists.add(SERVICE_EXPRESSIONS);
				lists.put(LITERALS.alias, LITERALS); orderedLists.add(LITERALS);
				lists.put(PROJECTS.alias, PROJECTS); orderedLists.add(PROJECTS);
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
	

	public static class ListHandlers extends MetaListHandlers {
		
		public static class COMPONENTS extends ListHandler<K2Service, Component> {
			public COMPONENTS() { super(Lists.COMPONENTS); }
			@Override
			public ServiceList<Component> getServiceList() { return entity.getComponents(); }
		}
		
		public static class SERVICE_EXPRESSIONS extends ListHandler<K2Service, ImplementedExpression> {
			public SERVICE_EXPRESSIONS() { super(Lists.SERVICE_EXPRESSIONS); }
			@Override
			public ServiceList<ImplementedExpression> getServiceList() { return entity.getServiceExpressions(); }
		}
		
		public static class LITERALS extends ListHandler<K2Service, Literal> {
			public LITERALS() { super(Lists.LITERALS); }
			@Override
			public ServiceList<Literal> getServiceList() { return entity.getLiterals(); }
		}
		
		public static class PROJECTS extends ListHandler<K2Service, Project> {
			public PROJECTS() { super(Lists.PROJECTS); }
			@Override
			public ServiceList<Project> getServiceList() { return entity.getProjects(); }
		}
		
	}

	public static class Methods extends MetaMethods {
		public static MetaMethod<K2Service> BUILD_LITERALS = new MetaMethod<K2Service>(
				"buildLiterals", // Alias
				"Build Literals", // Default label
				"This method builds the service literals class in the working directory of the selected project", // Method description
				MethodHandlers.BUILD_LITERALS.class, // Method handler class
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
		public static MetaMethod<K2Service> BUILD_EXPRESSIONS = new MetaMethod<K2Service>(
				"buildExpressions", // Alias
				"Build Expressions", // Default label
				"This method builds the service expressions class in the working directory of the selected project", // Method description
				MethodHandlers.BUILD_EXPRESSIONS.class, // Method handler class
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
		public static MetaMethod<K2Service> BUILD_META_MODEL = new MetaMethod<K2Service>(
				"buildMetaModel", // Alias
				"Build Meta Model", // Default label
				"This method builds the meta model class in the working directory of the selected project", // Method description
				MethodHandlers.BUILD_META_MODEL.class, // Method handler class
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
				methods.put(BUILD_LITERALS.alias, BUILD_LITERALS); orderedMethods.add(BUILD_LITERALS);
				methods.put(BUILD_EXPRESSIONS.alias, BUILD_EXPRESSIONS); orderedMethods.add(BUILD_EXPRESSIONS);
				methods.put(BUILD_META_MODEL.alias, BUILD_META_MODEL); orderedMethods.add(BUILD_META_MODEL);
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

	public static class MethodHandlers extends MetaMethodHandlers {
		
		public static class BUILD_LITERALS extends MethodHandler<K2Service> {
			public BUILD_LITERALS() { super(Methods.BUILD_LITERALS); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.buildLiterals((Project)methodParms.get("project"));
				} else {
					Project project = null; if (args.length > 0) { project = (Project)args[0]; }
					entity.buildLiterals(project); 
				}
			}
		}

		public static class BUILD_EXPRESSIONS extends MethodHandler<K2Service> {
			public BUILD_EXPRESSIONS() { super(Methods.BUILD_EXPRESSIONS); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.buildExpressions((Project)methodParms.get("project"));
				} else {
					Project project = null; if (args.length > 0) { project = (Project)args[0]; }
					entity.buildExpressions(project); 
				}
			}
		}

		public static class BUILD_META_MODEL extends MethodHandler<K2Service> {
			public BUILD_META_MODEL() { super(Methods.BUILD_META_MODEL); }
			@SuppressWarnings("rawtypes")
			@Override
			public void execute(Object ... args) {
				if ((args.length > 0)&&(Map.class.isAssignableFrom(args[0].getClass()))) {
					Map methodParms = (Map)args[0];
					entity.buildMetaModel((Project)methodParms.get("project"));
				} else {
					Project project = null; if (args.length > 0) { project = (Project)args[0]; }
					entity.buildMetaModel(project); 
				}
			}
		}

	}


}
