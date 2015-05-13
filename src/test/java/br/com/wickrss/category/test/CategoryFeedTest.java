package br.com.wickrss.category.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryFeedTest extends DBUnitLoad {

	@Inject
	static CategoryBO categoryBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
	
	final static String dataSet = "src/test/resources/CategoryFeedDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
		
		Injector injector = Guice.createInjector(new AppModule());
		categoryBO = injector.getInstance(CategoryBO.class);
		userBO = injector.getInstance(UserBO.class);
	}
	

	
	@Test
	public void findAll(){
		
		user = userBO.findByEmail("user1@user.com");
		
		List<Category> categories = categoryBO.findAll(user);
		
		Assert.assertEquals(2, categories.size());
	}
	
	@Test
	public void findUnread(){
		
		user = userBO.findByEmail("user1@user.com");
		
		List<Category> categories = categoryBO.findAllWithUnRead(user);
		
		Assert.assertEquals(2, categories.size());
		Assert.assertEquals(2, categories.get(0).getUnread().intValue());
		Assert.assertEquals(1, categories.get(1).getUnread().intValue());
	}
	
	@Test
	public void findById() throws UserException, CategoryException{
		
		Category defaultCategory = categoryBO.findById(1, user);

		Assert.assertEquals(1, defaultCategory.getChannels().size());
		
		Category outros = categoryBO.find("Outros", user);

		Assert.assertEquals(1, outros.getChannels().size());
	}
	
	@Test
	public void testDelete() throws UserException, CategoryException{
		
		categoryBO.delete(2, user);

		Category defaultCategory = categoryBO.findById(1, user);

		Assert.assertEquals(2, defaultCategory.getChannels().size());
		
		Category outros = categoryBO.find("Outros", user);
		
		Assert.assertNull(outros);

	}
	
	@Test(expected = CategoryException.class)
	public void testDeleteDefault() throws UserException, CategoryException{
		
		categoryBO.delete(1, user);
	}
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
	
}
