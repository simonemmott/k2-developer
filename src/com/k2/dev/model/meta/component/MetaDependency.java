package com.k2.dev.model.meta.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.common.fieldSet.FieldSet;
import com.k2.common.interaction.FieldHandler;
import com.k2.common.interaction.ListHandler;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.common.meta.MetaFields;
import com.k2.common.meta.MetaLinkedField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaLists;
import com.k2.common.meta.MetaMethod;
import com.k2.common.meta.MetaMethodHandlers;
import com.k2.common.meta.MetaMethods;
import com.k2.common.meta.MetaListHandlers;
import com.k2.common.meta.MetaNumberField;
import com.k2.common.meta.MetaTextField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Field.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.Fields;
import com.k2.dev.model.meta.component.MetaK2Field.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.Lists;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2MethodService;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2MethodController;

@SuppressWarnings({"unused"})
public class MetaDependency implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<Dependency, Long> ID = new MetaNumberField<Dependency, Long>(
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
		public static MetaField<Dependency, K2Entity> ENTITY = new MetaLinkedField<Dependency, K2Entity>(
				K2Entity.class,  // Data type
				false, // Required
				true, // Enabled
				"entity", // Alias
				"Entity", // Default label 
				FieldHandlers.ENTITY.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.ENTITIES.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2EntityService.class // The class of the service object handling the linked entities
				);
		public static MetaField<Dependency, K2Method> METHOD = new MetaLinkedField<Dependency, K2Method>(
				K2Method.class,  // Data type
				false, // Required
				true, // Enabled
				"method", // Alias
				"Method", // Default label 
				FieldHandlers.METHOD.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.ENTITY_METHODS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2MethodService.class // The class of the service object handling the linked entities
				);
		public static MetaField<Dependency, String> DEPENDS_ON_PACKAGE_NAME = new MetaTextField<Dependency, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"dependesOnPackageName", // Alias
				"Package name", // Default label
				FieldHandlers.DEPENDS_ON_PACKAGE_NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				250, // The maximum number of characters in the field
				100 // The displayed size of the field
				);
		public static MetaField<Dependency, String> DEPENDS_ON_COMPONENT_NAME = new MetaTextField<Dependency, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"dependsOnClassName", // Alias
				"Class name", // Default label
				FieldHandlers.DEPENDS_ON_COMPONENT_NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				100, // The maximum number of characters in the field
				100 // The displayed size of the field
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
				fields.put(ENTITY.alias, ENTITY); orderedFields.add(ENTITY);
				fields.put(METHOD.alias, METHOD); orderedFields.add(METHOD);
				fields.put(DEPENDS_ON_PACKAGE_NAME.alias, DEPENDS_ON_PACKAGE_NAME); orderedFields.add(DEPENDS_ON_PACKAGE_NAME);
				fields.put(DEPENDS_ON_COMPONENT_NAME.alias, DEPENDS_ON_COMPONENT_NAME); orderedFields.add(DEPENDS_ON_COMPONENT_NAME);
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
		
		public static class ID extends FieldHandler<Dependency, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class ENTITY extends FieldHandler<Dependency, K2Entity> {
			public ENTITY() { super(Fields.ENTITY); }
			@Override public K2Entity get() { return entity.getK2Entity(); }
			@Override public void set(K2Entity value) { entity.setK2Entity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class METHOD extends FieldHandler<Dependency, K2Method> {
			public METHOD() { super(Fields.METHOD); }
			@Override public K2Method get() { return entity.getK2Method(); }
			@Override public void set(K2Method value) { entity.setK2Method(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class DEPENDS_ON_PACKAGE_NAME extends FieldHandler<Dependency, String> {
			public DEPENDS_ON_PACKAGE_NAME() { super(Fields.DEPENDS_ON_PACKAGE_NAME); }
			@Override public String get() { return entity.getDependsOnPackageName(); }
			@Override public void set(String value) { entity.setDependsOnPackageName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class DEPENDS_ON_COMPONENT_NAME extends FieldHandler<Dependency, String> {
			public DEPENDS_ON_COMPONENT_NAME() { super(Fields.DEPENDS_ON_COMPONENT_NAME); }
			@Override public String get() { return entity.getDependsOnComponentName(); }
			@Override public void set(String value) { entity.setDependsOnComponentName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.ENTITY, Fields.METHOD, Fields.DEPENDS_ON_PACKAGE_NAME, Fields.DEPENDS_ON_COMPONENT_NAME);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<Dependency, K2Entity> ENTITIES = new MetaList<Dependency, K2Entity>(
				false, // Enabled
				"entities", // Alias
				"Entities", // Default label
				ListHandlers.ENTITIES.class, // List handler class
				K2EntityController.class, // Stateless controller class
				MetaK2Entity.defaultFieldSet, // Default field set
				true // Allow new
				);

		public static MetaList<Dependency, K2Method> ENTITY_METHODS = new MetaList<Dependency, K2Method>(
				false, // Enabled
				"methods", // Alias
				"Methods", // Default label
				ListHandlers.ENTITY_METHODS.class, // List handler class
				K2MethodController.class, // Stateless controller class
				MetaK2Method.defaultFieldSet, // Default field set
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
				lists.put(ENTITIES.alias, ENTITIES); orderedLists.add(ENTITIES);
				lists.put(ENTITY_METHODS.alias, ENTITY_METHODS); orderedLists.add(ENTITY_METHODS);
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
		
		public static class ENTITIES extends ListHandler<Dependency, K2Entity> {
			public ENTITIES() { super(Lists.ENTITIES); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getEntities(); }
		}
		
		public static class ENTITY_METHODS extends ListHandler<Dependency, K2Method> {
			public ENTITY_METHODS() { super(Lists.ENTITY_METHODS); }
			@Override
			public ServiceList<K2Method> getServiceList() { return entity.getEntityMethods(); }
		}
				
	}

	public static class Methods extends MetaMethods {
		@SuppressWarnings("rawtypes")
		private static Map<String, MetaMethod> methods;
		@SuppressWarnings("rawtypes")
		private static List<MetaMethod> orderedMethods;
		@SuppressWarnings("rawtypes")
		public static List<MetaMethod> getMethods() {
			if (methods == null) {
				methods =  new HashMap<String, MetaMethod>();
				orderedMethods = new ArrayList<MetaMethod>();
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
		
	}


}
