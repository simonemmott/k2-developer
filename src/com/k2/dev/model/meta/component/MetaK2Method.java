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
import com.k2.common.meta.MetaMemoField;
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
import com.k2.common.util.K2Type;
import com.k2.common.util.LongUtil;
import com.k2.common.util.StringUtil;
import com.k2.dev.model.Component;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2MethodENT.Types;
import com.k2.dev.model.entity.K2NativeFieldENT.Types.NativeDateType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.MetaModel.Entities;
import com.k2.dev.model.meta.component.MetaK2Field.Fields;
import com.k2.dev.model.meta.component.MetaK2Field.ListHandlers;
import com.k2.dev.model.meta.component.MetaK2Field.Lists;
import com.k2.dev.model.meta.component.MetaK2NativeField.FieldHandlers;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.web.stateless.DependencyController;
import com.k2.dev.web.stateless.K2EntityController;

@SuppressWarnings({"unused"})
public class MetaK2Method implements MetaEntity {
	
	public static class Fields extends MetaFields {
		public static MetaField<K2Method, Long> ID = new MetaNumberField<K2Method, Long>(
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
		public static MetaField<K2Method, K2Entity> K2ENTITY = new MetaLinkedField<K2Method, K2Entity>(
				K2Entity.class,  // Data type
				true, // Required
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
		public static MetaField<K2Method, String> ALIAS = new MetaTextField<K2Method, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"alias", // Alias
				"Alias", // Default label
				FieldHandlers.ALIAS.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				50, // The maximum number of characters in the field
				50 // The displayed size of the field
				);
		public static MetaField<K2Method, String> NAME = new MetaTextField<K2Method, String>(
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
				50 // The displayed size of the field
				);
		public static MetaField<K2Method, Types.returnTypes> RETURNS_TYPE = new MetaTypeField<K2Method, Types.returnTypes>(
				Types.returnTypes.class, // Data type
				true, // Required
				true, // Enabled
				"returnsType", // Alias
				"Returns type", // Default label
				FieldHandlers.RETURNS_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.returnTypes.values() // The class defining the type values
				);
		public static MetaField<K2Method, K2Entity> RETURNS_ENTITY = new MetaLinkedField<K2Method, K2Entity>(
				K2Entity.class,  // Data type
				false, // Required
				true, // Enabled
				"returnsEntity", // Alias
				"Returns entity", // Default label 
				FieldHandlers.RETURNS_ENTITY.class, // Field handler class
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
		public static MetaField<K2Method, Types.nativeReturnType> NATIVE_RETURN_TYPE = new MetaTypeField<K2Method, Types.nativeReturnType>(
				Types.nativeReturnType.class, // Data type
				false, // Required
				true, // Enabled
				"nativeDataType", // Alias
				"Data type", // Default label
				FieldHandlers.NATIVE_RETURN_TYPE.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Type field settings
				Types.nativeReturnType.values() // The class defining the type values
				);
		public static MetaField<K2Method, String> METHOD_BODY = new MetaMemoField<K2Method, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"methodBody", // Alias
				"Method body", // Default label
				FieldHandlers.METHOD_BODY.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				4000, // The maximum number of characters in the field
				10 // The displayed height of the field
				);
		public static MetaField<K2Method, String> DEPENDENCY_FIELDS = new MetaMemoField<K2Method, String>(
				String.class, // Data type
				true, // Required
				true, // Enabled
				"dependencyFields", // Alias
				"Dependency fields", // Default label
				FieldHandlers.DEPENDENCY_FIELDS.class, // Field handler class
				null, // Default top caption
				null, // Default left caption
				null, // Default right caption
				// Text field settings
				4000, // The maximum number of characters in the field
				5 // The displayed height of the field
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
				fields.put(ALIAS.alias, ALIAS); orderedFields.add(ALIAS);
				fields.put(NAME.alias, NAME); orderedFields.add(NAME);
				fields.put(K2ENTITY.alias, K2ENTITY); orderedFields.add(K2ENTITY);
				fields.put(RETURNS_TYPE.alias, RETURNS_TYPE); orderedFields.add(RETURNS_TYPE);
				fields.put(RETURNS_ENTITY.alias, RETURNS_ENTITY); orderedFields.add(RETURNS_ENTITY);
				fields.put(NATIVE_RETURN_TYPE.alias, NATIVE_RETURN_TYPE); orderedFields.add(NATIVE_RETURN_TYPE);
				fields.put(METHOD_BODY.alias, METHOD_BODY); orderedFields.add(METHOD_BODY);
				fields.put(DEPENDENCY_FIELDS.alias, DEPENDENCY_FIELDS); orderedFields.add(DEPENDENCY_FIELDS);
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
		
		public static class ID extends FieldHandler<K2Method, Long> {
			public ID() { super(Fields.ID); }
			@Override public Long get() { return entity.getId(); }
			@Override public void set(Long value) { entity.setId(value); }
			@Override public void setFromUI(String value) { set(StringUtil.toLong(value)); }
			@Override public String getForUI() { return LongUtil.toString(get()); }
		}
		
		public static class K2ENTITY extends FieldHandler<K2Method, K2Entity> {
			public K2ENTITY() { super(Fields.K2ENTITY); }
			@Override public K2Entity get() { return entity.getK2Entity(); }
			@Override public void set(K2Entity value) { entity.setK2Entity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class ALIAS extends FieldHandler<K2Method, String> {
			public ALIAS() { super(Fields.ALIAS); }
			@Override public String get() { return entity.getAlias(); }
			@Override public void set(String value) { entity.setAlias(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class NAME extends FieldHandler<K2Method, String> {
			public NAME() { super(Fields.NAME); }
			@Override public String get() { return entity.getName(); }
			@Override public void set(String value) { entity.setName(value); }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class RETURNS_TYPE extends FieldHandler<K2Method, Types.returnTypes> {
			public RETURNS_TYPE() { super(Fields.RETURNS_TYPE); }
			@Override public Types.returnTypes get() { return entity.getReturnsType(); }
			@Override public void set(Types.returnTypes value) { entity.setReturnsType(value); }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.returnTypes.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class RETURNS_ENTITY extends FieldHandler<K2Method, K2Entity> {
			public RETURNS_ENTITY() { super(Fields.RETURNS_ENTITY); }
			@Override public K2Entity get() { return entity.getReturnsEntity(); }
			@Override public void set(K2Entity value) { entity.setReturnsEntity(value); }
			@Override public void setFromUI(String value) { }
			@Override public String getForUI() { return get().getIdentity(); }
		}
		
		public static class NATIVE_RETURN_TYPE extends FieldHandler<K2Method, Types.nativeReturnType> {
			public NATIVE_RETURN_TYPE() { super(Fields.NATIVE_RETURN_TYPE); }
			@Override public Types.nativeReturnType get() { return entity.getNativeReturnType(); }
			@Override public void set(Types.nativeReturnType value) { entity.setNativeReturnType(value); }
			@Override public void setFromUI(String value) { if (K2Type.NOT_SET.equals(value)) { set(null); } else { set(Types.nativeReturnType.valueOf(value)); }}
			@Override public String getForUI() { if (get()==null) { return ""; } else { return get().toString(); }}
		}
		
		public static class METHOD_BODY extends FieldHandler<K2Method, String> {
			public METHOD_BODY() { super(Fields.METHOD_BODY); }
			@Override public String get() { return entity.getMethodBody(); }
			@Override public void set(String value) { entity.setMethodBody(value);; }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
		public static class DEPENDENCY_FIELDS extends FieldHandler<K2Method, String> {
			public DEPENDENCY_FIELDS() { super(Fields.DEPENDENCY_FIELDS); }
			@Override public String get() { return entity.getDependencyFields(); }
			@Override public void set(String value) { entity.setDependencyFields(value);; }
			@Override public void setFromUI(String value) { set(value); }
			@Override public String getForUI() { return get(); }
		}
		
	}
	
	public static FieldSet defaultFieldSet = new FieldSet(Fields.K2ENTITY, Fields.NAME, Fields.RETURNS_TYPE);
	@Override
	public FieldSet getDefaultFieldSet() { return defaultFieldSet; }

	
	public static class Lists extends MetaLists {
		public static MetaList<K2Method, K2Entity> ENTITIES = new MetaList<K2Method, K2Entity>(
				false, // Enabled
				"entities", // Alias
				"Entities", // Default label
				ListHandlers.ENTITIES.class, // List handler class
				K2EntityController.class, // Stateless controller class
				MetaK2Entity.defaultFieldSet, // Default field set
				true // Allow new
				);

		public static MetaList<K2Method, Dependency> DEPENDENCIES = new MetaList<K2Method, Dependency>(
				true, // Enabled
				"dependencies", // Alias
				"Dependencies", // Default label
				ListHandlers.DEPENDENCIES.class, // List handler class
				DependencyController.class, // Stateless controller class
				MetaDependency.defaultFieldSet, // Default field set
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
				lists.put(ENTITIES.alias, ENTITIES); orderedLists.add(ENTITIES);
				lists.put(DEPENDENCIES.alias, DEPENDENCIES); orderedLists.add(DEPENDENCIES);
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
		
		public static class ENTITIES extends ListHandler<K2Method, K2Entity> {
			public ENTITIES() { super(Lists.ENTITIES); }
			@Override
			public ServiceList<K2Entity> getServiceList() { return entity.getEntities(); }
		}
		
		public static class DEPENDENCIES extends ListHandler<K2Method, Dependency> {
			public DEPENDENCIES() { super(Lists.DEPENDENCIES); }
			@Override
			public ServiceList<Dependency> getServiceList() { return entity.getMethodDependencies(); }
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
