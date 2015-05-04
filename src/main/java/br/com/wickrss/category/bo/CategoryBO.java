package br.com.wickrss.category.bo;

import java.util.List;

import br.com.wickrss.category.model.Category;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.InvalidCategoryException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.user.model.User;

public interface CategoryBO {

	public void insert(String title, User user) throws CategoryException, UserException;

	public List<Category> findAll(User user);

	public Category find(String title, User user) throws InvalidCategoryException, UserException;

	public void delete(Integer id, User user) throws CategoryException, UserException;

	public void update(Integer id, String title, User user) throws InvalidCategoryException, UserException, CategoryException;

	List<Category> findAllWithUnRead(User user);

	public void createDefaultCategory(User user) throws CategoryException, UserException;

	public Category findDefaultCategory(User user) throws UserException;

	public Category findById(Integer id, User user) throws CategoryException;
}
