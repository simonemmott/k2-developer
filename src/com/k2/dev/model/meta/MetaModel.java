package com.k2.dev.model.meta;

public class MetaModel {
	
	public static enum Entities {
		K2ENTITY("k2Entity", // Alias
				"K2 Entity", // Name
				"K2 Entities", // Plural Name
				"An object capable of being persisted in the database"), // Description
		K2FIELD("k2Field", // Alias
				"K2 Field", // Name
				"K2 Fields", //PluralName
				"A field of an entity"), //Description
		DEPENDENCY("dependency", // Alias
				"Dependency", // Name
				"Dependencies", // Plural Name
				"A component dependency"), // Description
		COMPONENT("component", // Alias
				"Component", // Name
				"Components", // Plural Name
				"A component within the application"); // Description
		
		private String alias;
		private String name;
		private String pluralName;
		private String description;
		
		Entities(String alias, String name, String pluralName, String description) {
			this.alias = alias;
			this.name = name;
			this.pluralName = pluralName;
			this.description = description;
		}
		
		public String getAlias() { return alias; }
		public String getName() { return name; }
		public String getPluralName() { return pluralName; }
		public String getDescription() { return description; }
		
	}

}
