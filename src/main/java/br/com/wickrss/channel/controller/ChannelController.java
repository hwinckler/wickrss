package br.com.wickrss.channel.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.controller.ServletController;
import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.reader.ChannelStream;
import br.com.wickrss.reader.RSSFeed;
import br.com.wickrss.user.model.User;

@Singleton
public class ChannelController extends ServletController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	
	private static final long serialVersionUID = 1L;
    
	@Inject
	private ChannelBO channelBO;
	
	@Inject
	private FeedBO feedBO;
	
	@Inject
	private RSSFeed rssFeed;
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logger.debug("index()...");
	
		forward("/index.jsp", request, response);
		
	}
	
	public void synchronize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("synchronize()...");
		
		try{
		
			User user = getUserLogged(request, response);
			
			List<Channel> channels = channelBO.findAllWithLastPubDate(user);
			List<Feed> feeds = null;
			Integer countUnRead = 0;
			
			for (Channel channel : channels) {
				
				logger.debug("link: " + channel.getLink() + " lastPubDate: " + channel.getLastPubDate());
				
				try{

					if((feeds = (rssFeed.parse(new ChannelStream().getStream(channel.getLink()), user, channel.getLastPubDate())).getFeeds()).size() > 0){
						
						for (Feed feed : feeds) {
							feed.setChannel(channel);
							
							logger.debug("title: " + feed.getTitle() + " pubDate: " + feed.getPubDate());
							feedBO.insert(feed);
							countUnRead++;
						}
					}
				}
				catch(Exception e){
					logger.error("synchronize", e);
				}
				
			}
			
			response.getOutputStream().print(countUnRead);
			
		}
		catch(Exception e){
			logger.error("synchronize", e);

			printStackTraceToString(e, request);
		}
		
	}

}
