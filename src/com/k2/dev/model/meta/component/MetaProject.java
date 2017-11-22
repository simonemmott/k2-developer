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
import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaFieldHandlers;
import com.k2.common.meta.MetaFields;
import com.k2.common.meta.MetaLinkedField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaListHandlers;
import com.k2.common.meta.MetaMethod;
import com.k2.common.meta.MetaMethodHandlers;
import com.k2.common.meta.MetaMethodParameter;
import com.k2.common.meta.MetaMethodParameters;
import com.k2.common.meta.MetaNumberField;
import com.k2.common.meta.MetaTextField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlLinkedField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.Project;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaComponent.Fields;
import com.k2.dev.model.meta.component.MetaComponent.Lists;
import com.k2.dev.model.meta.component.MetaComponent.Methods;
//import com.k2.dev.model.meta.component.MetaComponent.FieldHandlers;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.ProjectController;

@SuppressWarnings({"unused"})
public class MetaProject implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<Project, Long> ID = new MetaNumberField<Project, Long>(
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
		public static MetaField<Project, String> NAME = new MetaTextField<Project, String>(
				String.class,  // Data type
				true, // Required
				true, // Enabled
				"name", // Alias
				"Name", // Default label 
				FieldHandlers.NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settings
				null, // The maximum number of characters in the field
				null // Minimum value for the field
				);
		public static MetaField<Project, String> DESCRIPTION = new MetaTextField<Project, String>(
				String.class,  // Data type
				false, // Required
				true, // Enabled
				"description", // Alias 
				"Description", // Default label 
				FieldHandlers.DESCRIPTION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settings
				null, // The maximum number of characters in the field
				null // Minimum value for the field
				);
		public static MetaField<Project, String> FILE_SYSTEM_ROOT = new MetaTextField<Project, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"fileSystemRoot", // Alias
				"File system root", // Default label 
				FieldHandlers.FILE_SYSTEM_ROOT.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Text field settings
				null, // The maximum number of characters in the field
				null // Minimum value for the field
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
				fields.put(NAME.alias, NAME); orderedFields.add(NAME);
				fields.put(DESCRIPTION.alias, DESCRIPTION); orderedFields.add(DESCRIPTION);
				fields.put(FILE_SYSTEM_ROOT.alias, FILE_SYSTEM_ROOT); orderedFields.add(FILE_SYSTEM_ROOT);
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
		
		public static class ID extends FieldHandler<Project, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class NAME extends FieldHandler<Project, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get(); }
		}
		
		public static class DESCRIPTION extends FieldHandler<Project, String> {
			public DESCRIPTION() { super(Fields.DESCRIPTION); }
			@Override public String get() { return entity.getDescription(); }
			@Override public void set(String value) { entity.setDescription(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class FILE_SYSTEM_ROOT extends FieldHandler<Project, String> {
			public FILE_SYSTEM_ROOT() { super(Fields.FILE_SYSTEM_ROOT); }
			@Override public String get() { return entity.getFileSystemRoot(); }
			@Override public void set(String value) { entity.setFileSystemRoot(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
	}

	public static FieldSet defaultFieldSet = new FieldSet(Fields.NAME, Fields.FILE_SYSTEM_ROOT);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }
		
	public static class Lists extends MetaComponent.Lists {
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
		
		
	}
	
	public static class Methods extends MetaComponent.Methods {

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
