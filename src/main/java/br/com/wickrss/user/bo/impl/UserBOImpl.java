package br.com.wickrss.user.bo.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.InvalidUserException;
import br.com.wickrss.exception.UserAlreadExistsException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.dao.UserDAO;
import br.com.wickrss.user.model.User;

public class UserBOImpl implements UserBO {

	private static final Logger logger = LoggerFactory.getLogger(UserBOImpl.class);
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private CategoryBO categoryBO;
	
	@Override
	public void insert(User user) throws UserException{
		logger.debug("insert()...");
		validate(user);
		
		if(findByEmail(user.getEmail()) != null)
			throw new UserAlreadExistsException("user already exists!");
		
		userDAO.insert(user);
	}

	@Override
	public User findByEmail(String email) {
		logger.debug("findByEmail()...");
		return userDAO.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		logger.debug("findAll()...");
		return userDAO.findAll();
	}

	@Override
	public void delete(String email) {
		logger.debug("delete()...");
		userDAO.delete(email);
	}

	@Override
	public User createsNotExist(User user) throws CategoryException, UserException {
		logger.debug("createsNotExist()...");
		validate(user);
		
		User u = findByEmail(user.getEmail());
		if(u == null){
			userDAO.insert(user);
			
			categoryBO.createDefaultCategory(user);
			
			return user;
		}
		return u;
	}

	private void validate(User user) throws UserException{
		if(user == null || (user.getEmail() == null || user.getEmail().isEmpty()))
			throw new UserException("User can't be null");		
	}
}
