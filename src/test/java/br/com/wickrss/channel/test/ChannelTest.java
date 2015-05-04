package br.com.wickrss.channel.test;

import java.util.Date;
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
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.ChannelException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChannelTest extends DBUnitLoad{

	@Inject
	static ChannelBO channelBO;
	
	@Inject
	static CategoryBO categoryBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
		
	final static String dataSet = "/ChannelDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
		
		Injector injector = Guice.createInjector(new AppModule());
		
		channelBO = injector.getInstance(ChannelBO.class);
		categoryBO = injector.getInstance(CategoryBO.class);
		userBO = injector.getInstance(UserBO.class);
	}
	
	@Test
	public void addChannel() throws ChannelException, CategoryException, UserException{
		
		user = userBO.findByEmail("user1@user.com");
		
		Channel channel1 = new Channel("channel1", "channel1 description", "http://channel1.com", new Date());
		Channel channel2 = new Channel("channel2", "channel2 description", "http://channel2.com", new Date());
		
		channel2.getFeeds().add(new Feed(user, channel2, "feed1", "feed1 description", new Date(), "http://feed1.com", false));
		channel2.getFeeds().add(new Feed(user, channel2, "feed2", "feed2 description", new Date(), "http://feed2.com", false));
		
		channelBO.insert(channel1, user);
		channelBO.insert(channel2, user);
		
		Category category2 = categoryBO.find("Outros", user);
		
		Channel channel3 = new Channel(category2, "channel3", "channel3 description", "http://channel3.com", new Date());
		
		channelBO.insert(channel3, user);
		
		Category category3 = categoryBO.find("Novo", user);

		Channel channel4 = new Channel(category3, "channel4", "channel4 description", "http://channel4.com", new Date());
		
		channelBO.insert(channel4, user);

	}
	
	@Test
	public void findAll(){
		
		List<Channel> channels = channelBO.findAll(user);
		
		Assert.assertEquals(4, channels.size());
	}
	
	@Test
	public void findById(){
		
		Channel channel = channelBO.findById(2, user);
		
		Assert.assertEquals("channel2", channel.getTitle());
		
		Assert.assertEquals(Category.DEFAULT, channel.getCategory().getTitle());
		
		Assert.assertEquals(2, channel.getFeeds().size());
	}
	
	@Test
	public void findDelete() throws CategoryException{
		
		channelBO.delete(4, user);
		
		List<Channel> channels = channelBO.findAll(user);
		
		Assert.assertEquals(3, channels.size());
		

	}
	
	@Test
	public void findByCategory() throws CategoryException{
		
		List<Channel> channels = channelBO.findByCategory(1, user);
		
		Assert.assertEquals(2, channels.size());

	}
	
	@Test
	public void updateDefault() throws CategoryException, UserException{
		
		channelBO.updateToDefaultCategory(2, user);
		
		List<Channel> channels = channelBO.findByCategory(1, user);
		
		Assert.assertEquals(3, channels.size());
		

	}
	
	@Test
	public void updateToCategory() throws CategoryException, UserException, ChannelException{
		
		
		Channel channel5 = new Channel("channel5", "channel5 description", "http://channel5.com", new Date());
		
		channelBO.insert(channel5, user);
		
		channelBO.update(5, 3, user);
		
		List<Channel> channels = channelBO.findByCategory(3, user);
		
		Assert.assertEquals(1, channels.size());
		
		Assert.assertEquals("channel5", channels.get(0).getTitle());
		

	}
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
	
}
