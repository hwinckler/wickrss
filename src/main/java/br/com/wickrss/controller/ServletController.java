package br.com.wickrss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

@Singleton
public class ServletController extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(ServletController.class);
	
	private static final long serialVersionUID = 1L;
	
	private final String OAUTH_CALLBACK = "/oauth2callback";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doGet()...");
		doAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost()...");
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doAction()...");
		
		String act = (request.getParameter("act") == null || request.getParameter("act").isEmpty()) ? "index" : request.getParameter("act");

		logger.debug("act: " + act);
		
		try{
			this.getClass().getMethod(act, HttpServletRequest.class, HttpServletResponse.class).invoke(this, request, response); 
		}
		catch(Exception e){
			logger.error("doAction", e);
		}
		
	}
	
	protected void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("forward()...");
		
		getServletContext().getRequestDispatcher("/pages" + path).forward(request,response);
	} 
	
	protected void redirect(String url, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("redirect()...");
		
		response.sendRedirect(url);
	}

	protected void printStackTraceToString(Exception e, HttpServletRequest request) throws ServletException, IOException {
		logger.debug("printStackTraceToString()...");
		
		String erro = "Erro: <br> " + e.getMessage() + "<br>";
		
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		erro += errors.toString();
		
		request.setAttribute("erro", erro);
	}
	
	public User getUserLogged(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
		return (User) request.getSession().getAttribute("user");
	}
	
	protected String getCallbackURL(HttpServletRequest request) throws UnsupportedEncodingException{
		
        return URLEncoder.encode((request.getRequestURL().replace(request.getRequestURL().lastIndexOf("/"), request.getRequestURL().length(), "").append(OAUTH_CALLBACK)).toString(), "UTF-8");
		
	}
}
