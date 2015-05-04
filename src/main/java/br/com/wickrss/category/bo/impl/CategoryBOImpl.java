package br.com.wickrss.category.bo.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.dao.CategoryDAO;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.exception.CategoryAlreadExistsException;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.InvalidCategoryException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.user.model.User;

public class CategoryBOImpl implements CategoryBO {

	private static final Logger logger = LoggerFactory.getLogger(CategoryBOImpl.class);
	
	@Inject
	private CategoryDAO categoryDAO;
	@Inject
	private ChannelBO channelBO;
	
	public void insert(String title, User user) throws CategoryException, UserException {
		logger.debug("insert()...");
		
		validate(title, user);
		
		Category category = new Category(user, title);

		if(categoryDAO.find(category) != null){
			throw new CategoryAlreadExistsException("Title already exists");
		}
		
		categoryDAO.insert(category);
	}

	private void validate(String title, User user)throws InvalidCategoryException, UserException {
		if(title == null || title.isEmpty()){
			throw new InvalidCategoryException("Title is empty");
		}
		
		validate(user);
	}

	private void validate(User user) throws UserException {
		if(user == null || (user.getEmail() == null || user.getEmail().isEmpty())){
			throw new UserException("User can't be null");
		}
	}

	public List<Category> findAllWithUnRead(User user) {
		logger.debug("findAllWithUnRead()...");
		return categoryDAO.findAllWithUnRead(user);
	}
	
	public List<Category> findAll(User user) {
		logger.debug("findAll()...");
		return categoryDAO.findAll(user);
	}

	public Category find(String title, User user) throws InvalidCategoryException, UserException {
		logger.debug("findById()...");
		
		validate(title, user);
		
		return categoryDAO.find(new Category(user, title));
	}

	public void delete(Integer id, User user) throws CategoryException, UserException {
		logger.debug("delete()...");
		
		validate(user);
		
		Category category = findById(id, user);
		
		if(category.getTitle().equalsIgnoreCase(Category.DEFAULT)){
			throw new CategoryException("Default category can't be excluded");
		}
		
		channelBO.updateToDefaultCategory(id, user);
		categoryDAO.delete(category);
	}

	public void update(Integer id, String title, User user) throws UserException, CategoryException {
		logger.debug("update()...");
	
		validate(title, user);
		
		if(title.equalsIgnoreCase(Category.DEFAULT)){
			throw new CategoryException("Default category already exists");
		}
		
		Category category = findById(id, user);
		
		category.setTitle(title);
		
		categoryDAO.update(category);
	}

	public void createDefaultCategory(User user) throws CategoryException, UserException {
		logger.debug("createDefaultCategory()...");
		
		validate(user);
		
		categoryDAO.insert(Category.newDefault(user));
	}

	public Category findDefaultCategory(User user) throws UserException {
		logger.debug("findDefaultCategory()...");
		
		validate(user);
		
		return categoryDAO.findDefaultCategory(user);
	}

	public Category findById(Integer id, User user) throws CategoryException {
		logger.debug("findById()...");
		
		Category category = categoryDAO.findById(id, user);
		if(category == null){
			throw new CategoryException("Category not found");
		}
		
		return category;
	}
	
}
