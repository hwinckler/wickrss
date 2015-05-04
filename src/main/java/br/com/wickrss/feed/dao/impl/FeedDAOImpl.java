package br.com.wickrss.feed.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.feed.dao.FeedDAO;
import br.com.wickrss.feed.mapper.FeedMapper;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.user.model.User;


public class FeedDAOImpl implements FeedDAO{

	private static final Logger logger = LoggerFactory.getLogger(FeedDAOImpl.class);
	
	@Inject 
	private SqlSessionFactory sqlSessionFactory;
	
	public void insert(Feed feed) {
		logger.debug("insert()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			feedMapper.insert(feed);
			session.commit();
		}
	
	}

	@Override
	public List<Feed> findAll(User user) {
		logger.debug("findAll()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			return feedMapper.findAll(user);
		}
	}

	@Override
	public Feed findById(Integer id, User user) {
		logger.debug("findById()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			return feedMapper.findById(id, user);
		}
	}

	@Override
	public void deleteByChannel(Integer id, User user) {
		logger.debug("deleteByChannel()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			feedMapper.deleteByChannel(id, user);
			session.commit();
		}
		
	}

	@Override
	public List<Feed> findByCategory(Integer categoryID, User user) {
		logger.debug("findByCategory()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			return feedMapper.findByCategory(categoryID, user);
		}
	}

	@Override
	public void markAsRead(Integer feedID, User user) {
		logger.debug("markAsRead()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			feedMapper.markAsRead(feedID, user);
			session.commit();
		}
	}

	@Override
	public void markAllAsRead(Integer categoryID, User user) {
		logger.debug("markAllAsRead()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			feedMapper.markAllAsRead(categoryID, user);
			session.commit();
		}
	}

	@Override
	public List<Feed> findUnread(User user) {
		logger.debug("findUnread()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			FeedMapper feedMapper = session.getMapper(FeedMapper.class);
			return feedMapper.findUnread(user);
		}
	}
}
