package com.k2.dev.model.meta.component;

import com.k2.common.interaction.FieldHandler;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.common.meta.MetaFields;
import com.k2.dev.model.Component;

@SuppressWarnings({"unchecked", "unused"})
public class MetaComponent {
	
	public static class Fields extends MetaFields {
		public static MetaField<Component, Long> ID = register(new MetaField<Component, Long>(
				Long.class, // Data type
				"id", // Alias
				"ID", // Default label
				FieldHandlers.ID.class // Field handler class
				));
		public static MetaField<Component, String> NAME = register(new MetaField<Component, String>(
				String.class, // Data type
				"name", // Alias
				"Name", // Default label
				FieldHandlers.NAME.class // Field handler class
				));
		public static MetaField<Component, String> PACKAGE_NAME = register(new MetaField<Component, String>(
				String.class, // Data type
				"packageName", // Alias
				"Package name", // Default label
				FieldHandlers.PACKAGE_NAME.class // Field handler class
				));
	}
	
	public static class FieldHandlers extends MetaFieldHandlers {
		
		public static class ID extends FieldHandler<Component, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getID(); }
			@Override public void set(Long value) { entity.setID(value); }
		}
		
		public static class NAME extends FieldHandler<Component, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
		}
		
		public static class PACKAGE_NAME extends FieldHandler<Component, String> {
			public PACKAGE_NAME() { super(Fields.PACKAGE_NAME); }
			@Override public String get() { return entity.getPackageName(); }
			@Override public void set(String value) { entity.setPackageName(value); }
		}
		
	}

}
