package com.k2.dev.web.stateless;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.meta.MetaField;
import com.k2.common.service.ServiceList;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.common.snippets.html.HtmlInline;
import com.k2.common.snippets.html.HtmlLabel;
import com.k2.common.snippets.html.HtmlPage;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.meta.component.MetaK2Entity;
import com.k2.dev.service.K2EntityService;

@Controller
public class K2EntityController {
	
	@Autowired
	private K2EntityService service;
	private OutputFormatterContext<K2Entity, PrintWriter> context;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public K2EntityController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		context = (OutputFormatterContext<K2Entity, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetContext.getBean(SnippetFactory.class));
		context.setVerbose();
	}
	
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/entities/k2Entity/{id}", method=RequestMethod.GET)
	public void getEntity(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		
		K2Entity entity = service.fetch(id);

		
		if (entity.isNull()) {
			response.reset();
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unable to identify entity with id: "+id);
			return;
		}
		
		HtmlPage<PrintWriter> page = context.getSnippet(HtmlPage.class);
		page.addStyleSheets("css/k2-default.css");
		{
			HtmlHeader<PrintWriter> title = context.getSnippet(HtmlHeader.class);
			title.setText("K2 Entity "+entity.getEntityName());
			title.setLevel(1);
		
			page.addToBody(title);
		}
		
		for (@SuppressWarnings({"rawtypes" }) MetaField field : MetaK2Entity.Fields.getFields()) {
			
			
		}
						
		page.output(response.getWriter());

	}
	
}
