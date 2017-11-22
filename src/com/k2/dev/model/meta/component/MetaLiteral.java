package com.k2.dev.model.meta.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.k2.common.meta.MetaBooleanField;
import com.k2.common.meta.MetaDateField;
import com.k2.common.meta.MetaDecimalField;
import com.k2.common.meta.MetaTextField;
import com.k2.common.meta.MetaTypeField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.DateUtil;
import com.k2.common.util.DoubleUtil;
import com.k2.common.util.IntegerUtil;
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.entity.LiteralENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Field.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.Lists;
import com.k2.dev.model.meta.component.MetaK2NativeField.Fields;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2ServiceService;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2ServiceController;

@SuppressWarnings({"unused"})
public class MetaLiteral implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<Literal, Long> ID = new MetaNumberField<Literal, Long>(
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
		public static MetaField<Literal, K2Service> SERVICE = new MetaLinkedField<Literal, K2Service>(
				K2Service.class,  // Data type
				true, // Required
				true, // Enabled
				"k2Service", // Alias
				"Service", // Default label 
				FieldHandlers.SERVICE.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.SERVICES.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2ServiceService.class // The class of the service object handling the linked entities
				);
		public static MetaField<Literal, String> ALIAS = new MetaTextField<Literal, String>(
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
				30 // The displayed size of the field
				);
		public static MetaField<Literal, Types.LiteralDataType> DATA_TYPE = new MetaTypeField<Literal, Types.LiteralDataType>(
				Types.LiteralDataType.class, // Data type
				true, // Required
				true, // Enabled
				"dataType", // Alias
				"Data type", // Default label
				FieldHandlers.DATA_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.LiteralDataType.values() // The class defining the type values
				);
		public static MetaField<Literal, Long> NUMERIC_VALUE = new MetaNumberField<Literal, Long>(
				Long.class, // Data type
				false, // Required
				true, // Enabled
				"numericValue", // Alias
				"Numeric value", // Default label
				FieldHandlers.NUMERIC_VALUE.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Number field settings
				null, // The displayed size of the field
				null, // Maximum value for the field
				null // Minimum value for the field
				);
		public static MetaField<Literal, Double> DECIMAL_VALUE = new MetaDecimalField<Literal, Double>(
				Double.class, // Data type
				false, // Required
				true, // Enabled
				"decimalValue", // Alias
				"Decimal value", // Default label
				FieldHandlers.DECIMAL_VALUE.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Decimal field settings
				null, // The displayed size of the field
				null, // Maximum value for the field
				null // Minimum value for the field
				);
		public static MetaField<Literal, Boolean> BOOLEAN_VALUE = new MetaBooleanField<Literal, Boolean>(
				Boolean.class, // Data type
				false, // Required
				true, // Enabled
				"booleanValue", // Alias
				"Boolean value", // Default label
				FieldHandlers.BOOLEAN_VALUE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null // Default right caption
				// Boolean field settings
				);
		public static MetaField<Literal, Date> DATE_VALUE = new MetaDateField<Literal, Date>(
				Date.class, // Data type
				false, // Required
				true, // Enabled
				"dateValue", // Alias
				"Date value", // Default label
				FieldHandlers.DATE_VALUE.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null // Default right caption
				// Date field settings
				);
		public static MetaField<Literal, String> TEXT_VALUE = new MetaTextField<Literal, String>(
				String.class, // Data type
				false, // Required
				true, // Enabled
				"textValue", // Alias
				"Text value", // Default label
				FieldHandlers.TEXT_VALUE.class, // Field handler class
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
				fields.put(SERVICE.alias, SERVICE); orderedFields.add(SERVICE);
				fields.put(ALIAS.alias, ALIAS); orderedFields.add(ALIAS);
				fields.put(DATA_TYPE.alias, DATA_TYPE); orderedFields.add(DATA_TYPE);
				fields.put(NUMERIC_VALUE.alias, NUMERIC_VALUE); orderedFields.add(NUMERIC_VALUE);
				fields.put(DECIMAL_VALUE.alias, DECIMAL_VALUE); orderedFields.add(DECIMAL_VALUE);
				fields.put(BOOLEAN_VALUE.alias, BOOLEAN_VALUE); orderedFields.add(BOOLEAN_VALUE);
				fields.put(DATE_VALUE.alias, DATE_VALUE); orderedFields.add(DATE_VALUE);
				fields.put(TEXT_VALUE.alias, TEXT_VALUE); orderedFields.add(TEXT_VALUE);
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
		
		public static class ID extends FieldHandler<Literal, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class SERVICE extends FieldHandler<Literal, K2Service> {
			public SERVICE() { super(Fields.SERVICE); }
			@Override public K2Service get() { return entity.getK2Service(); }
			@Override public void set(K2Service value) { entity.setK2Service(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class ALIAS extends FieldHandler<Literal, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class DATA_TYPE extends FieldHandler<Literal, Types.LiteralDataType> {
			public DATA_TYPE() { super(Fields.DATA_TYPE); }
			@Override public Types.LiteralDataType get() { return entity.getDataType(); }
			@Override public void set(Types.LiteralDataType value) { entity.setDataType(value); }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.LiteralDataType.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class NUMERIC_VALUE extends FieldHandler<Literal, Long> {
			public NUMERIC_VALUE() { super(Fields.NUMERIC_VALUE); }
			@Override public Long get() { return entity.getNumericValue(); }
			@Override public void set(Long value) { entity.setNumericValue(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class DECIMAL_VALUE extends FieldHandler<Literal, Double> {
			public DECIMAL_VALUE() { super(Fields.DECIMAL_VALUE); }
			@Override public Double get() { return entity.getDecimalValue(); }
			@Override public void set(Double value) { entity.setDecimalValue(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toDouble(value)); }
			@Override public String getForUI() { return DoubleUtil.toString(get()); }
		}
		
		public static class BOOLEAN_VALUE extends FieldHandler<Literal, Boolean> {
			public BOOLEAN_VALUE() { super(Fields.BOOLEAN_VALUE); }
			@Override public Boolean get() { return entity.getBooleanValue(); }
			@Override public void set(Boolean value) { entity.setBooleanValue(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toBoolean(value)); }
			@Override public String getForUI() { return BooleanUtil.toString(get()); }
		}
		
		public static class DATE_VALUE extends FieldHandler<Literal, Date> {
			public DATE_VALUE() { super(Fields.DATE_VALUE); }
			@Override public Date get() { return entity.getDateValue(); }
			@Override public void set(Date value) { entity.setDateValue(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toDate(value)); }
			@Override public String getForUI() { return DateUtil.toString(get()); }
		}
		
		public static class TEXT_VALUE extends FieldHandler<Literal, String> {
			public TEXT_VALUE() { super(Fields.TEXT_VALUE); }
			@Override public String get() { return entity.getTextValue(); }
			@Override public void set(String value) { entity.setTextValue(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.SERVICE, Fields.ALIAS);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<Literal, K2Service> SERVICES = new MetaList<Literal, K2Service>(
				false, // Enabled
				"services", // Alias
				"Services", // Default label
				ListHandlers.SERVICES.class, // List handler class
				K2ServiceController.class, // Stateless controller class
				MetaK2Service.defaultFieldSet, // Default field set
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
				lists.put(SERVICES.alias, SERVICES); orderedLists.add(SERVICES);
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
		
		public static class SERVICES extends ListHandler<Literal, K2Service> {
			public SERVICES() { super(Lists.SERVICES); }
			@Override
			public ServiceList<K2Service> getServiceList() { return entity.getServices(); }
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
