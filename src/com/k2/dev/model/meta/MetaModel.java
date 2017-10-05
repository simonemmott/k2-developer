package com.k2.dev.model.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaField;

public class MetaModel {
	
	public static class Entities {
		public static MetaEntity K2ENTITY = new MetaEntity(	
				"k2Entity", // Alias
				"K2 Entity", // Name
				"K2 Entities", // Plural Name
				"An object capable of being persisted in the database" // Description
			);		
		public static MetaEntity K2FIELD = new MetaEntity(
				"k2Field", // Alias
				"K2 Field", // Name
				"K2 Fields", //PluralName
				"A field of an entity" //Description
			);		
		public static MetaEntity DEPENDENCY = new MetaEntity(
				"dependency", // Alias
				"Dependency", // Name
				"Dependencies", // Plural Name
				"A component dependency" // Description
			);		
		public static MetaEntity COMPONENT = new MetaEntity(
				"component", // Alias
				"Component", // Name
				"Components", // Plural Name
				"A component within the application" // Description
			);	
		public static MetaEntity ENTITY_BINDING = new MetaEntity(
				"entityBinding", // Alias
				"Entity binding", // Name
				"Entity bindings", // Plural Name
				"The bindings binding the values of an entity to the parameters of its implemented snippet" // Description
			);	
		public static MetaEntity ENTITY_FORMATTER = new MetaEntity(
				"entityFormatter", // Alias
				"Entity formatter", // Name
				"Entity formatters", // Plural Name
				"The defintion of how to output an entity using a snippet assembly" // Description
			);	
		public static MetaEntity PERMITTED_CONTENT = new MetaEntity(
				"permittedContent", // Alias
				"Permitted content", // Name
				"Permitted contents", // Plural Name
				"The snippets that are allowed as child snippets of this snippet" // Description
			);	
		public static MetaEntity SNIPPET_BINDING = new MetaEntity(
				"snippetBinding", // Alias
				"Snippet binding", // Name
				"Snippet bindings", // Plural Name
				"The bindings that binding values to this snippet" // Description
			);	
		public static MetaEntity SNIPPET_CONTAINER = new MetaEntity(
				"snippetContainer", // Alias
				"Snippet container", // Name
				"Snippet containers", // Plural Name
				"The containers in a snippet" // Description
			);	
		public static MetaEntity SNIPPET_PARAMETER = new MetaEntity(
				"snippetParameter", // Alias
				"Snippet parameter", // Name
				"Snippet parameters", // Plural Name
				"The parameters that can be set on a snippet" // Description
			);	
		public static MetaEntity SNIPPET = new MetaEntity(
				"snippet", // Alias
				"Snippet", // Name
				"Snippets", // Plural Name
				"A snippet available to be included in a snippet assembly" // Description
			);	
		public static MetaEntity LITERAL = new MetaEntity(
				"literal", // Alias
				"Literal", // Name
				"Literals", // Plural Name
				"A literal value used by this application" // Description
			);	
		public static MetaEntity PROJECT = new MetaEntity(
				"project", // Alias
				"Project", // Name
				"Projects", // Plural Name
				"The definition of the project being managed by the k2 development envronment" // Description
			);	
		public static MetaEntity TEMPLATE_BINDING = new MetaEntity(
				"templateBinging", // Alias
				"Template binding", // Name
				"Template bindings", // Plural Name
				"The bindings that binding the values passed to a template to the parameters of its implemented snippet" // Description
			);	
		public static MetaEntity TEMPLATE_CONTENT = new MetaEntity(
				"templateContent", // Alias
				"Template content", // Name
				"Template contents", // Plural Name
				"The snippets contained in this template or entity formatter" // Description
			);	
		public static MetaEntity TEMPLATE = new MetaEntity(
				"template", // Alias
				"Template", // Name
				"Templates", // Plural Name
				"The templates available to be included in a snippet assembly" // Description
			);	
	}
	
	private static Map<String, MetaEntity> entities = null;
	private static List<MetaEntity> entityList = null;
	
	public static List<MetaEntity> getEntities() {
		if (entities == null) {
			entities = new HashMap<String, MetaEntity>();
			entityList = new ArrayList<MetaEntity>();
			entities.put(Entities.K2ENTITY.alias, 			Entities.K2ENTITY); 					entityList.add(Entities.K2ENTITY);
			entities.put(Entities.K2FIELD.alias, 			Entities.K2FIELD); 					entityList.add(Entities.K2FIELD);
			entities.put(Entities.DEPENDENCY.alias, 			Entities.DEPENDENCY); 				entityList.add(Entities.DEPENDENCY);
			entities.put(Entities.COMPONENT.alias, 			Entities.COMPONENT); 				entityList.add(Entities.COMPONENT);
			entities.put(Entities.ENTITY_BINDING.alias, 		Entities.ENTITY_BINDING); 			entityList.add(Entities.ENTITY_BINDING);
			entities.put(Entities.ENTITY_FORMATTER.alias, 	Entities.ENTITY_FORMATTER); 			entityList.add(Entities.ENTITY_FORMATTER);
			entities.put(Entities.PERMITTED_CONTENT.alias, 	Entities.PERMITTED_CONTENT); 		entityList.add(Entities.PERMITTED_CONTENT);
			entities.put(Entities.SNIPPET_BINDING.alias, 	Entities.SNIPPET_BINDING); 			entityList.add(Entities.SNIPPET_BINDING);
			entities.put(Entities.PERMITTED_CONTENT.alias, 	Entities.PERMITTED_CONTENT); 		entityList.add(Entities.PERMITTED_CONTENT);
			entities.put(Entities.SNIPPET_CONTAINER.alias, 	Entities.SNIPPET_CONTAINER); 		entityList.add(Entities.SNIPPET_CONTAINER);
			entities.put(Entities.SNIPPET_PARAMETER.alias, 	Entities.SNIPPET_PARAMETER); 		entityList.add(Entities.SNIPPET_PARAMETER);
			entities.put(Entities.SNIPPET.alias, 			Entities.SNIPPET); 					entityList.add(Entities.SNIPPET);
			entities.put(Entities.LITERAL.alias, 			Entities.LITERAL); 					entityList.add(Entities.LITERAL);
			entities.put(Entities.PROJECT.alias, 			Entities.PROJECT); 					entityList.add(Entities.PROJECT);
			entities.put(Entities.TEMPLATE_BINDING.alias, 	Entities.TEMPLATE_BINDING); 			entityList.add(Entities.TEMPLATE_BINDING);
			entities.put(Entities.TEMPLATE_CONTENT.alias, 	Entities.TEMPLATE_CONTENT); 			entityList.add(Entities.TEMPLATE_CONTENT);
			entities.put(Entities.TEMPLATE.alias, 			Entities.TEMPLATE); 					entityList.add(Entities.TEMPLATE);
		}
		return entityList;
	}


}
