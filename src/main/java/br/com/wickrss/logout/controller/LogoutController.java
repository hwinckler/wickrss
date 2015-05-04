package br.com.wickrss.logout.controller;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.controller.ServletController;

@Singleton
public class LogoutController extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	
	private static final long serialVersionUID = 1L;
	
	
    
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("index()...");
		
		request.getSession().invalidate();
	
		redirect("signin.jsp", response);
		
	}

}
