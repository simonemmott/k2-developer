package com.k2.dev.web.stateless;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.interaction.Message;
import com.k2.common.meta.MetaField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.common.snippets.html.HtmlInline;
import com.k2.common.snippets.html.HtmlLabel;
import com.k2.common.snippets.html.HtmlPage;
import com.k2.common.web.AbstractStatelessEntityController;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.meta.component.MetaK2Entity;
import com.k2.dev.service.K2EntityService;

@Controller
public class K2EntityController extends AbstractStatelessEntityController<K2Entity> {
	
	@Autowired
	private K2EntityService service;
	
	@Autowired
	public K2EntityController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		super(formatterContextFactory, snippetContext);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaField> getMetaFields() { return MetaK2Entity.Fields.getFields(); }
	
	@Override
	@SuppressWarnings("rawtypes")
	protected MetaField getMetaField(String parmName) { return  MetaK2Entity.Fields.getMetaField(parmName); }
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@RequestMapping(value="/entities/k2Entity", method=RequestMethod.GET)
	public String getEntityList(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");

		HtmlPage<PrintWriter> page = context.getSnippet(HtmlPage.class);
		page.addStyleSheets("css/k2-default.css");
		
		{
			HtmlHeader<PrintWriter> title = context.getSnippet(HtmlHeader.class);
			title.setText("List of K2 Entities");
			title.setLevel(1);
		
			page.addToBody(title);
		}
				
		ServiceList<K2Entity> entities = service.listAll();
		while (entities.hasNext()) {
			K2Entity entity = entities.next();
			HtmlInline<PrintWriter> inline = context.getSnippet(HtmlInline.class);
			{
				HtmlLabel<PrintWriter> entityLabel = context.getSnippet(HtmlLabel.class);
				entityLabel.setLabel(entity.getEntityName());
				inline.addToBody(entityLabel);
			}
			{
				HtmlButton<PrintWriter> button = context.getSnippet(HtmlButton.class);
				button.setLabel("Show");
				button.setAction("k2Entity/"+entity.getID());
				inline.addToBody(button);
			}
			page.addToBody(inline);
			
		}
				
		try {
			page.output(response.getWriter());
		} catch (IOException e) {
			response.reset();
			try {
				response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}
	
	@RequestMapping(value="/entities/k2Entity/{id}", method=RequestMethod.POST)
	public void postEntity(
			@RequestParam(value="k2-action", required=false) String k2Action, 
			@PathVariable("id") Long id, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		TransactionStatus status = ptm.getTransaction(getDefaultTransactionDef("StateLessK2Entity"));		
		
		try {
			List<Message> messages = new ArrayList<Message>();

			K2Entity entity = service.fetch(id);
			if (entity.isNull()) {
				response.reset();
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unable to identify entity with id: "+id);
				return;
			}
			
			if (k2Action != null) {
				if (k2Action.equals("ok")) {

					updateEntity(entity, messages, request);

				}
			}
			
			presentEntity(entity, messages, request, response);
			
		} catch (RuntimeException e) {
			  ptm.rollback(status);
		} finally {
			if (!status.isCompleted()) {
				ptm.commit(status);
			}
		}
	}

	
	@RequestMapping(value="/entities/k2Entity/{id}", method=RequestMethod.GET)
	public void getEntity(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		TransactionStatus status = ptm.getTransaction(getDefaultTransactionDef("StateLessK2Entity"));

		try {

			List<Message> messages = new ArrayList<Message>();
			K2Entity entity = service.fetch(id);
		
			if (entity.isNull()) {
				response.reset();
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unable to identify entity with id: "+id);
				return;
			}
			
			presentEntity(entity, messages, request, response);

		} catch (RuntimeException e) {
			  ptm.rollback(status);
		} finally {
			if (!status.isCompleted()) {
				ptm.commit(status);
			}
		}
		return;
	}
	
}
