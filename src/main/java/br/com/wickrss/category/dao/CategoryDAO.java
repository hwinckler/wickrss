package br.com.wickrss.category.dao;

import java.util.List;

import br.com.wickrss.category.model.Category;
import br.com.wickrss.user.model.User;

public interface CategoryDAO {

		public void insert(Category category);

		public List<Category> findAll(User user);

		public Category find(Category category);

		public void delete(Category category);

		public void update(Category category);

		public Category findDefaultCategory(User user);

		List<Category> findAllWithUnRead(User user);

		public Category findById(Integer id, User user);
}
