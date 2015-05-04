package br.com.wickrss.category.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.exception.CategoryAlreadExistsException;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.InvalidCategoryException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;

public class CategoryTest extends DBUnitLoad {

	@Inject
	static CategoryBO categoryBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
	
	final static String dataSet = "/CategoryDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
		
		Injector injector = Guice.createInjector(new AppModule());
		categoryBO = injector.getInstance(CategoryBO.class);
		userBO = injector.getInstance(UserBO.class);
	}
	

	@Test
	public void addCategory() throws CategoryException, UserException{

		user = userBO.createsNotExist(new User("user1@user.com"));
		
		categoryBO.insert("Outros", user);
	}
	
	@Test(expected = CategoryAlreadExistsException.class)
	public void addCategoryDuplicate() throws CategoryException, UserException{

		categoryBO.insert("Outros", user);
	}
	
	@Test(expected = InvalidCategoryException.class)
	public void addCategoryEmpty() throws CategoryException, UserException{

		categoryBO.insert("", user);
	}
	
	@Test(expected = InvalidCategoryException.class)
	public void addCategoryNull() throws CategoryException, UserException{

		categoryBO.insert(null, user);
	}
	
	@Test(expected = UserException.class)
	public void addCategoryUserNull() throws CategoryException, UserException{

		categoryBO.insert("Temp", null);
	}
	
	@Test(expected = UserException.class)
	public void addCategoryUserEmailEmpty() throws CategoryException, UserException{

		categoryBO.insert("Temp", new User(""));
	}
	
	@Test(expected = UserException.class)
	public void addCategoryUserEmailNull() throws CategoryException, UserException{

		categoryBO.insert("Temp", new User(null));
	}
	
	@Test
	public void findAll(){
		
		List<Category> categories = categoryBO.findAll(user);
		
		Assert.assertEquals(2, categories.size());
	}
	
	@Test
	public void findById() throws InvalidCategoryException, UserException{
		
		Category category = categoryBO.find("Outros", user);
		
		Assert.assertEquals("Outros", category.getTitle());
		Assert.assertEquals(2, category.getId().intValue());
		
		Assert.assertEquals(0, category.getChannels().size());
	}
	
	@Test(expected = CategoryException.class)
	public void findByIdInvalid() throws UserException, CategoryException{
		
		categoryBO.findById(99, user);

	}
	
	@Test
	public void defaultCategory() throws UserException{
		
		Category category = categoryBO.findDefaultCategory(user);
		
		Assert.assertEquals(Category.DEFAULT, category.getTitle());
		Assert.assertEquals(1, category.getId().intValue());
	}	

	@Test
	public void updateCategory() throws InvalidCategoryException, UserException, CategoryException{
		
		categoryBO.update(2, "Outros updated", user);
		
		Category categoryUpdated = categoryBO.findById(2, user);
		
		Assert.assertEquals("Outros updated", categoryUpdated.getTitle());
		Assert.assertEquals(2, categoryUpdated.getId().intValue());		
		
	}
	
	@Test(expected = CategoryException.class)
	public void updateCategoryDefaultExists() throws InvalidCategoryException, UserException, CategoryException{
		
		categoryBO.update(2, "Default", user);
		
	}
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
	
}
