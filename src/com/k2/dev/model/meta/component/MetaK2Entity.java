package com.k2.dev.model.meta.component;

import com.k2.common.interaction.FieldHandler;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.meta.component.MetaComponent.FieldHandlers;

@SuppressWarnings({"unchecked", "unused"})
public class MetaK2Entity extends MetaComponent {
	
	public static class Fields extends MetaComponent.Fields {
		public static MetaField<K2Entity, String> ENTITYNAME = register(new MetaField<K2Entity, String>(
				String.class, // Data type
				"entityName", // Alias
				"Entity name", // Default label
				FieldHandlers.ENTITYNAME.class // Field handler class
				));
		public static MetaField<K2Entity, K2Entity> EXTENDS_ENTITY = register(new MetaField<K2Entity, K2Entity>(
				K2Entity.class,  // Data type
				"name", // Alias
				"Name", // Default label 
				FieldHandlers.EXTENDS_ENTITY.class // Field handler class
				));
		public static MetaField<K2Entity, String> INHERITANCE_JOIN_COLUMN = register(new MetaField<K2Entity, String>(
				String.class,  // Data type
				"inheritanceJoinColumn", // Alias 
				"Join column", // Default label 
				FieldHandlers.INHERITANCE_JOIN_COLUMN.class // Field handler class
				));
		public static MetaField<K2Entity, String> TABLE_NAME = register(new MetaField<K2Entity, String>(
				String.class, // Data type
				"tableName", // Alias
				"Table name", // Default label 
				FieldHandlers.TABLE_NAME.class // Field handler class
				));
	}
	
	public static class FieldHandlers extends MetaFieldHandlers {
		
		public static class ENTITYNAME extends FieldHandler<K2Entity, String> {
			public ENTITYNAME() { super(Fields.ENTITYNAME); }
			@Override public String get() { return entity.getEntityName(); }
			@Override public void set(String value) { entity.setEntityName(value); }
		}
		
		public static class EXTENDS_ENTITY extends FieldHandler<K2Entity, K2Entity> {
			public EXTENDS_ENTITY() { super(Fields.EXTENDS_ENTITY); }
			@Override public K2Entity get() { return entity.getExtendsEntity(); }
			@Override public void set(K2Entity value) { entity.setExtendsEntity(value); }
		}
		
		public static class INHERITANCE_JOIN_COLUMN extends FieldHandler<K2Entity, String> {
			public INHERITANCE_JOIN_COLUMN() { super(Fields.INHERITANCE_JOIN_COLUMN); }
			@Override public String get() { return entity.getInheritanceJoinColumn(); }
			@Override public void set(String value) { entity.setInheritanceJoinColumn(value); }
		}
		
		public static class TABLE_NAME extends FieldHandler<K2Entity, String> {
			public TABLE_NAME() { super(Fields.TABLE_NAME); }
			@Override public String get() { return entity.getTableName(); }
			@Override public void set(String value) { entity.setTableName(value); }
		}
		
	}


}
