package br.com.wickrss.login.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.controller.ServletController;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;


@Singleton
public class Oauth2Callback extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(Oauth2Callback.class);
	
	private static final long serialVersionUID = 1L;
	
	private final String URL_TOKEN = "https://accounts.google.com/o/oauth2/token";
	private final String URL_INFO = "https://www.googleapis.com/oauth2/v1/userinfo";
	
	@Inject
	private UserBO userBO;
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("index()...");
	
		try{
			
			String code = request.getParameter("code");
			
			logger.debug("code: " + code);
			
			if(code != null && !code.isEmpty()){

				final String CLIENT_ID = getServletContext().getInitParameter("CLIENT_ID");
				final String CLIENT_SECRET = getServletContext().getInitParameter("CLIENT_SECRET");
				
				String parameters = getURLParameters(code, CLIENT_ID, CLIENT_SECRET, getCallbackURL(request));
				
				logger.debug("parameters: " + parameters);
				
	            URL url = new URL(URL_TOKEN);
	            URLConnection urlConn = url.openConnection();
	            urlConn.setDoOutput(true);
	            
	            try(OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream())){
	            	writer.write(parameters);
	            	writer.flush();
	            }
	            
	            String output = "";
	            try(BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()))){
	            	String line = "";
		            while ((line = reader.readLine()) != null) {
		                output += line;
		            }
	            }
	            
	            logger.debug("output: " + output);
	            
	            JSONObject json = (JSONObject) new JSONParser().parse(output);
	            
	            String token = json.get("access_token").toString();
	            
	            if(token != null && !token.isEmpty()){

		            url = new URL(URL_INFO + "?access_token=" + token);
		            urlConn = url.openConnection();
		           
		            try(BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()))){
		            	String line = "";
		            	output = "";
			            while ((line = reader.readLine()) != null) {
			            	output += line;
			            }
		            }
		            
		            logger.debug("output final: " + output);
		            
		            if(output != null && !output.isEmpty()){
		            	
		            	JSONObject userJson = (JSONObject) new JSONParser().parse(output);
		                
		            	User user = new User();
		            	user.setDate(new Date());
		            	user.setEmail(userJson.get("email").toString());
		            			            	
		            	user = userBO.createsNotExist(user);
		            	
		            	user.setPicture(userJson.get("picture").toString());
		            	
		            	request.getSession().setAttribute("user", user);
		            	
		            	response.sendRedirect("index");
		            	
		            	//forward("/index", request, response);
		            	
		            	//getServletContext().getRequestDispatcher("/").forward(request,response);
		            	
			            return;
		            }
	            	
	            }

			}

		}
		catch(Exception e){
			logger.error("login", e);
			
			printStackTraceToString(e, request);
			
		}
		
		//redirect("signin.jsp", response);
		forward("/index", request, response);
		
	}

	private String getURLParameters(String code, String CLIENT_ID, String CLIENT_SECRET, String callBackUrl) {

        return "code=" + code + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri=" + callBackUrl + "&grant_type=authorization_code";

	}
	

}
