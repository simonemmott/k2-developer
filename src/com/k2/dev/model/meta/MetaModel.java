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
    
        public static MetaModelEntity K2ENTITY = new MetaModelEntity(
                "k2Entity", // Alias
                "K2Entity", // Name
                "K2 Entities", // Plural Name
                "An object capable of being persisted in the database", // Description
                null, // SubClasses
                new MetaK2Entity(), // MetaEntity
                false, // IsAbstract
                K2EntityBO.class, // Service model class
                K2EntityController.class // Stateless controller class
            );
        public static MetaModelEntity COMPONENT = new MetaModelEntity(
                "component", // Alias
                "Component", // Name
                "Components", // Plural Name
                "A collection of components which together provide a service", // Description
                new MetaModelEntity[] {K2ENTITY}, // SubClasses
                new MetaComponent(), // MetaEntity
                true, // IsAbstract
                ComponentBO.class, // Service model class
                ComponentController.class // Stateless controller class
            );
        public static MetaModelEntity DEPENDENCY = new MetaModelEntity(
                "dependency", // Alias
                "Dependency", // Name
                "Dependencies", // Plural Name
                "A component dependency", // Description
                null, // SubClasses
                new MetaDependency(), // MetaEntity
                false, // IsAbstract
                DependencyBO.class, // Service model class
                DependencyController.class // Stateless controller class
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
        public static MetaModelEntity ENTITYFORMATTER = new MetaModelEntity(
                "entityFormatter", // Alias
                "EntityFormatter", // Name
                "Entity formatters", // Plural Name
                "The definition of how to output an entity using a snippet assembly", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                EntityFormatterBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity NATIVE_FIELD = new MetaModelEntity(
                "k2NativeField", // Alias
                "Native field", // Name
                "K2 Native Fields", // Plural Name
                "A naive field of an entity", // Description
                null, // SubClasses
                new MetaK2NativeField(), // MetaEntity
                false, // IsAbstract
                K2NativeFieldBO.class, // Service model class
                K2NativeFieldController.class // Stateless controller class
            );
        public static MetaModelEntity LINKED_FIELD = new MetaModelEntity(
                "k2LinkedField", // Alias
                "Linked field", // Name
                "K2 Linked Fields", // Plural Name
                "A field linking one entity to another", // Description
                null, // SubClasses
                new MetaK2LinkedField(), // MetaEntity
                false, // IsAbstract
                K2LinkedFieldBO.class, // Service model class
                K2LinkedFieldController.class // Stateless controller class
            );
        public static MetaModelEntity TYPE_FIELD = new MetaModelEntity(
                "k2TypeField", // Alias
                "Type field", // Name
                "K2 Type Fields", // Plural Name
                "A field allowing the selection of a type value from a selection list", // Description
                null, // SubClasses
                new MetaK2TypeField(), // MetaEntity
                false, // IsAbstract
                K2TypeFieldBO.class, // Service model class
                K2TypeFieldController.class // Stateless controller class
            );
        public static MetaModelEntity K2FIELD = new MetaModelEntity(
                "k2Field", // Alias
                "K2Field", // Name
                "K2 Fields", // Plural Name
                "A field of an entity", // Description
                new MetaModelEntity[] {NATIVE_FIELD, LINKED_FIELD, TYPE_FIELD}, // SubClasses
                new MetaK2Field(), // MetaEntity
                true, // IsAbstract
                K2FieldBO.class, // Service model class
                K2FieldController.class // Stateless controller class
            );
        public static MetaModelEntity K2PERMITTEDCONTENT = new MetaModelEntity(
                "k2PermittedContent", // Alias
                "K2PermittedContent", // Name
                "Permitted contents", // Plural Name
                "The snippets that are allowed as child snippets of this snippet", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                K2PermittedContentBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity TEMPLATEBINDING = new MetaModelEntity(
                "templateBinding", // Alias
                "TemplateBinding", // Name
                "Template bindings", // Plural Name
                "The bindings that binding the values passed to a template to the parameters of its implemented snippet", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                TemplateBindingBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity K2SNIPPETBINDING = new MetaModelEntity(
                "k2SnippetBinding", // Alias
                "K2SnippetBinding", // Name
                "Snippet bindings", // Plural Name
                "The bindings that binding values to this snippet", // Description
                new MetaModelEntity[] {TEMPLATEBINDING}, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                K2SnippetBindingBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity K2SNIPPETCONTAINER = new MetaModelEntity(
                "k2SnippetContainer", // Alias
                "K2SnippetContainer", // Name
                "Snippet containers", // Plural Name
                "The containers in a snippet", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                K2SnippetContainerBO.class, // Service model class
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
        public static MetaModelEntity K2SNIPPET = new MetaModelEntity(
                "k2Snippet", // Alias
                "K2Snippet", // Name
                "Snippets", // Plural Name
                "A snippet available to be included in a snippet assembly", // Description
                new MetaModelEntity[] {TEMPLATE}, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                K2SnippetBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity K2SNIPPETPARAMETER = new MetaModelEntity(
                "k2SnippetParameter", // Alias
                "K2SnippetParameter", // Name
                "Snippet parameters", // Plural Name
                "The parameters that can be set on a snippet", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                K2SnippetParameterBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity LITERAL = new MetaModelEntity(
                "literal", // Alias
                "Literal", // Name
                "Literals", // Plural Name
                "A literal value used by this application", // Description
                null, // SubClasses
                new MetaLiteral(), // MetaEntity
                false, // IsAbstract
                LiteralBO.class, // Service model class
                LiteralController.class // Stateless controller class
            );
        public static MetaModelEntity PROJECT = new MetaModelEntity(
                "project", // Alias
                "Project", // Name
                "Projects", // Plural Name
                "The definition of the project being managed by the k2 development envronment", // Description
                null, // SubClasses
                new MetaProject(), // MetaEntity
                false, // IsAbstract
                ProjectBO.class, // Service model class
                ProjectController.class // Stateless controller class
            );
        public static MetaModelEntity TEMPLATECONTENT = new MetaModelEntity(
                "templateContent", // Alias
                "TemplateContent", // Name
                "Template contents", // Plural Name
                "The snippets contained in this template or entity formatter", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                TemplateContentBO.class, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity FIELD_SET = new MetaModelEntity(
                "fieldSet", // Alias
                "Field set", // Name
                "Field sets", // Plural Name
                "A list of fields typically used to define the fields to include in a list.", // Description
                null, // SubClasses
                new MetaFieldSet(), // MetaEntity
                false, // IsAbstract
                K2FieldSetBO.class, // Service model class
                K2FieldSetController.class // Stateless controller class
            );
        public static MetaModelEntity FIELD_SET_MEMBER = new MetaModelEntity(
                "fieldSetMember", // Alias
                "Field set member", // Name
                "Field set members", // Plural Name
                "The fields that are members of a field set and their order within the set.", // Description
                null, // SubClasses
                new MetaFieldSetMember(), // MetaEntity
                false, // IsAbstract
                FieldSetMemberBO.class, // Service model class
                FieldSetMemberController.class // Stateless controller class
            );
        public static MetaModelEntity TYPE_DEFINITION = new MetaModelEntity(
                "k2TypeDef", // Alias
                "Type definition", // Name
                "Type definitions", // Plural Name
                "A type definition", // Description
                null, // SubClasses
                new MetaK2TypeDef(), // MetaEntity
                false, // IsAbstract
                K2TypeDefBO.class, // Service model class
                K2TypeDefController.class // Stateless controller class
            );
        public static MetaModelEntity TYPE_VALUE = new MetaModelEntity(
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
        public static MetaModelEntity K2_LIST = new MetaModelEntity(
                "k2List", // Alias
                "K2 List", // Name
                "Lists", // Plural Name
                "A list defined on an entity", // Description
                null, // SubClasses
                new MetaK2List(), // MetaEntity
                false, // IsAbstract
                K2ListBO.class, // Service model class
                K2ListController.class // Stateless controller class
            );
        public static MetaModelEntity K2_LIST_DEFINITION = new MetaModelEntity(
                "k2ListDef", // Alias
                "K2 List definition", // Name
                "List definitions", // Plural Name
                "The definition of a list of entities to be bound to a list on an entity", // Description
                null, // SubClasses
                null, // MetaEntity
                false, // IsAbstract
                null, // Service model class
                null // Stateless controller class
            );
        public static MetaModelEntity METHOD = new MetaModelEntity(
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
                "An implementation of an expression that can draw values from service literals and if implemented for an entity from the entities fields", // Description
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
                "An expression that takes parameters and returns a value based on those parameters", // Description
                new MetaModelEntity[] {NATIVE_EXPRESSION, IMPLEMENTED_EXPRESSION}, // SubClasses
                new MetaExpression(), // MetaEntity
                true, // IsAbstract
                ExpressionBO.class, // Service model class
                ExpressionController.class // Stateless controller class
            );
        public static MetaModelEntity SERVICE = new MetaModelEntity(
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
                "A binding to a literal, expression, parameter or field value for an expression parameter ", // Description
                null, // SubClasses
                new MetaBoundExpressionParameter(), // MetaEntity
                false, // IsAbstract
                BoundExpressionParameterBO.class, // Service model class
                BoundExpressionParameterController.class // Stateless controller class
            );
    }
    
    private static Map<String, MetaModelEntity> entities = null;
    private static List<MetaModelEntity> entityList = null;
    
    public static List<MetaModelEntity> getEntities() {
        if (entities == null) {
            entities = new HashMap<String, MetaModelEntity>();
            entityList = new ArrayList<MetaModelEntity >();
            entities.put(Entities.COMPONENT.alias, Entities.COMPONENT); entityList.add(Entities.COMPONENT);
            entities.put(Entities.DEPENDENCY.alias, Entities.DEPENDENCY); entityList.add(Entities.DEPENDENCY);
            entities.put(Entities.ENTITY_BINDING.alias, Entities.ENTITY_BINDING); entityList.add(Entities.ENTITY_BINDING);
            entities.put(Entities.ENTITYFORMATTER.alias, Entities.ENTITYFORMATTER); entityList.add(Entities.ENTITYFORMATTER);
            entities.put(Entities.K2ENTITY.alias, Entities.K2ENTITY); entityList.add(Entities.K2ENTITY);
            entities.put(Entities.K2FIELD.alias, Entities.K2FIELD); entityList.add(Entities.K2FIELD);
            entities.put(Entities.K2PERMITTEDCONTENT.alias, Entities.K2PERMITTEDCONTENT); entityList.add(Entities.K2PERMITTEDCONTENT);
            entities.put(Entities.K2SNIPPETBINDING.alias, Entities.K2SNIPPETBINDING); entityList.add(Entities.K2SNIPPETBINDING);
            entities.put(Entities.K2SNIPPETCONTAINER.alias, Entities.K2SNIPPETCONTAINER); entityList.add(Entities.K2SNIPPETCONTAINER);
            entities.put(Entities.K2SNIPPET.alias, Entities.K2SNIPPET); entityList.add(Entities.K2SNIPPET);
            entities.put(Entities.K2SNIPPETPARAMETER.alias, Entities.K2SNIPPETPARAMETER); entityList.add(Entities.K2SNIPPETPARAMETER);
            entities.put(Entities.LITERAL.alias, Entities.LITERAL); entityList.add(Entities.LITERAL);
            entities.put(Entities.PROJECT.alias, Entities.PROJECT); entityList.add(Entities.PROJECT);
            entities.put(Entities.TEMPLATEBINDING.alias, Entities.TEMPLATEBINDING); entityList.add(Entities.TEMPLATEBINDING);
            entities.put(Entities.TEMPLATECONTENT.alias, Entities.TEMPLATECONTENT); entityList.add(Entities.TEMPLATECONTENT);
            entities.put(Entities.TEMPLATE.alias, Entities.TEMPLATE); entityList.add(Entities.TEMPLATE);
            entities.put(Entities.FIELD_SET.alias, Entities.FIELD_SET); entityList.add(Entities.FIELD_SET);
            entities.put(Entities.FIELD_SET_MEMBER.alias, Entities.FIELD_SET_MEMBER); entityList.add(Entities.FIELD_SET_MEMBER);
            entities.put(Entities.NATIVE_FIELD.alias, Entities.NATIVE_FIELD); entityList.add(Entities.NATIVE_FIELD);
            entities.put(Entities.LINKED_FIELD.alias, Entities.LINKED_FIELD); entityList.add(Entities.LINKED_FIELD);
            entities.put(Entities.TYPE_DEFINITION.alias, Entities.TYPE_DEFINITION); entityList.add(Entities.TYPE_DEFINITION);
            entities.put(Entities.TYPE_VALUE.alias, Entities.TYPE_VALUE); entityList.add(Entities.TYPE_VALUE);
            entities.put(Entities.TYPE_FIELD.alias, Entities.TYPE_FIELD); entityList.add(Entities.TYPE_FIELD);
            entities.put(Entities.K2_LIST.alias, Entities.K2_LIST); entityList.add(Entities.K2_LIST);
            entities.put(Entities.K2_LIST_DEFINITION.alias, Entities.K2_LIST_DEFINITION); entityList.add(Entities.K2_LIST_DEFINITION);
            entities.put(Entities.METHOD.alias, Entities.METHOD); entityList.add(Entities.METHOD);
            entities.put(Entities.EXPRESSION.alias, Entities.EXPRESSION); entityList.add(Entities.EXPRESSION);
            entities.put(Entities.NATIVE_EXPRESSION.alias, Entities.NATIVE_EXPRESSION); entityList.add(Entities.NATIVE_EXPRESSION);
            entities.put(Entities.SERVICE.alias, Entities.SERVICE); entityList.add(Entities.SERVICE);
            entities.put(Entities.IMPLEMENTED_EXPRESSION.alias, Entities.IMPLEMENTED_EXPRESSION); entityList.add(Entities.IMPLEMENTED_EXPRESSION);
            entities.put(Entities.EXPRESSION_PARAMETER.alias, Entities.EXPRESSION_PARAMETER); entityList.add(Entities.EXPRESSION_PARAMETER);
            entities.put(Entities.BOUND_EXPRESSION_PARAMETER.alias, Entities.BOUND_EXPRESSION_PARAMETER); entityList.add(Entities.BOUND_EXPRESSION_PARAMETER);
        }
        return entityList;
    }
    
    public static MetaModelEntity getMetaModelEntity(String alias) { getEntities(); return entities.get(alias); }
    
    @SuppressWarnings("rawtypes")
    public static MetaModelEntity getMetaModelEntity(ServiceModel entity) { return getMetaModelEntity(entity.getMetaEntity().alias); }
    
}
