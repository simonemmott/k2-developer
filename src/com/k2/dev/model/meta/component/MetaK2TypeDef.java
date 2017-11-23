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
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Field.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.Fields;
import com.k2.dev.model.meta.component.MetaK2Field.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.Lists;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2TypeValueController;

@SuppressWarnings({"unused"})
public class MetaK2TypeDef implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<K2TypeDef, Long> ID = new MetaNumberField<K2TypeDef, Long>(
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
		public static MetaField<K2TypeDef, String> ALIAS = new MetaTextField<K2TypeDef, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"alias", // Alias
				"Alias", // Default label
				FieldHandlers.ALIAS.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settinfgs
				50, // The maximum number of characters in the field
				40 // The displayed size of the field
				);
		public static MetaField<K2TypeDef, String> NAME = new MetaTextField<K2TypeDef, String>(
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
		public static MetaField<K2TypeDef, K2Entity> ENTITY = new MetaLinkedField<K2TypeDef, K2Entity>(
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
				ListHandlers.ENTITY.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2EntityService.class // The class of the service object handling the linked entities
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
				fields.put(ENTITY.alias, ENTITY); orderedFields.add(ENTITY);
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
		
		public static class ID extends FieldHandler<K2TypeDef, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class NAME extends FieldHandler<K2TypeDef, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class ALIAS extends FieldHandler<K2TypeDef, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class ENTITY extends FieldHandler<K2TypeDef, K2Entity> {
			public ENTITY() { super(Fields.ENTITY); }
			@Override public K2Entity get() { return entity.getK2Entity(); }
			@Override public void set(K2Entity value) { entity.setK2Entity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.ALIAS, Fields.NAME);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {

		public static MetaList<K2TypeDef, K2Entity> ENTITY = new MetaList<K2TypeDef, K2Entity>(
				false, // Enabled
				"Entities", // Alias
				"Entities", // Default label
				ListHandlers.ENTITY.class, // List handler class
				K2EntityController.class, // Stateless controller class
				MetaK2Entity.defaultFieldSet, // Default field set
				true // Allow new
				);

		public static MetaList<K2TypeDef, K2TypeValue> TYPE_VALUES = new MetaList<K2TypeDef, K2TypeValue>(
				true, // Enabled
				"typeValues", // Alias
				"Values", // Default label
				ListHandlers.TYPE_VALUES.class, // List handler class
				K2TypeValueController.class, // Stateless controller class
				MetaK2TypeValue.defaultFieldSet, // Default field set
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
				lists.put(ENTITY.alias, ENTITY); orderedLists.add(ENTITY);
				lists.put(TYPE_VALUES.alias, TYPE_VALUES); orderedLists.add(TYPE_VALUES);
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
		
		public static class ENTITY extends ListHandler<K2TypeDef, K2Entity> {
			public ENTITY() { super(Lists.ENTITY); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getEntities(); }
		}
		
		public static class TYPE_VALUES extends ListHandler<K2TypeDef, K2TypeValue> {
			public TYPE_VALUES() { super(Lists.TYPE_VALUES); }
			@Override
			public ServiceList<K2TypeValue> getServiceList() { return entity.getTypeValues(); }
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
