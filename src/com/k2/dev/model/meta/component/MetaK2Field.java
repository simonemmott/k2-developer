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
import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaModelEntity;
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
import com.k2.common.meta.MetaTypeField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.IntegerUtil;
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2FieldENT.Types;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Fields;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.model.meta.component.MetaK2Entity.MethodHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Methods;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.web.stateless.K2EntityController;

@SuppressWarnings({"unused"})
public class MetaK2Field implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<K2Field, Long> ID = new MetaNumberField<K2Field, Long>(
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
		public static MetaField<K2Field, String> ALIAS = new MetaTextField<K2Field, String>(
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
				30, // The maximum number of characters in the field
				30 // The displayed size of the field
				);
		public static MetaField<K2Field, String> NAME = new MetaTextField<K2Field, String>(
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
				50 // The displayed size of the field
				);
		public static MetaField<K2Field, K2Entity> ENTITY = new MetaLinkedField<K2Field, K2Entity>(
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
		public static MetaField<K2Field, Types.FieldType> FIELD_TYPE = new MetaTypeField<K2Field, Types.FieldType>(
				Types.FieldType.class, // Data type
				true, // Required
				false, // Enabled
				"fieldType", // Alias
				"Field type", // Default label
				FieldHandlers.FIELD_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.FieldType.values() // The class defining the type values
				);
		public static MetaField<K2Field, Integer> COLUMN_LENGTH = new MetaNumberField<K2Field, Integer>(
				Integer.class, // Data type
				true, // Required
				true, // Enabled
				"columnLength", // Alias
				"Column length", // Default label
				FieldHandlers.COLUMN_LENGTH.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Number field settings
				null, // The displayed size of the field
				null, // Maximum value for the field
				null // Minimum value for the field
				);
		public static MetaField<K2Field, String> COLUMN_NAME = new MetaTextField<K2Field, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"columnName", // Alias
				"Column name", // Default label
				FieldHandlers.COLUMN_NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				null, // The maximum number of characters in the field
				null // The displayed size of the field
				);
		public static MetaField<K2Field, Boolean> NULLABLE = new MetaBooleanField<K2Field, Boolean>(
				Boolean.class, // Data type
				true, // Required
				true, // Enabled
				"nullable", // Alias
				"Nullable", // Default label
				FieldHandlers.NULLABLE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null // Default right caption
				// Boolean field settings
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
				fields.put(FIELD_TYPE.alias, FIELD_TYPE); orderedFields.add(FIELD_TYPE);
				fields.put(COLUMN_LENGTH.alias, COLUMN_LENGTH); orderedFields.add(COLUMN_LENGTH);
				fields.put(COLUMN_NAME.alias, COLUMN_NAME); orderedFields.add(COLUMN_NAME);
				fields.put(NULLABLE.alias, NULLABLE); orderedFields.add(NULLABLE);
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
		
		public static class ID extends FieldHandler<K2Field, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class ALIAS extends FieldHandler<K2Field, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class NAME extends FieldHandler<K2Field, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class ENTITY extends FieldHandler<K2Field, K2Entity> {
			public ENTITY() { super(Fields.ENTITY); }
			@Override public K2Entity get() { return entity.getK2Entity(); }
			@Override public void set(K2Entity value) { entity.setK2Entity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class FIELD_TYPE extends FieldHandler<K2Field, Types.FieldType> {
			public FIELD_TYPE() { super(Fields.FIELD_TYPE); }
			@Override public Types.FieldType get() { return entity.getFieldType(); }
			@Override public void set(Types.FieldType value) { }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class COLUMN_LENGTH extends FieldHandler<K2Field, Integer> {
			public COLUMN_LENGTH() { super(Fields.COLUMN_LENGTH); }
			@Override public Integer get() { return entity.getColumnLength(); }
			@Override public void set(Integer value) { entity.setColumnLength(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toInteger(value)); }
			@Override public String getForUI() { return IntegerUtil.toString(get()); }
		}
		
		public static class COLUMN_NAME extends FieldHandler<K2Field, String> {
			public COLUMN_NAME() { super(Fields.COLUMN_NAME); }
			@Override public String get() { return entity.getColumnName(); }
			@Override public void set(String value) { entity.setColumnName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class NULLABLE extends FieldHandler<K2Field, Boolean> {
			public NULLABLE() { super(Fields.NULLABLE); }
			@Override public Boolean get() { return entity.getNullable(); }
			@Override public void set(Boolean value) { entity.setNullable(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toBoolean(value)); }
			@Override public String getForUI() { return BooleanUtil.toString(get()); }
		}
		
		
	}

	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.ENTITY, Fields.NAME, Fields.FIELD_TYPE);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }
	
	public static class Lists extends MetaLists {
		public static MetaList<K2Field, K2Entity> ENTITY = new MetaList<K2Field, K2Entity>(
				false, // Enabled
				"extendableEntities", // Alias
				"Extendable entities", // Default label
				ListHandlers.ENTITY.class, // List handler class
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
				lists.put(ENTITY.alias, ENTITY); orderedLists.add(ENTITY);
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
		public static class ENTITY extends ListHandler<K2Field, K2Entity> {
			public ENTITY() { super(Lists.ENTITY); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getEntities(); }
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
				for (MetaMethod metaMethod : MetaComponent.Methods.getMethods()) {
					methods.put(metaMethod.alias, metaMethod); orderedMethods.add(metaMethod);
				}
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
