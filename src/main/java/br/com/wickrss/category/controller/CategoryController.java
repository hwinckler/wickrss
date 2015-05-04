package br.com.wickrss.category.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.controller.ServletController;

@Singleton
public class CategoryController extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	private static final long serialVersionUID = 1L;
    
	@Inject
	private CategoryBO categoryBO;
	

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("index()...");
	
		forward("/category.jsp", request, response);
		
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("insert()...");
		
		try{
			
			String title = ((request.getParameter("title") != null) ? request.getParameter("title") : "");
			
			logger.debug("title = " + title);
			
			categoryBO.insert(title, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("insert", e);
			
			printStackTraceToString(e, request);
			
		}
		
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("update()...");
		
		try{
			
			String title = ((request.getParameter("title") != null) ? request.getParameter("title") : "");
			Integer id = ((request.getParameter("id") != null) ? Integer.valueOf(request.getParameter("id")) : 0);
			
			logger.debug("id = " + id);
			logger.debug("title = " + title);
			
			categoryBO.update(id, title, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("update", e);
			
			printStackTraceToString(e, request);
			
		}
		
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("delete()...");
		
		try{
			
			Integer id = ((request.getParameter("id") != null) ? Integer.valueOf(request.getParameter("id")) : 0);
			
			logger.debug("id = " + id);
			
			categoryBO.delete(id, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("delete", e);
			
			printStackTraceToString(e, request);
			
		}
		
	}

	public void categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("categoryList()...");
		
		List<Category> categories = null;
		
		try{
			
			categories = categoryBO.findAll(getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("categoryList", e);
			
			printStackTraceToString(e, request);
			
		}
		
		if(categories == null){
			categories = Collections.emptyList();
		}
		
		request.setAttribute("categories", categories);
	
		forward("/categoryList.jsp", request, response);
		
	}
}
