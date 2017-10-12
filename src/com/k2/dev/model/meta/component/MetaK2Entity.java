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
import com.k2.common.util.StringUtil;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.Project;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaComponent.FieldHandlers;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2FieldController;

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
				// Text field settings
				null, // The maximum number of characters in the field
				null // Minimum value for the field
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
				null, // The maximum number of characters in the field
				null // Minimum value for the field
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
				null, // The maximum number of characters in the field
				null // Minimum value for the field
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
			}
			return orderedFields;
		}
		@SuppressWarnings("rawtypes")
		public static MetaField getMetaField(String alias) { getFields(); return fields.get(alias); }

	}
	
	public static class FieldHandlers extends MetaFieldHandlers {
		
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
		
	}

	public static FieldSet defaultFieldSet = new FieldSet(Fields.NAME, Fields.PACKAGE_NAME, Fields.ENTITYNAME, Fields.EXTENDS_ENTITY);
		
	public static class Lists extends MetaComponent.Lists {
		public static MetaList<K2Entity, K2Field> FIELDS = new MetaList<K2Entity, K2Field>(
				true, // Enabled
				"fields", // Alias
				"Fields", // Default label
				ListHandlers.FIELDS.class, // List handler class
				K2FieldController.class, // Stateless controller class
				new FieldSet(MetaK2Field.Fields.ENTITY, MetaK2Field.Fields.NAME, MetaK2Field.Fields.DATA_TYPE), // Default field set
				true // Allow new
				);
		public static MetaList<K2Entity, K2Entity> EXTENDABLE_ENTITIES = new MetaList<K2Entity, K2Entity>(
				true, // Enabled
				"extendableEntities", // Alias
				"Extendable entities", // Default label
				ListHandlers.EXTENDABLE_ENTITIES.class, // List handler class
				K2EntityController.class, // Stateless controller class
				defaultFieldSet, // Default field set
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
			}
			return orderedLists;
		}
		@SuppressWarnings("rawtypes")
		public static MetaList getMetaList(String alias) { getLists(); return lists.get(alias); }

	}
	

	
	
	
	public static class ListHandlers extends MetaListHandlers {
		
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
			}
			return orderedMethods;
		}
		@SuppressWarnings("rawtypes")
		public static MetaMethod getMetaMethod(String alias) { getMethods(); return methods.get(alias); }

	}
	
	public static class MethodHandlers extends MetaMethodHandlers {
		
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
		
	}
	



}
