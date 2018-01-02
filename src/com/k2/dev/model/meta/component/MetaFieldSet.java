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
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlNumberField;
import com.k2.common.snippets.html.HtmlTextField;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.FieldSetMember;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.web.stateless.ComponentController;
import com.k2.dev.web.stateless.FieldSetMemberController;
import com.k2.dev.web.stateless.K2FieldController;

@SuppressWarnings({"unused"})
public class MetaFieldSet implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<K2FieldSet, Long> ID = new MetaNumberField<K2FieldSet, Long>(
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
		public static MetaField<K2FieldSet, String> NAME = new MetaTextField<K2FieldSet, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"name", // Alias
				"Name", // Default label
				FieldHandlers.NAME.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				50, // The maximum number of characters in the field
				40 // The displayed size of the field
				);
		public static MetaField<K2FieldSet, Component> COMPONENT = new MetaLinkedField<K2FieldSet, Component>(
				Component.class,  // Data type
				true, // Required
				true, // Enabled
				"component", // Alias
				"Component", // Default label 
				FieldHandlers.COMPONENT.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.SERVICE_COMPONENTS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				ComponentService.class // The class of the service object handling the linked entities
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
				fields.put(COMPONENT.alias, COMPONENT); orderedFields.add(COMPONENT);
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
		
		public static class ID extends FieldHandler<K2FieldSet, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class NAME extends FieldHandler<K2FieldSet, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class COMPONENT extends FieldHandler<K2FieldSet, Component> {
			public COMPONENT() { super(Fields.COMPONENT); }
			@Override public Component get() { return entity.getComponent(); }
			@Override public void set(Component value) { entity.setComponent(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.NAME);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<K2FieldSet, Component> SERVICE_COMPONENTS = new MetaList<K2FieldSet, Component>(
				false, // Enabled
				"components", // Alias
				"Components", // Default label
				ListHandlers.SERVICE_COMPONENTS.class, // List handler class
				ComponentController.class, // Stateless controller class
				MetaComponent.defaultFieldSet, // Default field set
				false // Allow new
				);

		public static MetaList<K2FieldSet, FieldSetMember> MEMBERS = new MetaList<K2FieldSet, FieldSetMember>(
				true, // Enabled
				"members", // Alias
				"Members", // Default label
				ListHandlers.MEMBERS.class, // List handler class
				FieldSetMemberController.class, // Stateless controller class
				MetaFieldSetMember.defaultFieldSet, // Default field set
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
				lists.put(SERVICE_COMPONENTS.alias, SERVICE_COMPONENTS); orderedLists.add(SERVICE_COMPONENTS);
				lists.put(MEMBERS.alias, MEMBERS); orderedLists.add(MEMBERS);
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
		
		public static class SERVICE_COMPONENTS extends ListHandler<K2FieldSet, Component> {
			public SERVICE_COMPONENTS() { super(Lists.SERVICE_COMPONENTS); }
			@Override
			public ServiceList<Component> getServiceList() { return entity.getServiceComponents(); }
		}
		
		public static class MEMBERS extends ListHandler<K2FieldSet, FieldSetMember> {
			public MEMBERS() { super(Lists.MEMBERS); }
			@Override
			public ServiceList<FieldSetMember> getServiceList() { return entity.getMembers(); }
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
