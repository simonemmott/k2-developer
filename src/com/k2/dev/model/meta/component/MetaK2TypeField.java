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
import com.k2.common.meta.MetaEntity;
import com.k2.common.meta.MetaModelEntity;
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
import com.k2.common.util.BooleanUtil;
import com.k2.common.util.IntegerUtil;
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeField;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Entity.FieldHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Fields;
import com.k2.dev.model.meta.component.MetaK2Entity.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Lists;
import com.k2.dev.model.meta.component.MetaK2Entity.MethodHandlers;
import com.k2.dev.model.meta.component.MetaK2Entity.Methods;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.web.stateless.K2EntityController;
import com.k2.dev.web.stateless.K2FieldController;
import com.k2.dev.web.stateless.K2TypeDefController;

@SuppressWarnings({"unused"})
public class MetaK2TypeField extends MetaK2Field implements MetaEntity {
	
	public static class Fields extends MetaK2Field.Fields {
		public static MetaField<K2TypeField, K2TypeDef> TYPE_DEFINTION = new MetaLinkedField<K2TypeField, K2TypeDef>(
				K2TypeDef.class,  // Data type
				true, // Required
				true, // Enabled
				"typeDefinition", // Alias
				"Type definition", // Default label 
				FieldHandlers.TYPE_DEFINTION.class, // Field handler class
				null, // Default top caption
				null, // Default left cation
				null, // Default right caption
				// Linked field settings
				30, // The displayed size of the linked field
				ListHandlers.ENTITY_TYPES.class, // Selection list handler class
				true, // Allow navigation to linked record
				true, // Allow insert into selection list
				K2TypeDefService.class // The class of the service object handling the linked entities
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
				for (MetaField metaField : MetaK2Field.Fields.getFields()) {
					fields.put(metaField.alias, metaField); orderedFields.add(metaField);
				}
				fields.put(TYPE_DEFINTION.alias, TYPE_DEFINTION); orderedFields.add(TYPE_DEFINTION);
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
	
	public static class FieldHandlers extends MetaK2Field.FieldHandlers {
		
		public static class TYPE_DEFINTION extends FieldHandler<K2TypeField, K2TypeDef> {
			public TYPE_DEFINTION() { super(Fields.TYPE_DEFINTION); }
			@Override public K2TypeDef get() { return entity.getTypeDefinition(); }
			@Override public void set(K2TypeDef value) { entity.setTypeDefinition(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		
	}

	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.ENTITY, Fields.NAME);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }
	
	public static class Lists extends MetaK2Field.Lists {

		public static MetaList<K2TypeField, K2TypeDef> ENTITY_TYPES = new MetaList<K2TypeField, K2TypeDef>(
				true, // Enabled
				"entityTypes", // Alias
				"Entity types", // Default label
				ListHandlers.ENTITY_TYPES.class, // List handler class
				K2TypeDefController.class, // Stateless controller class
				new FieldSet(MetaK2TypeDef.Fields.ENTITY, MetaK2TypeDef.Fields.NAME), // Default field set
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
				for (MetaList metaList : MetaK2Field.Lists.getLists()) {
					lists.put(metaList.alias, metaList); orderedLists.add(metaList);
				}
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

	public static class ListHandlers extends MetaK2Field.ListHandlers {

		public static class ENTITY_TYPES extends ListHandler<K2TypeField, K2TypeDef> {
			public ENTITY_TYPES() { super(Lists.ENTITY_TYPES); }
			@Override
			public ServiceList<K2TypeDef> getServiceList() { return entity.getEntityTypes(); }
		}
		
	}

	public static class Methods extends MetaK2Field.Methods {
		@SuppressWarnings("rawtypes")
		private static Map<String, MetaMethod> methods; 
		@SuppressWarnings("rawtypes")
		private static List<MetaMethod> orderedMethods;
		@SuppressWarnings("rawtypes")
		public static List<MetaMethod> getMethods() {
			if (methods == null) {
				methods =  new HashMap<String, MetaMethod>();
				orderedMethods = new ArrayList<MetaMethod>();
				for (MetaMethod metaMethod : MetaComponent.Methods.getMethods()) {
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
	
	public static class MethodHandlers extends MetaK2Field.MethodHandlers {
		
	}


}
