package com.k2.dev.model.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.snippets.SnippetFactory;
import com.k2.core.formatters.ControllerWriter;
import com.k2.core.formatters.LiteralWriter;
import com.k2.core.formatters.ServiceExpressionsWriter;
import com.k2.core.formatters.ServiceLiteralsWriter;
import com.k2.core.formatters.ServiceMetaModelWriter;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.expressions.Ex;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.lib.Literals;
import com.k2.dev.model.Component;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.Literal;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.entity.ComponentENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2ServiceService;
import com.k2.dev.service.LiteralService;
import com.k2.dev.service.ProjectService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2ServiceBO extends GenericServiceModel implements ServiceModel, K2Service {
	
	@Autowired(required=true)
	protected K2ServiceService service;
	@Autowired(required=true)
	protected ComponentService componentService;
	@Autowired(required=true)
	protected ImplementedExpressionService implementedExpressionService;
	@Autowired(required=true)
	protected LiteralService literalService;
	@Autowired(required=true)
	protected ProjectService projectService;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Override
	public EntityService getService() { return service; }
	
	public static K2Service NULL = new K2ServiceBO();
	public K2ServiceBO() { super(null); }
	public K2ServiceBO(PersistenceState state) { super(state); }
    public K2ServiceBO(K2ServiceENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2SERVICE; }
	
	public String getIdentity() { return getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2Service Null() { return NULL; }
	
	protected K2ServiceENT entity;
	@Override
	public K2ServiceENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public String getPackageName() { if (isNull()) { return null; } return getEntity().getPackageName(); }
	@Override
	public void setPackageName(String packageName) { if (isNull()) { return; } getEntity().setPackageName(packageName); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2ServiceENT(); }
		if (K2Service.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2Service)source);
		}
	}
	
	// Lists -------
	// Components list
	@Override
	public ServiceList<Component> getComponents() { return componentService.listForService(this); }
	
	// ServiceExpressions list
	@Override
	public ServiceList<ImplementedExpression> getServiceExpressions() { return implementedExpressionService.listForServiceOnly(this); }
	
	// Literals list
	@Override
	public ServiceList<Literal> getLiterals() { return literalService.listForService(this); }

	// Projects list
	@Override
	public ServiceList<Project> getProjects() { return projectService.listAll(); }

	// RootEntities list
	@Override
	public ServiceList<K2Entity> getRootEntities() { return k2EntityService.listRootEntitiesForService(this); }

	// Expressions ---------
	// LibPackagName expression
	@Override
	public String getLibPackageName() { 
		return Ex.STRING.concatenate(
			getPackageName(),
			Literals.libPackageExtension
		);
	}
	
	// MetaPackageName expression
	@Override
	public String getMetaPackageName() {
		return Ex.STRING.concatenate(
				getPackageName(),
				Literals.modelPackageExtension,
				Literals.metaPackageExtension
			);
	}
	
	// BoPackageName expression
	@Override
	public String getBoPackageName() {
		return Ex.STRING.concatenate(
				getPackageName(),
				Literals.modelPackageExtension,
				Literals.boPackageExtention
			);
	}
	
	// MetaComponentPackageName expression
	@Override
	public String getMetaComponentPackageName() {
		return Ex.STRING.concatenate(
				getPackageName(),
				Literals.modelPackageExtension,
				Literals.metaPackageExtension,
				Literals.metaComponentPackageExtension
			);
	}
	
	// WebStatelessPackageName expression
	@Override
	public String getWebStatelessPackageName() {
		return Ex.STRING.concatenate(
				getPackageName(),
				Literals.webPackageExtension,
				Literals.webStatelessPackageExtension
			);
	}
	
	// Methods ----------
	// buildLiterals method
	@Autowired
	SnippetFactory<PrintWriter> snippetFactory;
	@Autowired
	OutputFormatterContextFactory formatterContextFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public void buildLiterals(Project project) {
		
		OutputFormatterContext<?, PrintWriter> context = (OutputFormatterContext<?, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetFactory);
		context.setVerbose();	
		
		File literals = project.getJavaFile(getLibPackageName(), Literals.literalsClassName);
		PrintWriter pw;
		try {
			pw = new PrintWriter(literals);
			
			ServiceLiteralsWriter<PrintWriter> slw = context.getSnippet(ServiceLiteralsWriter.class);
			slw.setEntity(this);
			
			slw.output(pw);

		} catch (FileNotFoundException e) {
			// This shouldn't happen
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void buildExpressions(Project project) {
		
		OutputFormatterContext<?, PrintWriter> context = (OutputFormatterContext<?, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetFactory);
		context.setVerbose();	
		
		File expressions = project.getJavaFile(getLibPackageName(), Literals.expressionsClassName);
		PrintWriter pw;
		try {
			pw = new PrintWriter(expressions);
			
			ServiceExpressionsWriter<PrintWriter> sew = context.getSnippet(ServiceExpressionsWriter.class);
			sew.setEntity(this);
			
			sew.output(pw);

		} catch (FileNotFoundException e) {
			// This shouldn't happen
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildMetaModel(Project project) {
		
		OutputFormatterContext<?, PrintWriter> context = (OutputFormatterContext<?, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetFactory);
		context.setVerbose();	
		
		File metaModel = project.getJavaFile(getMetaPackageName(), Literals.metaModelClassName);
		PrintWriter pw;
		try {
			pw = new PrintWriter(metaModel);
			
			ServiceMetaModelWriter<PrintWriter> smmw = (ServiceMetaModelWriter<PrintWriter>) context.getFormatter(this, ServiceMetaModelWriter.class);
//			ServiceMetaModelWriter<PrintWriter> smmw = context.getSnippet(ServiceMetaModelWriter.class);
//			smmw.setEntity(this);
			
			smmw.output(pw);

		} catch (FileNotFoundException e) {
			// This shouldn't happen
			e.printStackTrace();
		}
	}

}
