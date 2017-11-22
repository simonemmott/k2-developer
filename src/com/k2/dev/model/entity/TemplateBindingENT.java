package com.k2.dev.model.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.util.K2Type;
import com.k2.dev.model.Project;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.bo.K2SnippetParameterBO;
import com.k2.dev.model.bo.TemplateBindingBO;

@Entity(name="TemplateBinding")
@Table(name="TemplateBindings")
@PrimaryKeyJoinColumn(name="SnippetBindingID")
public class TemplateBindingENT extends K2SnippetBindingENT {
	
	public static class Types {
		public static enum BindingSource implements K2Type {
			NULL(0, "Not set", "This type has is not set."),
			LITERAL(1, "From literal", "The binding value comes from a litteral value defined on the binding its self."),
			PARAMETER(2, "From paramter", "The bindign value comes for a parameter passed to the template.");
			
			private int id;
			private String name;
			private String description;
			
			BindingSource(int id, String name, String description) { 
				this.id = id; 
				this.name = name;
				this.description = description;
			}
			
			@Override
			public int getId() { return this.id; }
			@Override
			public String getName() { return this.name; }
			@Override
			public String getDescription() { return this.description; }
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ServiceModel getServiceModel(PersistenceState state) { return new TemplateBindingBO(this, state); }
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=K2SnippetParameterENT.class, optional=true)
	@JoinColumn(name="BindingParameterId", nullable=true)
	@Expose(serialize=false)
	protected K2SnippetParameterENT bindingParamter;
	public K2SnippetParameterENT getBindingParameter() { return bindingParamter; }
	public void setBindingParameter(K2SnippetParameterENT bindingParamter) { this.bindingParamter = bindingParamter; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=TemplateENT.class, optional=true)
	@JoinColumn(name="BindingTemplateID", nullable=true)
	@Expose(serialize=false)
	protected TemplateENT bindingTemplate;
	public TemplateENT getBindingTemplate() { return bindingTemplate; }
	public void setBindingTemplate(TemplateENT bindingTemplate) { this.bindingTemplate = bindingTemplate; }
	
	@Expose(serialize=true)
	@Column(name="BindingSource", length=50)
	protected Types.BindingSource bindingSource;
	public Types.BindingSource getBindingSource() { return bindingSource; }
	public void setBindingSource(Types.BindingSource bindingSource) { this.bindingSource = bindingSource; }

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=LiteralENT.class, optional=true)
	@JoinColumn(name="LiteralID", nullable=true)
	@Expose(serialize=true)
	protected LiteralENT literalValue;
	public LiteralENT getLiteral() { return literalValue; }
	public void setLiteral(LiteralENT literalValue) { this.literalValue = literalValue; }
	
	@SuppressWarnings("rawtypes")
	public void clone(ServiceModel source) {
		super.clone(source);
		if (TemplateBinding.class.isAssignableFrom(source.getClass())) {
			TemplateBinding clone = (TemplateBinding)source;
			bindingParamter = clone.getBindingParameter().getEntity();
			bindingTemplate = clone.getBindingTemplate().getEntity();
			bindingSource = clone.getBindingSource();
			literalValue = clone.getLiteral().getEntity();
		}
	}



}
