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
import com.k2.dev.lib.Literals;
import com.k2.dev.model.BoundExpressionParameter;
import com.k2.dev.model.Component;
import com.k2.dev.model.Expression;
import com.k2.dev.model.ExpressionParameter;
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
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.ExpressionParameterENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.BoundExpressionParameterService;
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
public class ImplementedExpressionBO extends ExpressionBO implements ServiceModel, ImplementedExpression {
	
	@Autowired(required=true)
	protected ImplementedExpressionService service;
	@Autowired(required=true)
	protected ExpressionService expressionService;
	@Autowired(required=true)
	protected K2ServiceService k2ServiceService;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Autowired(required=true)
	protected BoundExpressionParameterService boundExpressionParameterService;

	@Override
	public EntityService  getService() { return service; }
	
	// Entity methods ------
	public static ImplementedExpression NULL = new ImplementedExpressionBO();
	public ImplementedExpressionBO() { super(null); }
	public ImplementedExpressionBO(ImplementedExpressionENT entity, PersistenceState state) { super(state); this.entity = entity; }
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.IMPLEMENTED_EXPRESSION; }
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public ImplementedExpression Null() { return NULL; }
	@Override
	public ImplementedExpressionENT getEntity() { return (ImplementedExpressionENT)entity; }
	
	// Fields -----------
	// Implements field
	@Override
	public Expression getImplementsExpression() { if (isNull()) { return ExpressionBO.NULL; } return Nvl.nvl(expressionService.getBO(getEntity().getImplementsExpression()), ExpressionBO.NULL); }
	@Override
	public void setImplementsExpression(Expression implementsExpression) { if (isNull()) { return; } getEntity().setImplementsExpression(implementsExpression.getEntity()); changed(); }
	
	// K2Service field
	@Override
	public K2Service getK2Service() { if (isNull()) { return K2ServiceBO.NULL; } return Nvl.nvl(k2ServiceService.getBO(getEntity().getK2Service()), K2ServiceBO.NULL); }
	@Override
	public void setK2Service(K2Service k2Service) { if (isNull()) { return; } getEntity().setK2Service(k2Service.getEntity()); changed(); }

	// K2Entity field
	@Override
	public K2Entity getK2Entity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getK2Entity()), K2EntityBO.NULL); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } getEntity().setK2Entity(k2Entity.getEntity()); changed(); }
	
	// DiscriminatorType field
	@Override
	public BoundExpressionParameter getBoundToParameter() { if (isNull()) { return BoundExpressionParameterBO.NULL; } return Nvl.nvl(boundExpressionParameterService.getBO(getEntity().getBoundToParameter()), BoundExpressionParameterBO.NULL); }
	@Override
	public void setBoundToParameter(BoundExpressionParameter boundToParameter) { if (isNull()) { return; } getEntity().setBoundToParameter(boundToParameter.getEntity()); changed(); }
	
	// Clone -----
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new ImplementedExpressionENT(); }
		if (ImplementedExpression.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((ImplementedExpression)source);
		} else {
			super.clone(source);
		}
	}
	
	// Lists ----------
	// ImplementableExpressions list
	@Override
	public ServiceList<Expression> getImplementableExpressions() { return expressionService.listOfNativeOrServiceOrEntityExpressions(getK2Service(), getK2Entity()); }
	
	// Services list
	@Override
	public ServiceList<K2Service> getServices() { return k2ServiceService.listAll(); }
	
	// Entities list
	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listForService(getK2Service()); }
	
	// ParameterBindings list
	@Override
	public ServiceList<BoundExpressionParameter> getParameterBindings() { return boundExpressionParameterService.listImplementedExpression(this); }
	
	@Override
	public ServiceList<BoundExpressionParameter> getBoundExpressionParameters() { return boundExpressionParameterService.listAll(); }
	
	// Expressions -----------
	// ImplementedExpressionTypeAlias expression
	@Override
	public String getImplementedExpressionTypeAlias() {
		
		return Ex.STRING.decode(
			getImplementsExpression().getExpressionType(),
			ExpressionENT.Types.ExpressionType.NATIVE,
			nativeExpressionTypeAlias(),
			implementedExpressionTypeAlias()
		);
	}
	
	private String nativeExpressionTypeAlias() {
		return Ex.STRING.concatenate(
			Literals.nativeExpressionClassName,
			Literals.canonicalPathSeparator,
			upperNativeReturnType(),
			Literals.canonicalPathSeparator,
			getImplementsExpression().getAlias()
		);
	}
	
	private String upperNativeReturnType() {
		return Ex.STRING.decode(
			this.getImplementsExpression().getReturnType(),
			ExpressionENT.Types.ReturnType.BOOLEAN,
			Literals.booleanUpperName,
			ExpressionENT.Types.ReturnType.INTEGER,
			Literals.integerUpperName,
			ExpressionENT.Types.ReturnType.LONG,
			Literals.longUpperName,
			ExpressionENT.Types.ReturnType.FLOAT,
			Literals.floatUpperName,
			ExpressionENT.Types.ReturnType.DOUBLE,
			Literals.doubleUpperName,
			ExpressionENT.Types.ReturnType.DATE,
			Literals.dateUpperName,
			ExpressionENT.Types.ReturnType.STRING,
			Literals.stringUpperName
		);
	}
	
	private String implementedExpressionTypeAlias() {
		return Ex.STRING.concatenate(
			Literals.expressionsClassName,
			Literals.canonicalPathSeparator,
			this.getImplementsExpression().getAlias()
		);
	}
	
	// JavaDataType expression
	@Override
	public String getJavaDataType() {
		return Ex.STRING.decode(
			this.getReturnType(), 
			ExpressionENT.Types.ReturnType.BOOLEAN,
			Literals.booleanName,
			ExpressionENT.Types.ReturnType.DATE,
			Literals.dateName,
			ExpressionENT.Types.ReturnType.DOUBLE,
			Literals.doubleName,
			ExpressionENT.Types.ReturnType.FLOAT,
			Literals.floatName,
			ExpressionENT.Types.ReturnType.INTEGER,
			Literals.integerName,
			ExpressionENT.Types.ReturnType.LONG,
			Literals.longName,
			ExpressionENT.Types.ReturnType.STRING,
			Literals.stringName
		);
	}
	
	

}
