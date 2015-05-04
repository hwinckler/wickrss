package br.com.wickrss.login.controller;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.controller.ServletController;

@Singleton
public class LoginController extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private static final long serialVersionUID = 1L;
	
	
    
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("index()...");
	
		redirect("signin.jsp", response);
		
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("login()...");
		
		try{
			
			final String CLIENT_ID = getServletContext().getInitParameter("CLIENT_ID");
			final String PERMISSIONS = getServletContext().getInitParameter("PERMISSIONS");
			
			String url = "https://accounts.google.com/o/oauth2/auth?scope="+ PERMISSIONS +"&state=profile&redirect_uri=" + getCallbackURL(request) + "&response_type=code" + "&client_id=" + CLIENT_ID;
			
			logger.debug("url: " + url);
            
            redirect(url, response);
			
		}
		catch(Exception e){
			logger.error("login", e);
			
			printStackTraceToString(e, request);
			
		}
		
	}
	

	
}
