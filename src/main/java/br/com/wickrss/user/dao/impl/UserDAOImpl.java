package br.com.wickrss.user.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.user.dao.UserDAO;
import br.com.wickrss.user.mapper.UserMapper;
import br.com.wickrss.user.model.User;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Inject 
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insert(User user) {
		logger.debug("insert()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insert(user);
			session.commit();
		}
	}
	
	@Override
	public void delete(String email) {
		logger.debug("delete()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.delete(email);
			session.commit();
		}
	}

	@Override
	public User findByEmail(String email) {
		logger.debug("findByEmail()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.findByEmail(email);
		}
	}

	@Override
	public List<User> findAll() {
		logger.debug("findAll()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.findAll();
		}
	}

}
