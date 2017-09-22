package com.k2.dev.web.stateless;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.snippets.html.HtmlPage;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.dev.model.TemplateContent;

@Controller
public class EntitiesController {
	
	private OutputFormatterContext<TemplateContent, PrintWriter> context;
	
	private ApplicationContext snippetContext;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public EntitiesController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		this.snippetContext = snippetContext;
		context = (OutputFormatterContext<TemplateContent, PrintWriter>)formatterContextFactory.getContext();
		context.setVerbose();
		SnippetFactory<PrintWriter> snippetFactory = snippetContext.getBean(SnippetFactory.class);
		context.setSnippetFactory(snippetFactory);
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/entities", method=RequestMethod.GET)
	public String getEntities(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");
		
		@SuppressWarnings("unchecked")
		HtmlPage<PrintWriter> page = snippetContext.getBean(HtmlPage.class);
		page.setContext(context);
		
		{
			@SuppressWarnings("unchecked")
			HtmlHeader<PrintWriter> title = snippetContext.getBean(HtmlHeader.class);
			title.setText("List of Entities");
			title.setLevel(1);
		
			page.addToBody(title);
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

}
