package com.k2.dev.web.stateless;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.interaction.FieldHandler;
import com.k2.common.interaction.Message;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaLinkedField;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceModel;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlFieldRow;
import com.k2.common.snippets.html.HtmlForm;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.common.snippets.html.HtmlMessage;
import com.k2.common.snippets.html.HtmlOkButton;
import com.k2.common.snippets.html.HtmlPage;
import com.k2.common.util.StringUtil;
import com.k2.common.writeEvents.ValidationException;

public abstract class AbstractStatelessEntityController<E> {
	
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	protected PlatformTransactionManager ptm;
	protected OutputFormatterContext<E, PrintWriter> context;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public AbstractStatelessEntityController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		context = (OutputFormatterContext<E, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetContext.getBean(SnippetFactory.class));
		context.setVerbose();
	}
	
	protected DefaultTransactionDefinition getDefaultTransactionDef(String transactioNName) {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be done programmatically
		def.setName(transactioNName);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return def;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void updateEntity(ServiceModel entity, List<Message> messages, HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parmName = parameterNames.nextElement();
			String parmValue = request.getParameter(parmName);
			if (!parmName.equals("k2-action")) {
				MetaField field = getMetaField(parmName);
				FieldHandler handler = field.getHandler(entity);
				if (MetaLinkedField.class.isAssignableFrom(handler.metaField.getClass())) {
					MetaLinkedField linkedField = (MetaLinkedField)field;
					EntityService es = (EntityService) appContext.getBean(linkedField.serviceClass);
					if ("NULL".equalsIgnoreCase(parmValue)) {
						handler.set(es.nullBO());
					} else {
						ServiceModel sm = es.fetch(StringUtil.toLong(parmValue));
						handler.set(sm);
					}
				} else {
					handler.setFromUI(request.getParameter(parmName));
				}
			}
			
		}
		try {
			entity.write();
		} catch (ValidationException e) {
			messages.add(new Message(Message.Level.WARNING, "Validation exception: "+e.getMessage()));
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract MetaField getMetaField(String parmName);
	
	@SuppressWarnings("rawtypes")
	protected abstract List<MetaField> getMetaFields();

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void presentEntity(ServiceModel entity, List<Message> messages, HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");

		HtmlPage<PrintWriter> page = context.getSnippet(HtmlPage.class);
		page.addStyleSheets("../../css/k2-default.css");
		{
			HtmlHeader<PrintWriter> title = context.getSnippet(HtmlHeader.class);
			title.setText("K2 Entity "+entity.getIdentity());
			title.setLevel(1);
		
			page.addToBody(title);
		}
		for (Message message : messages) {
			page.addToBody(context.getSnippet(HtmlMessage.class).setLevel(message.level.getLevel()).setMessage(message.message));
		}
		{
			HtmlForm<PrintWriter> form = context.getSnippet(HtmlForm.class);
			page.addToBody(form);
		
			for (MetaField field : getMetaFields()) {
				HtmlFieldRow row = context.getSnippet(HtmlFieldRow.class);
				row.addToRow(context.getSnippet(entity, field));
				form.addToBody(row);
			}
			{
				HtmlButton<PrintWriter> cancelButton = context.getSnippet(HtmlButton.class);
				cancelButton.setLabel("Cancel");
//				cancelButton.setAction("?k2-action=cancel");
				cancelButton.setOnClick("window.history.go(-1); return false;");
				form.addToBody(cancelButton);
			}
			{
				HtmlOkButton<PrintWriter> okButton = context.getSnippet(HtmlOkButton.class);
				form.addToBody(okButton);
			}
		}
						
		page.output(response.getWriter());

	}
	
}
