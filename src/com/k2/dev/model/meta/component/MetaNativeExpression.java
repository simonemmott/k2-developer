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
import com.k2.common.meta.MetaLists;
import com.k2.common.meta.MetaMethod;
import com.k2.common.meta.MetaMethodHandlers;
import com.k2.common.meta.MetaMethodParameter;
import com.k2.common.meta.MetaMethodParameters;
import com.k2.common.meta.MetaTextField;
import com.k2.common.meta.MetaTypeField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlLinkedField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.K2Type;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2List;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.NativeExpression;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.entity.NativeExpressionENT.Types;
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
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.K2ListController;
import com.k2.dev.web.stateless.K2MethodController;
import com.k2.dev.web.stateless.K2TypeDefController;
import com.k2.dev.web.stateless.K2TypeValueController;
import com.k2.dev.web.stateless.ProjectController;

@SuppressWarnings({"unused"})
public class MetaNativeExpression extends MetaExpression {
	
	public static class Fields extends MetaExpression.Fields {
		public static MetaField<NativeExpression, Types.NativeExpressionTypes> NATIVE_EXPRESSION_TYPE = new MetaTypeField<NativeExpression, Types.NativeExpressionTypes>(
				Types.NativeExpressionTypes.class, // Data type
				true, // Required
				true, // Enabled
				"nativeExpressionType", // Alias
				"Native expression type", // Default label
				FieldHandlers.NATIVE_EXPRESSION_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.NativeExpressionTypes.values() // The class defining the type values
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
				for (MetaField metaField : MetaExpression.Fields.getFields()) {
					fields.put(metaField.alias, metaField); orderedFields.add(metaField);
				}
				fields.put(NATIVE_EXPRESSION_TYPE.alias, NATIVE_EXPRESSION_TYPE); orderedFields.add(NATIVE_EXPRESSION_TYPE);
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
	
	public static class FieldHandlers extends MetaExpression.FieldHandlers {
		
		public static class NATIVE_EXPRESSION_TYPE extends FieldHandler<NativeExpression, Types.NativeExpressionTypes> {
			public NATIVE_EXPRESSION_TYPE() { super(Fields.NATIVE_EXPRESSION_TYPE); }
			@Override public Types.NativeExpressionTypes get() { return entity.getNativeExpressionType(); }
			@Override public void set(Types.NativeExpressionTypes value) { entity.setNativeExpressionType(value);; }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.NativeExpressionTypes.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
	}

	public static FieldSet defaultFieldSet = new FieldSet(Fields.EXPRESSION_TYPE, Fields.ALIAS, Fields.RETURN_TYPE, Fields.NATIVE_EXPRESSION_TYPE);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }
		
	public static class Lists extends MetaLists {
		@SuppressWarnings("rawtypes")
		private static Map<String, MetaList> lists; 
		@SuppressWarnings("rawtypes")
		private static List<MetaList> orderedLists;
		@SuppressWarnings("rawtypes")
		public static List<MetaList> getLists() {
			if (lists == null) {
				lists =  new HashMap<String, MetaList>();
				orderedLists = new ArrayList<MetaList>();
				for (MetaList metaList : MetaExpression.Lists.getLists()) {
					lists.put(metaList.alias, metaList); orderedLists.add(metaList);
				}
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
	
	public static class ListHandlers extends MetaExpression.ListHandlers {
		
	}
	
	public static class Methods extends MetaExpression.Methods {
		@SuppressWarnings("rawtypes")
		private static Map<String, MetaMethod> methods; 
		@SuppressWarnings("rawtypes")
		private static List<MetaMethod> orderedMethods;
		@SuppressWarnings("rawtypes")
		public static List<MetaMethod> getMethods() {
			if (methods == null) {
				methods =  new HashMap<String, MetaMethod>();
				orderedMethods = new ArrayList<MetaMethod>();
				for (MetaMethod metaMethod : MetaExpression.Methods.getMethods()) {
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
	
	public static class MethodHandlers extends MetaExpression.MethodHandlers {
		
		
	}
	



}
