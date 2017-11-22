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
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlLinkedField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2List;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.Project;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaComponent.FieldHandlers;
import com.k2.dev.model.meta.component.MetaComponent.Fields;
import com.k2.dev.model.meta.component.MetaComponent.Lists;
import com.k2.dev.model.meta.component.MetaComponent.Methods;
import com.k2.dev.service.BoundExpressionParameterService;
import com.k2.dev.service.ExpressionService;
import com.k2.dev.service.K2ServiceService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.service.K2TypeValueService;
import com.k2.dev.service.ProjectService;
import com.k2.dev.web.stateless.BoundExpressionParameterController;
import com.k2.dev.web.stateless.ExpressionController;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.K2ListController;
import com.k2.dev.web.stateless.K2MethodController;
import com.k2.dev.web.stateless.K2TypeDefController;
import com.k2.dev.web.stateless.K2TypeValueController;
import com.k2.dev.web.stateless.ProjectController;

@SuppressWarnings({"unused"})
public class MetaImplementedExpression extends MetaExpression {
	
	public static class Fields extends MetaExpression.Fields {
		public static MetaField<ImplementedExpression, Expression> IMPLEMENTS_EXPRESSION = new MetaLinkedField<ImplementedExpression, Expression>(
				Expression.class,  // Data type
				true, // Required
				true, // Enabled
				"implementsExpression", // Alias
				"Implements expression", // Default label 
				FieldHandlers.IMPLEMENTS_EXPRESSION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.IMPLEMENTABLE_EXPRESSIONS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ExpressionService.class // The class of the service object handling the linked entities
				);
		public static MetaField<ImplementedExpression, K2Service> K2SERVICE = new MetaLinkedField<ImplementedExpression, K2Service>(
				K2Service.class,  // Data type
				true, // Required
				true, // Enabled
				"k2Service", // Alias
				"Service", // Default label 
				FieldHandlers.K2SERVICE.class, // Field handler class
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
		public static MetaField<ImplementedExpression, K2Entity> K2ENTITY = new MetaLinkedField<ImplementedExpression, K2Entity>(
				K2Entity.class,  // Data type
				false, // Required
				true, // Enabled
				"k2Entity", // Alias
				"Entity", // Default label 
				FieldHandlers.K2ENTITY.class, // Field handler class
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
		public static MetaField<ImplementedExpression, BoundExpressionParameter> BOUND_TO_PARAMETER = new MetaLinkedField<ImplementedExpression, BoundExpressionParameter>(
				BoundExpressionParameter.class,  // Data type
				false, // Required
				true, // Enabled
				"boundToParameter", // Alias
				"Bound to parameter", // Default label 
				FieldHandlers.BOUND_TO_PARAMETER.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.BOUND_EXPRESSION_PARAMETERS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				BoundExpressionParameterService.class // The class of the service object handling the linked entities
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
				fields.put(IMPLEMENTS_EXPRESSION.alias, IMPLEMENTS_EXPRESSION); orderedFields.add(IMPLEMENTS_EXPRESSION);
				fields.put(K2SERVICE.alias, K2SERVICE); orderedFields.add(K2SERVICE);
				fields.put(K2ENTITY.alias, K2ENTITY); orderedFields.add(K2ENTITY);
				fields.put(BOUND_TO_PARAMETER.alias, BOUND_TO_PARAMETER); orderedFields.add(BOUND_TO_PARAMETER);
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
		
		public static class IMPLEMENTS_EXPRESSION extends FieldHandler<ImplementedExpression, Expression> {
			public IMPLEMENTS_EXPRESSION() { super(Fields.IMPLEMENTS_EXPRESSION); }
			@Override public Expression get() { return entity.getImplementsExpression(); }
			@Override public void set(Expression value) { entity.setImplementsExpression(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class K2SERVICE extends FieldHandler<ImplementedExpression, K2Service> {
			public K2SERVICE() { super(Fields.K2SERVICE); }
			@Override public K2Service get() { return entity.getK2Service(); }
			@Override public void set(K2Service value) { entity.setK2Service(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class K2ENTITY extends FieldHandler<ImplementedExpression, K2Entity> {
			public K2ENTITY() { super(Fields.K2ENTITY); }
			@Override public K2Entity get() { return entity.getK2Entity(); }
			@Override public void set(K2Entity value) { entity.setK2Entity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class BOUND_TO_PARAMETER extends FieldHandler<ImplementedExpression, BoundExpressionParameter> {
			public BOUND_TO_PARAMETER() { super(Fields.BOUND_TO_PARAMETER); }
			@Override public BoundExpressionParameter get() { return entity.getBoundToParameter(); }
			@Override public void set(BoundExpressionParameter value) { entity.setBoundToParameter(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
	}

	public static FieldSet defaultFieldSet = new FieldSet(Fields.EXPRESSION_TYPE, Fields.ALIAS, Fields.RETURN_TYPE, Fields.IMPLEMENTS_EXPRESSION);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }
		
	public static class Lists extends MetaLists {
		public static MetaList<ImplementedExpression, Expression> IMPLEMENTABLE_EXPRESSIONS = new MetaList<ImplementedExpression, Expression>(
				false, // Enabled
				"implementableExpressions", // Alias
				"Implementable expressions", // Default label
				ListHandlers.IMPLEMENTABLE_EXPRESSIONS.class, // List handler class
				ExpressionController.class, // Stateless controller class
				MetaExpression.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<ImplementedExpression, K2Service> SERVICES = new MetaList<ImplementedExpression, K2Service>(
				false, // Enabled
				"services", // Alias
				"Services", // Default label
				ListHandlers.SERVICES.class, // List handler class
				ExpressionController.class, // Stateless controller class
				MetaK2Service.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<ImplementedExpression, K2Entity> ENTITIES = new MetaList<ImplementedExpression, K2Entity>(
				false, // Enabled
				"extendableEntities", // Alias
				"Extendable entities", // Default label
				ListHandlers.ENTITIES.class, // List handler class
				K2EntityController.class, // Stateless controller class
				MetaImplementedExpression.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<ImplementedExpression, BoundExpressionParameter> BOUND_EXPRESSION_PARAMETERS = new MetaList<ImplementedExpression, BoundExpressionParameter>(
				false, // Enabled
				"boundExpressionParameters", // Alias
				"Bound expression parameters", // Default label
				ListHandlers.BOUND_EXPRESSION_PARAMETERS.class, // List handler class
				BoundExpressionParameterController.class, // Stateless controller class
				MetaBoundExpressionParameter.defaultFieldSet, // Default field set
				true // Allow new
				);
		public static MetaList<ImplementedExpression, BoundExpressionParameter> PARAMETER_BINDINGS = new MetaList<ImplementedExpression, BoundExpressionParameter>(
				true, // Enabled
				"parameterBindings", // Alias
				"Parameter bindings", // Default label
				ListHandlers.PARAMETER_BINDINGS.class, // List handler class
				BoundExpressionParameterController.class, // Stateless controller class
				MetaBoundExpressionParameter.defaultFieldSet, // Default field set
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
				for (MetaList metaList : MetaExpression.Lists.getLists()) {
					lists.put(metaList.alias, metaList); orderedLists.add(metaList);
				}
				lists.put(IMPLEMENTABLE_EXPRESSIONS.alias, IMPLEMENTABLE_EXPRESSIONS); orderedLists.add(IMPLEMENTABLE_EXPRESSIONS);
				lists.put(SERVICES.alias, SERVICES); orderedLists.add(SERVICES);
				lists.put(ENTITIES.alias, ENTITIES); orderedLists.add(ENTITIES);
				lists.put(BOUND_EXPRESSION_PARAMETERS.alias, BOUND_EXPRESSION_PARAMETERS); orderedLists.add(BOUND_EXPRESSION_PARAMETERS);
				lists.put(PARAMETER_BINDINGS.alias, PARAMETER_BINDINGS); orderedLists.add(PARAMETER_BINDINGS);
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
		
		public static class IMPLEMENTABLE_EXPRESSIONS extends ListHandler<ImplementedExpression, Expression> {
			public IMPLEMENTABLE_EXPRESSIONS() { super(Lists.IMPLEMENTABLE_EXPRESSIONS); }
			@Override
			public ServiceList<Expression> getServiceList() { return entity.getImplementableExpressions(); }
		}
		
		public static class SERVICES extends ListHandler<ImplementedExpression, K2Service> {
			public SERVICES() { super(Lists.SERVICES); }
			@Override
			public ServiceList<K2Service> getServiceList() { return entity.getServices(); }
		}
		
		public static class ENTITIES extends ListHandler<ImplementedExpression, K2Entity> {
			public ENTITIES() { super(Lists.ENTITIES); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getEntities(); }
		}
		
		public static class BOUND_EXPRESSION_PARAMETERS extends ListHandler<ImplementedExpression, BoundExpressionParameter> {
			public BOUND_EXPRESSION_PARAMETERS() { super(Lists.BOUND_EXPRESSION_PARAMETERS); }
			@Override
			public ServiceList<BoundExpressionParameter> getServiceList() { return entity.getBoundExpressionParameters(); }
		}
		
		public static class PARAMETER_BINDINGS extends ListHandler<ImplementedExpression, BoundExpressionParameter> {
			public PARAMETER_BINDINGS() { super(Lists.PARAMETER_BINDINGS); }
			@Override
			public ServiceList<BoundExpressionParameter> getServiceList() { return entity.getParameterBindings(); }
		}
		
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
