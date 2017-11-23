package com.k2.dev.model.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.bo.*;
import com.k2.dev.model.meta.component.*;
import com.k2.dev.web.stateless.*;

public class MetaModel {
	
	public static class Entities {

		public static MetaModelEntity K2SERVICE = new MetaModelEntity(	
				"k2Service", // Alias
				"Service", // Name
				"Services", // Plural Name
				"A collection of components providing a service", // Description
				null, // SubClasses
				new MetaK2Service(), // MetaEntity
				false, // IsAbstract
				K2ServiceBO.class, // Service model class
				K2ServiceController.class // Stateless controller class
			);		
		public static MetaModelEntity K2ENTITY = new MetaModelEntity(	
				"k2Entity", // Alias
				"K2 Entity", // Name
				"K2 Entities", // Plural Name
				"An object capable of being persisted in the database", // Description
				null, // SubClasses
				new MetaK2Entity(), // MetaEntity
				false, // IsAbstract
				K2EntityBO.class, // Service model class
				K2EntityController.class // Stateless controller class
			);		
		public static MetaModelEntity K2NATIVEFIELD = new MetaModelEntity(
				"k2NativeField", // Alias
				"K2 Native Field", // Name
				"K2 Native Fields", //PluralName
				"A naive field of an entity", //Description
				null, // SubClasses
				new MetaK2NativeField(), // MetaEntity
				false, // IsAbstract
				K2NativeFieldBO.class, // Service model class
				K2NativeFieldController.class // Stateless controller class

			);		
		public static MetaModelEntity K2LINKEDFIELD = new MetaModelEntity(
				"k2LinkedField", // Alias
				"K2 Linked Field", // Name
				"K2 Linked Fields", //PluralName
				"A field linking one entity to another", //Description
				null, // SubClasses
				new MetaK2LinkedField(), // MetaEntity
				false, // IsAbstract
				K2LinkedFieldBO.class, // Service model class
				K2LinkedFieldController.class // Stateless controller class

			);		
		public static MetaModelEntity K2TYPEFIELD = new MetaModelEntity(
				"k2TypeField", // Alias
				"K2 Type Field", // Name
				"K2 Type Fields", //PluralName
				"A field allowing the selection of a type value from a selection list", //Description
				null, // SubClasses
				new MetaK2TypeField(), // MetaEntity
				false, // IsAbstract
				K2TypeFieldBO.class, // Service model class
				K2TypeFieldController.class // Stateless controller class

			);		
		public static MetaModelEntity K2FIELD = new MetaModelEntity(
				"k2Field", // Alias
				"K2 Field", // Name
				"K2 Fields", //PluralName
				"A field of an entity", //Description
				new MetaModelEntity[] {K2NATIVEFIELD, K2LINKEDFIELD, K2TYPEFIELD}, // SubClasses
				new MetaK2Field(), // MetaEntity
				true, // IsAbstract
				K2FieldBO.class, // Service model class
				K2EntityController.class // Stateless controller class

			);		
		public static MetaModelEntity DEPENDENCY = new MetaModelEntity(
				"dependency", // Alias
				"Dependency", // Name
				"Dependencies", // Plural Name
				"A component dependency", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				DependencyBO.class, // Service model class
				null // Stateless controller class

			);		
		public static MetaModelEntity COMPONENT = new MetaModelEntity(
				"component", // Alias
				"Component", // Name
				"Components", // Plural Name
				"A component within the application", // Description
				new MetaModelEntity[] {K2ENTITY}, // SubClasses
				new MetaComponent(), // MetaEntity
				true, // IsAbstract
				ComponentBO.class, // Service model class
				ComponentController.class // Stateless controller class

			);	
		public static MetaModelEntity K2TYPEDEF = new MetaModelEntity(
				"k2TypeDef", // Alias
				"Type defintion", // Name
				"Type defintions", // Plural Name
				"A type definition", // Description
				null, // SubClasses
				new MetaK2TypeDef(), // MetaEntity
				false, // IsAbstract
				K2TypeDefBO.class, // Service model class
				K2TypeDefController.class // Stateless controller class

			);	
		public static MetaModelEntity K2TYPEVALUE = new MetaModelEntity(
				"k2TypeValue", // Alias
				"Type value", // Name
				"Type values", // Plural Name
				"A type value", // Description
				null, // SubClasses
				new MetaK2TypeValue(), // MetaEntity
				false, // IsAbstract
				K2TypeValueBO.class, // Service model class
				K2TypeValueController.class // Stateless controller class

			);	
		public static MetaModelEntity K2LIST = new MetaModelEntity(
				"k2List", // Alias
				"List", // Name
				"Lists", // Plural Name
				"A list defined on an entity", // Description
				null, // SubClasses
				new MetaK2List(), // MetaEntity
				false, // IsAbstract
				K2ListBO.class, // Service model class
				K2ListController.class // Stateless controller class

			);	
		public static MetaModelEntity NATIVE_EXPRESSION = new MetaModelEntity(
				"nativeExpression", // Alias
				"Native expression", // Name
				"Native expressions", // Plural Name
				"An expression defined by K2 for implementation", // Description
				null, // SubClasses
				new MetaNativeExpression(), // MetaEntity
				false, // IsAbstract
				NativeExpressionBO.class, // Service model class
				NativeExpressionController.class // Stateless controller class

			);	
		public static MetaModelEntity IMPLEMENTED_EXPRESSION = new MetaModelEntity(
				"implementedExpression", // Alias
				"Implemented expression", // Name
				"Implemented expressions", // Plural Name
				"An implementetion of an expression", // Description
				null, // SubClasses
				new MetaImplementedExpression(), // MetaEntity
				false, // IsAbstract
				ImplementedExpressionBO.class, // Service model class
				ImplementedExpressionController.class // Stateless controller class

			);	
		public static MetaModelEntity EXPRESSION = new MetaModelEntity(
				"expression", // Alias
				"Expression", // Name
				"Expressions", // Plural Name
				"An expression", // Description
				new MetaModelEntity[] {NATIVE_EXPRESSION, IMPLEMENTED_EXPRESSION}, // SubClasses
				new MetaExpression(), // MetaEntity
				true, // IsAbstract
				ExpressionBO.class, // Service model class
				ExpressionController.class // Stateless controller class

			);	
		public static MetaModelEntity EXPRESSION_PARAMETER = new MetaModelEntity(
				"expressionParameter", // Alias
				"Expression parameter", // Name
				"Expression parameters", // Plural Name
				"A parameter of an expression", // Description
				null, // SubClasses
				new MetaExpressionParameter(), // MetaEntity
				false, // IsAbstract
				ExpressionParameterBO.class, // Service model class
				ExpressionParameterController.class // Stateless controller class

			);	
		public static MetaModelEntity BOUND_EXPRESSION_PARAMETER = new MetaModelEntity(
				"boundExpressionParameter", // Alias
				"Bound expression parameter", // Name
				"Bound expressions parameters", // Plural Name
				"A binding for an expression parameer", // Description
				null, // SubClasses
				new MetaBoundExpressionParameter(), // MetaEntity
				false, // IsAbstract
				BoundExpressionParameterBO.class, // Service model class
				BoundExpressionParameterController.class // Stateless controller class

			);	
		public static MetaModelEntity K2METHOD = new MetaModelEntity(
				"k2Method", // Alias
				"Method", // Name
				"Methods", // Plural Name
				"A method of an entity", // Description
				null, // SubClasses
				new MetaK2Method(), // MetaEntity
				false, // IsAbstract
				K2MethodBO.class, // Service model class
				K2MethodController.class // Stateless controller class

			);	
		public static MetaModelEntity ENTITY_BINDING = new MetaModelEntity(
				"entityBinding", // Alias
				"Entity binding", // Name
				"Entity bindings", // Plural Name
				"The bindings binding the values of an entity to the parameters of its implemented snippet", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				EntityBindingBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity ENTITY_FORMATTER = new MetaModelEntity(
				"entityFormatter", // Alias
				"Entity formatter", // Name
				"Entity formatters", // Plural Name
				"The defintion of how to output an entity using a snippet assembly", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				EntityFormatterBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity PERMITTED_CONTENT = new MetaModelEntity(
				"permittedContent", // Alias
				"Permitted content", // Name
				"Permitted contents", // Plural Name
				"The snippets that are allowed as child snippets of this snippet", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				K2PermittedContentBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity SNIPPET_BINDING = new MetaModelEntity(
				"snippetBinding", // Alias
				"Snippet binding", // Name
				"Snippet bindings", // Plural Name
				"The bindings that binding values to this snippet", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				K2SnippetBindingBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity SNIPPET_CONTAINER = new MetaModelEntity(
				"snippetContainer", // Alias
				"Snippet container", // Name
				"Snippet containers", // Plural Name
				"The containers in a snippet", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				K2SnippetContainerBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity SNIPPET_PARAMETER = new MetaModelEntity(
				"snippetParameter", // Alias
				"Snippet parameter", // Name
				"Snippet parameters", // Plural Name
				"The parameters that can be set on a snippet", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				K2SnippetParameterBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity SNIPPET = new MetaModelEntity(
				"snippet", // Alias
				"Snippet", // Name
				"Snippets", // Plural Name
				"A snippet available to be included in a snippet assembly", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				K2SnippetBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity LITERAL = new MetaModelEntity(
				"literal", // Alias
				"Literal", // Name
				"Literals", // Plural Name
				"A literal value used by this application", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				LiteralBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity PROJECT = new MetaModelEntity(
				"project", // Alias
				"Project", // Name
				"Projects", // Plural Name
				"The definition of the project being managed by the k2 development envronment", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				ProjectBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity TEMPLATE_BINDING = new MetaModelEntity(
				"templateBinging", // Alias
				"Template binding", // Name
				"Template bindings", // Plural Name
				"The bindings that binding the values passed to a template to the parameters of its implemented snippet", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				TemplateBindingBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity TEMPLATE_CONTENT = new MetaModelEntity(
				"templateContent", // Alias
				"Template content", // Name
				"Template contents", // Plural Name
				"The snippets contained in this template or entity formatter", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				TemplateContentBO.class, // Service model class
				null // Stateless controller class

			);	
		public static MetaModelEntity TEMPLATE = new MetaModelEntity(
				"template", // Alias
				"Template", // Name
				"Templates", // Plural Name
				"The templates available to be included in a snippet assembly", // Description
				null, // SubClasses
				null, // MetaEntity
				false, // IsAbstract
				TemplateBO.class, // Service model class
				null // Stateless controller class

			);	
	}
	
	private static Map<String, MetaModelEntity> entities = null;
	private static List<MetaModelEntity> entityList = null;
	
	public static List<MetaModelEntity> getEntities() {
		if (entities == null) {
			entities = new HashMap<String, MetaModelEntity>();
			entityList = new ArrayList<MetaModelEntity >();
			entities.put(Entities.K2SERVICE.alias, 					Entities.K2SERVICE); 					entityList.add(Entities.K2SERVICE);
			entities.put(Entities.K2ENTITY.alias, 					Entities.K2ENTITY); 						entityList.add(Entities.K2ENTITY);
			entities.put(Entities.K2NATIVEFIELD.alias, 				Entities.K2NATIVEFIELD); 				entityList.add(Entities.K2NATIVEFIELD);
			entities.put(Entities.K2LINKEDFIELD.alias, 				Entities.K2LINKEDFIELD); 				entityList.add(Entities.K2LINKEDFIELD);
			entities.put(Entities.K2TYPEFIELD.alias, 				Entities.K2TYPEFIELD); 					entityList.add(Entities.K2TYPEFIELD);
			entities.put(Entities.K2FIELD.alias, 					Entities.K2FIELD); 						entityList.add(Entities.K2FIELD);
			entities.put(Entities.K2TYPEDEF.alias, 					Entities.K2TYPEDEF); 					entityList.add(Entities.K2TYPEDEF);
			entities.put(Entities.K2TYPEVALUE.alias, 				Entities.K2TYPEVALUE); 					entityList.add(Entities.K2TYPEVALUE);
			entities.put(Entities.K2LIST.alias, 						Entities.K2LIST); 						entityList.add(Entities.K2LIST);
			entities.put(Entities.EXPRESSION.alias, 					Entities.EXPRESSION); 					entityList.add(Entities.EXPRESSION);
			entities.put(Entities.IMPLEMENTED_EXPRESSION.alias, 		Entities.IMPLEMENTED_EXPRESSION); 		entityList.add(Entities.IMPLEMENTED_EXPRESSION);
			entities.put(Entities.EXPRESSION_PARAMETER.alias, 		Entities.EXPRESSION_PARAMETER); 			entityList.add(Entities.EXPRESSION_PARAMETER);
			entities.put(Entities.BOUND_EXPRESSION_PARAMETER.alias, 	Entities.BOUND_EXPRESSION_PARAMETER); 	entityList.add(Entities.BOUND_EXPRESSION_PARAMETER);
			entities.put(Entities.K2METHOD.alias, 					Entities.K2METHOD); 						entityList.add(Entities.K2METHOD);
			entities.put(Entities.DEPENDENCY.alias, 					Entities.DEPENDENCY); 					entityList.add(Entities.DEPENDENCY);
			entities.put(Entities.COMPONENT.alias, 					Entities.COMPONENT); 					entityList.add(Entities.COMPONENT);
			entities.put(Entities.ENTITY_BINDING.alias, 				Entities.ENTITY_BINDING); 				entityList.add(Entities.ENTITY_BINDING);
			entities.put(Entities.ENTITY_FORMATTER.alias, 			Entities.ENTITY_FORMATTER); 				entityList.add(Entities.ENTITY_FORMATTER);
			entities.put(Entities.PERMITTED_CONTENT.alias, 			Entities.PERMITTED_CONTENT); 			entityList.add(Entities.PERMITTED_CONTENT);
			entities.put(Entities.SNIPPET_BINDING.alias, 			Entities.SNIPPET_BINDING); 				entityList.add(Entities.SNIPPET_BINDING);
			entities.put(Entities.PERMITTED_CONTENT.alias, 			Entities.PERMITTED_CONTENT); 			entityList.add(Entities.PERMITTED_CONTENT);
			entities.put(Entities.SNIPPET_CONTAINER.alias, 			Entities.SNIPPET_CONTAINER); 			entityList.add(Entities.SNIPPET_CONTAINER);
			entities.put(Entities.SNIPPET_PARAMETER.alias, 			Entities.SNIPPET_PARAMETER); 			entityList.add(Entities.SNIPPET_PARAMETER);
			entities.put(Entities.SNIPPET.alias, 					Entities.SNIPPET); 						entityList.add(Entities.SNIPPET);
			entities.put(Entities.LITERAL.alias, 					Entities.LITERAL); 						entityList.add(Entities.LITERAL);
			entities.put(Entities.PROJECT.alias, 					Entities.PROJECT); 						entityList.add(Entities.PROJECT);
			entities.put(Entities.TEMPLATE_BINDING.alias, 			Entities.TEMPLATE_BINDING); 				entityList.add(Entities.TEMPLATE_BINDING);
			entities.put(Entities.TEMPLATE_CONTENT.alias, 			Entities.TEMPLATE_CONTENT); 				entityList.add(Entities.TEMPLATE_CONTENT);
			entities.put(Entities.TEMPLATE.alias, 					Entities.TEMPLATE); 						entityList.add(Entities.TEMPLATE);
		}
		return entityList;
	}
	
	public static MetaModelEntity getMetaModelEntity(String alias) { getEntities(); return entities.get(alias); }

	@SuppressWarnings("rawtypes")
	public static MetaModelEntity getMetaModelEntity(ServiceModel entity) { return getMetaModelEntity(entity.getMetaEntity().alias); }


}
