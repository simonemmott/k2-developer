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
import com.k2.common.meta.MetaTypeField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.Component;
import com.k2.dev.model.ExpressionParameter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.BoundExpressionParameterENT.Types;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.Fields;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.model.meta.component.MetaK2Field.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2NativeField.FieldHandlers;
import com.k2.dev.service.ExpressionParameterService;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.LiteralService;
import com.k2.dev.web.stateless.ExpressionParameterController;
import com.k2.dev.web.stateless.ImplementedExpressionController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.LiteralController;

@SuppressWarnings({"unused"})
public class MetaBoundExpressionParameter implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<BoundExpressionParameter, Long> ID = new MetaNumberField<BoundExpressionParameter, Long>(
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
		public static MetaField<BoundExpressionParameter, ImplementedExpression> IMPLEMENTED_EXPRESSION = new MetaLinkedField<BoundExpressionParameter, ImplementedExpression>(
				ImplementedExpression.class,  // Data type
				true, // Required
				true, // Enabled
				"expression", // Alias
				"Expression", // Default label 
				FieldHandlers.IMPLEMENTED_EXPRESSION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.IMPLEMENTABLE_EXPRESSIONS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ImplementedExpressionService.class // The class of the service object handling the linked entities
				);
		public static MetaField<BoundExpressionParameter, Types.ExpressionParameterBindingSource> BINDING_SOURCE = new MetaTypeField<BoundExpressionParameter, Types.ExpressionParameterBindingSource>(
				Types.ExpressionParameterBindingSource.class, // Data type
				true, // Required
				true, // Enabled
				"bindingSource", // Alias
				"Binding source", // Default label
				FieldHandlers.BINDING_SOURCE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.ExpressionParameterBindingSource.values() // The class defining the type values
				);
		public static MetaField<BoundExpressionParameter, Literal> BOUND_LITERAL = new MetaLinkedField<BoundExpressionParameter, Literal>(
				Literal.class,  // Data type
				false, // Required
				true, // Enabled
				"boundLiteral", // Alias
				"Bound literal", // Default label 
				FieldHandlers.BOUND_LITERAL.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.LITERALS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				LiteralService.class // The class of the service object handling the linked entities
				);
		public static MetaField<BoundExpressionParameter, ImplementedExpression> BOUND_EXPRESSION = new MetaLinkedField<BoundExpressionParameter, ImplementedExpression>(
				ImplementedExpression.class,  // Data type
				false, // Required
				true, // Enabled
				"boundExpression", // Alias
				"Bound expression", // Default label 
				FieldHandlers.BOUND_EXPRESSION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.BOUND_EXPRESSIONS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ImplementedExpressionService.class // The class of the service object handling the linked entities
				);
		public static MetaField<BoundExpressionParameter, K2Field> BOUND_FIELD = new MetaLinkedField<BoundExpressionParameter, K2Field>(
				K2Field.class,  // Data type
				false, // Required
				true, // Enabled
				"boundField", // Alias
				"Bound field", // Default label 
				FieldHandlers.BOUND_FIELD.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.BINDABLE_FIELDS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2FieldService.class // The class of the service object handling the linked entities
				);
		public static MetaField<BoundExpressionParameter, ExpressionParameter> BOUND_PARAMETER = new MetaLinkedField<BoundExpressionParameter, ExpressionParameter>(
				ExpressionParameter.class,  // Data type
				false, // Required
				true, // Enabled
				"boundParameter", // Alias
				"Bound parameter", // Default label 
				FieldHandlers.BOUND_PARAMETER.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.EXPRESSION_PARAMETERS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ExpressionParameterService.class // The class of the service object handling the linked entities
				);
		public static MetaField<BoundExpressionParameter, ExpressionParameter> BINDS_PARAMETER = new MetaLinkedField<BoundExpressionParameter, ExpressionParameter>(
				ExpressionParameter.class,  // Data type
				true, // Required
				true, // Enabled
				"bindsParameter", // Alias
				"Binds parameter", // Default label 
				FieldHandlers.BINDS_PARAMETER.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.IMPLEMENTED_EXPRESSION_PARAMETERS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ExpressionParameterService.class // The class of the service object handling the linked entities
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
				fields.put(IMPLEMENTED_EXPRESSION.alias, IMPLEMENTED_EXPRESSION); orderedFields.add(IMPLEMENTED_EXPRESSION);
				fields.put(BINDING_SOURCE.alias, BINDING_SOURCE); orderedFields.add(BINDING_SOURCE);
				fields.put(BOUND_LITERAL.alias, BOUND_LITERAL); orderedFields.add(BOUND_LITERAL);
				fields.put(BOUND_EXPRESSION.alias, BOUND_EXPRESSION); orderedFields.add(BOUND_EXPRESSION);
				fields.put(BOUND_FIELD.alias, BOUND_FIELD); orderedFields.add(BOUND_FIELD);
				fields.put(BOUND_PARAMETER.alias, BOUND_PARAMETER); orderedFields.add(BOUND_PARAMETER);
				fields.put(BINDS_PARAMETER.alias, BINDS_PARAMETER); orderedFields.add(BINDS_PARAMETER);
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
		
		public static class ID extends FieldHandler<BoundExpressionParameter, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class IMPLEMENTED_EXPRESSION extends FieldHandler<BoundExpressionParameter, ImplementedExpression> {
			public IMPLEMENTED_EXPRESSION() { super(Fields.IMPLEMENTED_EXPRESSION); }
			@Override public ImplementedExpression get() { return entity.getExpression(); }
			@Override public void set(ImplementedExpression value) { entity.setExpression(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class BINDING_SOURCE extends FieldHandler<BoundExpressionParameter, Types.ExpressionParameterBindingSource> {
			public BINDING_SOURCE() { super(Fields.BINDING_SOURCE); }
			@Override public Types.ExpressionParameterBindingSource get() { return entity.getBindingSource(); }
			@Override public void set(Types.ExpressionParameterBindingSource value) { entity.setBindingSource(value);; }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.ExpressionParameterBindingSource.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class BOUND_LITERAL extends FieldHandler<BoundExpressionParameter, Literal> {
			public BOUND_LITERAL() { super(Fields.BOUND_LITERAL); }
			@Override public Literal get() { return entity.getBoundLiteral(); }
			@Override public void set(Literal value) { entity.setBoundLiteral(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class BOUND_EXPRESSION extends FieldHandler<BoundExpressionParameter, ImplementedExpression> {
			public BOUND_EXPRESSION() { super(Fields.BOUND_EXPRESSION); }
			@Override public ImplementedExpression get() { return entity.getBoundExpression(); }
			@Override public void set(ImplementedExpression value) { entity.setBoundExpression(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class BOUND_FIELD extends FieldHandler<BoundExpressionParameter, K2Field> {
			public BOUND_FIELD() { super(Fields.BOUND_FIELD); }
			@Override public K2Field get() { return entity.getBoundField(); }
			@Override public void set(K2Field value) { entity.setBoundField(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class BOUND_PARAMETER extends FieldHandler<BoundExpressionParameter, ExpressionParameter> {
			public BOUND_PARAMETER() { super(Fields.BOUND_PARAMETER); }
			@Override public ExpressionParameter get() { return entity.getBoundParameter(); }
			@Override public void set(ExpressionParameter value) { entity.setBoundParameter(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class BINDS_PARAMETER extends FieldHandler<BoundExpressionParameter, ExpressionParameter> {
			public BINDS_PARAMETER() { super(Fields.BINDS_PARAMETER); }
			@Override public ExpressionParameter get() { return entity.getBindsParameter(); }
			@Override public void set(ExpressionParameter value) { entity.setBindsParameter(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.IMPLEMENTED_EXPRESSION, Fields.BINDS_PARAMETER);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<BoundExpressionParameter, ImplementedExpression> IMPLEMENTABLE_EXPRESSIONS = new MetaList<BoundExpressionParameter, ImplementedExpression>(
				false, // Enabled
				"implementableExpressions", // Alias
				"Implementable expressions", // Default label
				ListHandlers.IMPLEMENTABLE_EXPRESSIONS.class, // List handler class
				ImplementedExpressionController.class, // Stateless controller class
				MetaImplementedExpression.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<BoundExpressionParameter, Literal> LITERALS = new MetaList<BoundExpressionParameter, Literal>(
				false, // Enabled
				"litearls", // Alias
				"Literals", // Default label
				ListHandlers.LITERALS.class, // List handler class
				LiteralController.class, // Stateless controller class
				MetaLiteral.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<BoundExpressionParameter, ImplementedExpression> BOUND_EXPRESSIONS = new MetaList<BoundExpressionParameter, ImplementedExpression>(
				false, // Enabled
				"boundExpressions", // Alias
				"Bound expressions", // Default label
				ListHandlers.BOUND_EXPRESSIONS.class, // List handler class
				ImplementedExpressionController.class, // Stateless controller class
				MetaImplementedExpression.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<BoundExpressionParameter, K2Field> BINDABLE_FIELDS = new MetaList<BoundExpressionParameter, K2Field>(
				false, // Enabled
				"bindableFields", // Alias
				"Bindable fields", // Default label
				ListHandlers.BINDABLE_FIELDS.class, // List handler class
				K2FieldController.class, // Stateless controller class
				MetaK2Field.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<BoundExpressionParameter, ExpressionParameter> EXPRESSION_PARAMETERS = new MetaList<BoundExpressionParameter, ExpressionParameter>(
				false, // Enabled
				"bindableParameters", // Alias
				"Bindable parameters", // Default label
				ListHandlers.EXPRESSION_PARAMETERS.class, // List handler class
				ExpressionParameterController.class, // Stateless controller class
				MetaExpressionParameter.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<BoundExpressionParameter, ExpressionParameter> IMPLEMENTED_EXPRESSION_PARAMETERS = new MetaList<BoundExpressionParameter, ExpressionParameter>(
				false, // Enabled
				"implementedExpressionParameters", // Alias
				"Implemented expression parameters", // Default label
				ListHandlers.IMPLEMENTED_EXPRESSION_PARAMETERS.class, // List handler class
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
				lists.put(IMPLEMENTABLE_EXPRESSIONS.alias, IMPLEMENTABLE_EXPRESSIONS); orderedLists.add(IMPLEMENTABLE_EXPRESSIONS);
				lists.put(LITERALS.alias, LITERALS); orderedLists.add(LITERALS);
				lists.put(BOUND_EXPRESSIONS.alias, BOUND_EXPRESSIONS); orderedLists.add(BOUND_EXPRESSIONS);
				lists.put(BINDABLE_FIELDS.alias, BINDABLE_FIELDS); orderedLists.add(BINDABLE_FIELDS);
				lists.put(EXPRESSION_PARAMETERS.alias, EXPRESSION_PARAMETERS); orderedLists.add(EXPRESSION_PARAMETERS);
				lists.put(IMPLEMENTED_EXPRESSION_PARAMETERS.alias, IMPLEMENTED_EXPRESSION_PARAMETERS); orderedLists.add(IMPLEMENTED_EXPRESSION_PARAMETERS);
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
		
		public static class IMPLEMENTABLE_EXPRESSIONS extends ListHandler<BoundExpressionParameter, ImplementedExpression> {
			public IMPLEMENTABLE_EXPRESSIONS() { super(Lists.IMPLEMENTABLE_EXPRESSIONS); }
			@Override
			public ServiceList<ImplementedExpression> getServiceList() { return entity.getImplementableExpressions(); }
		}
		
		public static class LITERALS extends ListHandler<BoundExpressionParameter, Literal> {
			public LITERALS() { super(Lists.LITERALS); }
			@Override
			public ServiceList<Literal> getServiceList() { return entity.getLiterals(); }
		}
		
		public static class BOUND_EXPRESSIONS extends ListHandler<BoundExpressionParameter, ImplementedExpression> {
			public BOUND_EXPRESSIONS() { super(Lists.BOUND_EXPRESSIONS); }
			@Override
			public ServiceList<ImplementedExpression> getServiceList() { return entity.getBoundExpressions(); }
		}
		
		public static class BINDABLE_FIELDS extends ListHandler<BoundExpressionParameter, K2Field> {
			public BINDABLE_FIELDS() { super(Lists.BINDABLE_FIELDS); }
			@Override
			public ServiceList<K2Field> getServiceList() { return entity.getBindableFields(); }
		}
		
		public static class EXPRESSION_PARAMETERS extends ListHandler<BoundExpressionParameter, ExpressionParameter> {
			public EXPRESSION_PARAMETERS() { super(Lists.EXPRESSION_PARAMETERS); }
			@Override
			public ServiceList<ExpressionParameter> getServiceList() { return entity.getExpressionParameters(); }
		}
		
		public static class IMPLEMENTED_EXPRESSION_PARAMETERS extends ListHandler<BoundExpressionParameter, ExpressionParameter> {
			public IMPLEMENTED_EXPRESSION_PARAMETERS() { super(Lists.IMPLEMENTED_EXPRESSION_PARAMETERS); }
			@Override
			public ServiceList<ExpressionParameter> getServiceList() { return entity.getImplementedExpressionParameters(); }
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
