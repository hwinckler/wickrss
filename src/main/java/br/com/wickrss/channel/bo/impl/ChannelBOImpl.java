package br.com.wickrss.channel.bo.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.bo.impl.CategoryBOImpl;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.dao.ChannelDAO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.ChannelException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.user.model.User;

public class ChannelBOImpl implements ChannelBO {

	private static final Logger logger = LoggerFactory.getLogger(CategoryBOImpl.class);

	@Inject
	private ChannelDAO channelDAO;
	
	@Inject
	private CategoryBO categoryBO;
	
	@Inject
	private FeedBO feedBO;


	public void updateToDefaultCategory(Integer id, User user) throws UserException {
		logger.debug("updateToDefaultCategory()...");
		
		Category category = categoryBO.findDefaultCategory(user);
		
		channelDAO.updateToDefaultCategory(id, category.getId(), user);

	}

	@Override
	public List<Channel> findByCategory(Integer categoryID, User user) {
		logger.debug("findByCategory()...");
		
		
		return channelDAO.findByCategory(categoryID, user);
	}

	@Override
	public void insert(Channel channel, User user) throws ChannelException, CategoryException, UserException {
		logger.debug("insert()...");
		
		if(channel.getCategory() == null){
			
			Category category = categoryBO.findDefaultCategory(user);
			
			channel.setCategory(category);
		}
		
		channel.setUser(user);
		
		channelDAO.insert(channel);
		List<Feed> feeds = null;
		if(!(feeds = channel.getFeeds()).isEmpty()){
			for (Feed feed : feeds) {
				feedBO.insert(feed);
			}
		}
	}

	@Override
	public Channel findById(Integer id, User user) {
		logger.debug("findById()...");
		return channelDAO.findById(id, user);
	}

	@Override
	public void delete(Integer id, User user) {
		logger.debug("findById()...");
		
		feedBO.deleteByChannel(id, user);
		channelDAO.delete(id, user);
	}

	@Override
	public void update(Integer id, Integer categoryID, User user) {
		logger.debug("update()...");
		
		channelDAO.update(id, categoryID, user);
	}


	@Override
	public List<Channel> findAll(User user) {
		logger.debug("findAll()...");
		return channelDAO.findAll(user);
	}

	@Override
	public List<Channel> findAllWithLastPubDate(User user) {		logger.debug("findAllWithLastPubDate()...");
		return channelDAO.findAllWithLastPubDate(user);
	}

}
