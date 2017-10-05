package com.k2.dev.model.meta.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.common.fieldSet.FieldSet;
import com.k2.common.interaction.FieldHandler;
import com.k2.common.interaction.ListHandler;
import com.k2.common.meta.MetaBooleanField;
import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.common.meta.MetaFields;
import com.k2.common.meta.MetaLinkedField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaLists;
import com.k2.common.meta.MetaListHandlers;
import com.k2.common.meta.MetaNumberField;
import com.k2.common.meta.MetaTextField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.IntegerUtil;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Fields;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.web.stateless.K2EntityController;

@SuppressWarnings({"unused"})
public class MetaK2Field {
	
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
				20, // The maximum number of characters in the field
				40 // The displayed size of the field
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
		public static MetaField<K2Field, String> DATA_TYPE = new MetaTextField<K2Field, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"dataType", // Alias
				"Date type", // Default label
				FieldHandlers.DATA_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				null, // The maximum number of characters in the field
				null // The displayed size of the field
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
				fields.put(NAME.alias, NAME); orderedFields.add(NAME);
				fields.put(ENTITY.alias, ENTITY); orderedFields.add(ENTITY);
				fields.put(COLUMN_LENGTH.alias, COLUMN_LENGTH); orderedFields.add(COLUMN_LENGTH);
				fields.put(DATA_TYPE.alias, DATA_TYPE); orderedFields.add(DATA_TYPE);
				fields.put(COLUMN_NAME.alias, COLUMN_NAME); orderedFields.add(COLUMN_NAME);
				fields.put(NULLABLE.alias, NULLABLE); orderedFields.add(NULLABLE);
			}
			return orderedFields;
		}
		@SuppressWarnings("rawtypes")
		public static MetaField getMetaField(String alias) { getFields(); return fields.get(alias); }
	}
	
	public static class FieldHandlers extends MetaFieldHandlers {
		
		public static class ID extends FieldHandler<K2Field, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getID(); }
			@Override public void set(Long value) { entity.setID(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
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
		
		public static class COLUMN_LENGTH extends FieldHandler<K2Field, Integer> {
			public COLUMN_LENGTH() { super(Fields.COLUMN_LENGTH); }
			@Override public Integer get() { return entity.getColumnLength(); }
			@Override public void set(Integer value) { entity.setColumnLength(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toInteger(value)); }
			@Override public String getForUI() { return IntegerUtil.toString(get()); }
		}
		
		public static class DATA_TYPE extends FieldHandler<K2Field, String> {
			public DATA_TYPE() { super(Fields.DATA_TYPE); }
			@Override public String get() { return entity.getDataType(); }
			@Override public void set(String value) { entity.setDataType(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
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

	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.ENTITY, Fields.NAME, Fields.DATA_TYPE);
	
	public static class Lists extends MetaLists {
		public static MetaList<K2Field, K2Entity> ENTITY = new MetaList<K2Field, K2Entity>(
				true, // Enabled
				"extendableEntities", // Alias
				"Extendable entities", // Default label
				ListHandlers.ENTITY.class, // List handler class
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
				lists.put(ENTITY.alias, ENTITY); orderedLists.add(ENTITY);
			}

			return orderedLists;
		}
		@SuppressWarnings("rawtypes")
		public static MetaList getMetaList(String alias) { getLists(); return lists.get(alias); }
	}
	

	public static class ListHandlers extends MetaListHandlers {
		public static class ENTITY extends ListHandler<K2Field, K2Entity> {
			public ENTITY() { super(Lists.ENTITY); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getEntities(); }
		}
		

	}


}
