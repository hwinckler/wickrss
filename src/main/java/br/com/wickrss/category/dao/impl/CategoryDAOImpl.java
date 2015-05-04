package br.com.wickrss.category.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.category.dao.CategoryDAO;
import br.com.wickrss.category.mapper.CategoryMapper;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.user.model.User;


public class CategoryDAOImpl implements CategoryDAO{

	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	@Inject 
	private SqlSessionFactory sqlSessionFactory;
	
	public void insert(Category category) {
		logger.debug("insert()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			categoryMapper.insert(category);
			session.commit();
		}
	}

	@Override
	public List<Category> findAll(User user) {
		logger.debug("findAll()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			return categoryMapper.findAll(user);
		}
	}
	
	@Override
	public List<Category> findAllWithUnRead(User user) {
		logger.debug("findAllWithUnRead()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			return categoryMapper.findAllWithUnRead(user);
		}
	}

	@Override
	public Category find(Category category) {
		logger.debug("find()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			return categoryMapper.find(category);
		}
	}

	@Override
	public void delete(Category category) {
		logger.debug("delete()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			categoryMapper.delete(category);
			session.commit();
		}
	}

	@Override
	public void update(Category category) {
		logger.debug("update()...");
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			categoryMapper.update(category);
			session.commit();
		}
	}

	@Override
	public Category findDefaultCategory(User user) {
		logger.debug("findDefaultCategory()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			return categoryMapper.findDefaultCategory(user);
		}
	}

	@Override
	public Category findById(Integer id, User user) {
		logger.debug("findById()...");
		
		try(SqlSession session = sqlSessionFactory.openSession()){
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
			return categoryMapper.findById(id, user);
		}
	}

}
