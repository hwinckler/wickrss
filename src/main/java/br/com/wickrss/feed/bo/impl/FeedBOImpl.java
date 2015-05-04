package br.com.wickrss.feed.bo.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.dao.FeedDAO;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.user.model.User;

public class FeedBOImpl implements FeedBO {

	private static final Logger logger = LoggerFactory.getLogger(FeedBOImpl.class);

	@Inject
	private FeedDAO feedDAO;

	@Override
	public List<Feed> findAll(User user) {
		logger.debug("findAll()...");
		return feedDAO.findAll(user);
	}

	@Override
	public void insert(Feed feed) {
		logger.debug("insert()...");
		feedDAO.insert(feed);
	}

	@Override
	public void deleteByChannel(Integer id, User user) {
		logger.debug("deleteByChannel()...");
		feedDAO.deleteByChannel(id, user);
	}

	@Override
	public List<Feed> findByCategory(Integer categoryID, User user) {
		logger.debug("findByCategory()...");
		return feedDAO.findByCategory(categoryID, user);
	}

	@Override
	public void markAsRead(Integer id, User user) {
		logger.debug("markAsRead()...");
		feedDAO.markAsRead(id, user);
	}

	@Override
	public void markAllAsRead(Integer categoryID, User user) {
		logger.debug("markAllAsRead()...");
		feedDAO.markAllAsRead(categoryID, user);		
	}

	@Override
	public Feed findById(Integer id, User user) {
		logger.debug("findById()...");
		return feedDAO.findById(id, user);	
	}

	@Override
	public List<Feed> findUnread(User user) {
		logger.debug("findUnread()...");
		return feedDAO.findUnread(user);	
	}

}
