package br.com.wickrss.feed.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.controller.ServletController;
import br.com.wickrss.reader.ChannelStream;
import br.com.wickrss.reader.RSSFeed;
import br.com.wickrss.user.model.User;

@Singleton
public class FeedController extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
	
	private static final long serialVersionUID = 1L;
    
	@Inject
	private CategoryBO categoryBO;
	
	@Inject
	private RSSFeed rssFeed;
	
	@Inject
	private ChannelBO channelBO;
	
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("index()...");
	
		forward("/feed.jsp", request, response);
		
	}
	
	public void categorySelectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("categorySelectList()...");
		
		List<Category> categories = null;
		
		try{
			
			categories = categoryBO.findAll(getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("categorySelectList", e);
			
			printStackTraceToString(e, request);
			
		}
		
		if(categories == null){
			categories = Collections.emptyList();
		}
		
		request.setAttribute("categories", categories);
	
		forward("/categorySelectList.jsp", request, response);
		
	}
	
	public void channelListByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("channelList()...");
		
		List<Channel> channels = null;
		
		try{
			
			Integer categoryID = ((request.getParameter("categoryID") != null && !request.getParameter("categoryID").isEmpty()) ? Integer.valueOf(request.getParameter("categoryID")) : 0);
			
			logger.debug("categoryID = " + categoryID);
			
			channels = channelBO.findByCategory(categoryID, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("channelList", e);
			
			printStackTraceToString(e, request);
			
		}
		
		if(channels == null){
			channels = Collections.emptyList();
		}
		
		request.setAttribute("channels", channels);
	
		forward("/channelList.jsp", request, response);
		
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("delete()...");
		
		try{
			
			Integer id = ((request.getParameter("id") != null && !request.getParameter("id").isEmpty()) ? Integer.valueOf(request.getParameter("id")) : 0);
			
			logger.debug("id = " + id);
			
			channelBO.delete(id, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("delete", e);
			
			printStackTraceToString(e, request);
			
		}
	
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("insert()...");
		
		try{
			
			String link = ((request.getParameter("link") != null) ? request.getParameter("link") : "");
			Integer categoryID = ((request.getParameter("categoryID") != null && !request.getParameter("categoryID").isEmpty()) ? Integer.valueOf(request.getParameter("categoryID")) : 0);
			
			logger.debug("link = " + link);
			logger.debug("categoryID = " + categoryID);
			
			User user = getUserLogged(request, response);
			
			Channel channel = rssFeed.parse(new ChannelStream().getStream(link), user);
			channel.setLink(link);
			channel.setCategory(new Category(categoryID));
			channelBO.insert(channel, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("insert", e);
			
			printStackTraceToString(e, request);
			
		}
	
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("update()...");
		
		try{
			
			Integer id = ((request.getParameter("id") != null && !request.getParameter("id").isEmpty()) ? Integer.valueOf(request.getParameter("id")) : 0);
			Integer categoryID = ((request.getParameter("categoryID") != null && !request.getParameter("categoryID").isEmpty()) ? Integer.valueOf(request.getParameter("categoryID")) : 0);
			
			logger.debug("id = " + id);
			logger.debug("categoryID = " + categoryID);
			
			channelBO.update(id, categoryID, getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("update", e);
			
			printStackTraceToString(e, request);
			
		}
	
	}

	public void parse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("parse()...");
		
		Channel channel = null;
		String link =  "";
		
		try{
			
			link = ((request.getParameter("link") != null) ? request.getParameter("link") : "");
			
			logger.debug("link = " + link);
			
			channel = rssFeed.parse(new ChannelStream().getStream(link), getUserLogged(request, response));
			
		}
		catch(Exception e){
			logger.error("parse", e);
			
			printStackTraceToString(e, request);
			
		}
		
		JSONObject json = new JSONObject();
		
		json.put("link", link);
		json.put("title", (channel != null) ? channel.getTitle() : "");
		json.put("description", (channel != null) ? channel.getDescription() : "");
		
		response.getOutputStream().print(json.toString());
	
	}

}
