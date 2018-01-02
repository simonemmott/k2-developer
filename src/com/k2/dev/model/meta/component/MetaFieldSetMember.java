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
import com.k2.common.util.IntegerUtil;
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
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2FieldSetService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.web.stateless.ComponentController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.K2FieldSetController;

@SuppressWarnings({"unused"})
public class MetaFieldSetMember implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<FieldSetMember, Long> ID = new MetaNumberField<FieldSetMember, Long>(
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
		public static MetaField<FieldSetMember, K2FieldSet> FIELD_SET = new MetaLinkedField<FieldSetMember, K2FieldSet>(
				K2FieldSet.class,  // Data type
				true, // Required
				true, // Enabled
				"fieldSet", // Alias
				"Component", // Default label 
				FieldHandlers.FIELD_SET.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.FIELD_SETS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2FieldSetService.class // The class of the service object handling the linked entities
				);
		public static MetaField<FieldSetMember, Integer> ORDER_IN_SET = new MetaNumberField<FieldSetMember, Integer>(
				Integer.class, // Data type
				true, // Required
				true, // Enabled
				"orderInSet", // Alias
				"Order in set", // Default label
				FieldHandlers.ORDER_IN_SET.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Number field settings
				null, // The displayed size of the field
				null, // Maximum value for the field
				null // Minimum value for the field
				);
		public static MetaField<FieldSetMember, K2Field> MEMBER = new MetaLinkedField<FieldSetMember, K2Field>(
				K2Field.class,  // Data type
				true, // Required
				true, // Enabled
				"member", // Alias
				"Member", // Default label 
				FieldHandlers.MEMBER.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.COMPONENT_FIELDS.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2FieldService.class // The class of the service object handling the linked entities
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
				fields.put(FIELD_SET.alias, FIELD_SET); orderedFields.add(FIELD_SET);
				fields.put(ORDER_IN_SET.alias, ORDER_IN_SET); orderedFields.add(ORDER_IN_SET);
				fields.put(MEMBER.alias, MEMBER); orderedFields.add(MEMBER);
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
		
		public static class ID extends FieldHandler<FieldSetMember, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class FIELD_SET extends FieldHandler<FieldSetMember, K2FieldSet> {
			public FIELD_SET() { super(Fields.FIELD_SET); }
			@Override public K2FieldSet get() { return entity.getFieldSet(); }
			@Override public void set(K2FieldSet value) { entity.setFieldSet(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class ORDER_IN_SET extends FieldHandler<FieldSetMember, Integer> {
			public ORDER_IN_SET() { super(Fields.ORDER_IN_SET); }
			@Override public Integer get() { return entity.getOrderInSet(); }
			@Override public void set(Integer value) { entity.setOrderInSet(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toInteger(value)); }
			@Override public String getForUI() { return IntegerUtil.toString(get()); }
		}
		
		public static class MEMBER extends FieldHandler<FieldSetMember, K2Field> {
			public MEMBER() { super(Fields.MEMBER); }
			@Override public K2Field get() { return entity.getMember(); }
			@Override public void set(K2Field value) { entity.setMember(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.FIELD_SET, Fields.MEMBER);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<FieldSetMember, K2FieldSet> FIELD_SETS = new MetaList<FieldSetMember, K2FieldSet>(
				false, // Enabled
				"fieldSets", // Alias
				"Fields sets", // Default label
				ListHandlers.FIELD_SETS.class, // List handler class
				K2FieldSetController.class, // Stateless controller class
				MetaFieldSet.defaultFieldSet, // Default field set
				false // Allow new
				);

		public static MetaList<FieldSetMember, K2Field> COMPONENT_FIELDS = new MetaList<FieldSetMember, K2Field>(
				false, // Enabled
				"componentFields", // Alias
				"Component fields", // Default label
				ListHandlers.COMPONENT_FIELDS.class, // List handler class
				K2FieldSetController.class, // Stateless controller class
				MetaFieldSet.defaultFieldSet, // Default field set
				false // Allow new
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
				lists.put(FIELD_SETS.alias, FIELD_SETS); orderedLists.add(FIELD_SETS);
				lists.put(COMPONENT_FIELDS.alias, COMPONENT_FIELDS); orderedLists.add(COMPONENT_FIELDS);
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
		
		public static class FIELD_SETS extends ListHandler<FieldSetMember, K2FieldSet> {
			public FIELD_SETS() { super(Lists.FIELD_SETS); }
			@Override
			public ServiceList<K2FieldSet> getServiceList() { return entity.getFieldSets(); }
		}
		
		public static class COMPONENT_FIELDS extends ListHandler<FieldSetMember, K2Field> {
			public COMPONENT_FIELDS() { super(Lists.COMPONENT_FIELDS); }
			@Override
			public ServiceList<K2Field> getServiceList() { return entity.getComponentFields(); }
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
