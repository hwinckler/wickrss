package br.com.wickrss.channel.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.channel.dao.ChannelDAO;
import br.com.wickrss.channel.mapper.ChannelMapper;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.user.model.User;


public class ChannelDAOImpl implements ChannelDAO{

	private static final Logger logger = LoggerFactory.getLogger(ChannelDAOImpl.class);
	
	@Inject 
	private SqlSessionFactory sqlSessionFactory;
	
	public void insert(Channel channel) {
		logger.debug("insert()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			channelMapper.insert(channel);
			session.commit();
		}
	}

	@Override
	public List<Channel> findAll(User user) {
		logger.debug("findAll()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			return channelMapper.findAll(user);
		}
	}

	@Override
	public Channel findById(Integer id, User user) {
		logger.debug("findById()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			return channelMapper.findById(id, user);
		}
	}

	@Override
	public List<Channel> findByCategory(Integer categoryID, User user) {
		logger.debug("findByCategory()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			return channelMapper.findByCategory(categoryID, user);
		}
	}

	@Override
	public void delete(Integer id, User user) {
		logger.debug("delete()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			channelMapper.delete(id, user);
			session.commit();
		}
	}

	@Override
	public void update(Integer id, Integer categoryID, User user) {
		logger.debug("update()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			channelMapper.update(id, categoryID, user);
			session.commit();
		}
	}
	
	@Override
	public void updateToDefaultCategory(Integer categoryID, Integer defaultCategoryID, User user) {
		logger.debug("update()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			channelMapper.updateToDefaultCategory(categoryID, defaultCategoryID, user);
			session.commit();
		}
	}

	@Override
	public List<Channel> findAllWithLastPubDate(User user) {
		logger.debug("findAllWithLastPubDate()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			ChannelMapper channelMapper = session.getMapper(ChannelMapper.class);
			return channelMapper.findAllWithLastPubDate(user);
		}
	}

}
