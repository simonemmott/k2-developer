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
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.ExpressionENT.Types;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.model.meta.component.MetaK2NativeField.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2NativeField.Fields;
import com.k2.dev.web.stateless.ExpressionParameterController;
import com.k2.dev.web.stateless.K2FieldController;

@SuppressWarnings({"unused"})
public class MetaExpression implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<Expression, Long> ID = new MetaNumberField<Expression, Long>(
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
		public static MetaField<Expression, Types.ReturnType> RETURN_TYPE = new MetaTypeField<Expression, Types.ReturnType>(
				Types.ReturnType.class, // Data type
				true, // Required
				true, // Enabled
				"returnType", // Alias
				"Return type", // Default label
				FieldHandlers.RETURN_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.ReturnType.values() // The class defining the type values
				);
		public static MetaField<Expression, String> ALIAS = new MetaTextField<Expression, String>(
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
		public static MetaField<Expression, Types.ExpressionType> EXPRESSION_TYPE = new MetaTypeField<Expression, Types.ExpressionType>(
				Types.ExpressionType.class, // Data type
				true, // Required
				false, // Enabled
				"expressionType", // Alias
				"Expression type", // Default label
				FieldHandlers.EXPRESSION_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.ExpressionType.values() // The class defining the type values
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
				fields.put(RETURN_TYPE.alias, RETURN_TYPE); orderedFields.add(RETURN_TYPE);
				fields.put(ALIAS.alias, ALIAS); orderedFields.add(ALIAS);
				fields.put(EXPRESSION_TYPE.alias, EXPRESSION_TYPE); orderedFields.add(EXPRESSION_TYPE);
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
		
		public static class ID extends FieldHandler<Expression, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class RETURN_TYPE extends FieldHandler<Expression, Types.ReturnType> {
			public RETURN_TYPE() { super(Fields.RETURN_TYPE); }
			@Override public Types.ReturnType get() { return entity.getReturnType(); }
			@Override public void set(Types.ReturnType value) { entity.setReturnType(value); }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.ReturnType.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class ALIAS extends FieldHandler<Expression, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class EXPRESSION_TYPE extends FieldHandler<Expression, Types.ExpressionType> {
			public EXPRESSION_TYPE() { super(Fields.EXPRESSION_TYPE); }
			@Override public Types.ExpressionType get() { return entity.getExpressionType(); }
			@Override public void set(Types.ExpressionType value) { }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.EXPRESSION_TYPE, Fields.ALIAS, Fields.RETURN_TYPE);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<Expression, ExpressionParameter> PARAMETERS = new MetaList<Expression, ExpressionParameter>(
				true, // Enabled
				"parameters", // Alias
				"Parameters", // Default label
				ListHandlers.PARAMETERS.class, // List handler class
				ExpressionParameterController.class, // Stateless controller class
				MetaExpressionParameter.defaultFieldSet, // Default field set
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
				lists.put(PARAMETERS.alias, PARAMETERS); orderedLists.add(PARAMETERS);
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
		
		public static class PARAMETERS extends ListHandler<Expression, ExpressionParameter> {
			public PARAMETERS() { super(Lists.PARAMETERS); }
			@Override
			public ServiceList<ExpressionParameter> getServiceList() { return entity.getParameters(); }
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
