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
import com.k2.common.meta.MetaBooleanField;
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
import com.k2.common.meta.MetaTypeField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.ExpressionParameterENT.Types;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaExpression.Lists;
import com.k2.dev.model.meta.component.MetaK2Entity.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Fields;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.service.ExpressionService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.web.stateless.ExpressionController;
import com.k2.dev.web.stateless.ExpressionParameterController;

@SuppressWarnings({"unused"})
public class MetaExpressionParameter implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<ExpressionParameter, Long> ID = new MetaNumberField<ExpressionParameter, Long>(
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
		public static MetaField<ExpressionParameter, Expression> EXPRESSION = new MetaLinkedField<ExpressionParameter, Expression>(
				Expression.class,  // Data type
				true, // Required
				true, // Enabled
				"expression", // Alias
				"Expression", // Default label 
				FieldHandlers.EXPRESSION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.EXPRESSIONS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ExpressionService.class // The class of the service object handling the linked entities
				);
		public static MetaField<ExpressionParameter, Types.ParameterDataType> DATATYPE = new MetaTypeField<ExpressionParameter, Types.ParameterDataType>(
				Types.ParameterDataType.class, // Data type
				true, // Required
				true, // Enabled
				"dataType", // Alias
				"Data type", // Default label
				FieldHandlers.DATATYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.ParameterDataType.values() // The class defining the type values
				);
		public static MetaField<ExpressionParameter, String> ALIAS = new MetaTextField<ExpressionParameter, String>(
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
		public static MetaField<ExpressionParameter, Boolean> REPEATING = new MetaBooleanField<ExpressionParameter, Boolean>(
				Boolean.class, // Data type
				false, // Required
				true, // Enabled
				"repeating", // Alias
				"Repeating", // Default label
				FieldHandlers.REPEATING.class, // Field handler class
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
				fields.put(EXPRESSION.alias, EXPRESSION); orderedFields.add(EXPRESSION);
				fields.put(DATATYPE.alias, DATATYPE); orderedFields.add(DATATYPE);
				fields.put(ALIAS.alias, ALIAS); orderedFields.add(ALIAS);
				fields.put(REPEATING.alias, REPEATING); orderedFields.add(REPEATING);
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
		
		public static class ID extends FieldHandler<ExpressionParameter, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class EXPRESSION extends FieldHandler<ExpressionParameter, Expression> {
			public EXPRESSION() { super(Fields.EXPRESSION); }
			@Override public Expression get() { return entity.getExpression(); }
			@Override public void set(Expression value) { entity.setExpression(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class DATATYPE extends FieldHandler<ExpressionParameter, Types.ParameterDataType> {
			public DATATYPE() { super(Fields.DATATYPE); }
			@Override public Types.ParameterDataType get() { return entity.getDataType(); }
			@Override public void set(Types.ParameterDataType value) { entity.setDataType(value); }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.ParameterDataType.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class ALIAS extends FieldHandler<ExpressionParameter, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class REPEATING extends FieldHandler<ExpressionParameter, Boolean> {
			public REPEATING() { super(Fields.REPEATING); }
			@Override public Boolean get() { return entity.getRepeating(); }
			@Override public void set(Boolean value) { entity.setRepeating(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toBoolean(value)); }
			@Override public String getForUI() { return BooleanUtil.toString(get()); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.EXPRESSION, Fields.ALIAS, Fields.DATATYPE);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<ExpressionParameter, Expression> EXPRESSIONS = new MetaList<ExpressionParameter, Expression>(
				false, // Enabled
				"expressions", // Alias
				"Expressions", // Default label
				ListHandlers.EXPRESSIONS.class, // List handler class
				ExpressionController.class, // Stateless controller class
				MetaExpression.defaultFieldSet, // Default field set
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
			}
			lists.put(EXPRESSIONS.alias, EXPRESSIONS); orderedLists.add(EXPRESSIONS);
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
		
		public static class EXPRESSIONS extends ListHandler<ExpressionParameter, Expression> {
			public EXPRESSIONS() { super(Lists.EXPRESSIONS); }
			@Override
			public ServiceList<Expression> getServiceList() { return entity.getExpressions(); }
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
