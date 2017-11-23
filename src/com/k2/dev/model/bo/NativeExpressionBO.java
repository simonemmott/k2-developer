package com.k2.dev.model.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.util.StringUtil;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.expressions.Ex;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2List;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.NativeExpression;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.NativeExpressionENT;
import com.k2.dev.model.entity.NativeExpressionENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ExpressionService;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2ListService;
import com.k2.dev.service.K2MethodService;
import com.k2.dev.service.K2ServiceService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.service.K2TypeValueService;
import com.k2.dev.service.ProjectService;

@SuppressWarnings("rawtypes")
@Configurable
public class NativeExpressionBO extends ExpressionBO implements ServiceModel, NativeExpression {
	
	@Autowired(required=true)
	protected ImplementedExpressionService service;

	@Override
	public EntityService  getService() { return service; }
	
	// Entity methods ------
	public static NativeExpression NULL = new NativeExpressionBO();
	public NativeExpressionBO() { super(null); }
	public NativeExpressionBO(NativeExpressionENT entity, PersistenceState state) { super(state); this.entity = entity; }
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.NATIVE_EXPRESSION; }
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public NativeExpression Null() { return NULL; }
	@Override
	public NativeExpressionENT getEntity() { return (NativeExpressionENT)entity; }
	
	// Fields -----------
	// NativeExpressionType field
	@Override
	public Types.NativeExpressionTypes getNativeExpressionType() { if (isNull()) { return null; } return getEntity().getNativeExpressionType(); }
	@Override
	public void setNativeExpressionType(Types.NativeExpressionTypes nativeExpressionType) { if (isNull()) { return; } getEntity().setNativeExpressionType(nativeExpressionType); changed(); }
	
	// Clone -----
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new ImplementedExpressionENT(); }
		if (NativeExpression.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((NativeExpression)source);
		} else {
			super.clone(source);
		}
	}

}
