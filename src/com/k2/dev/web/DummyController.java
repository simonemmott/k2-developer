package com.k2.dev.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DummyController {
	
	public DummyController() {
		System.out.println("Creating dummy controller!");
	}
	
	@RequestMapping(value="/dummy", method=RequestMethod.GET)
	public String dummy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		response.getWriter().append("Hello Spring! Served at: ").append(request.getContextPath()+" Oh yeah!!!!");
		
		return null;
	}
	
	

}
