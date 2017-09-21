package com.k2.dev.web.stateless;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.k2.dev.service.K2EntityService;

@Controller
public class K2EntityController {
	
	@Autowired
	private K2EntityService service;
	
	@RequestMapping(value="/entities/k2entity", method=RequestMethod.GET)
	public String getEntity(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return null;
	}

}
