package br.com.wickrss.controller;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class AboutController extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	private static final long serialVersionUID = 1L;
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("index()...");
		
		forward("/about.jsp", request, response);
		
	}
	
}
