package br.com.wickrss.feed.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedMarkAsReadTest  extends DBUnitLoad{

	@Inject
	static FeedBO feedBO;

	@Inject
	static ChannelBO channelBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
	
	final static String dataSet = "src/test/resources/FeedMarkAsReadDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
		
		Injector injector = Guice.createInjector(new AppModule());
		
		feedBO = injector.getInstance(FeedBO.class);
		channelBO = injector.getInstance(ChannelBO.class);
		userBO = injector.getInstance(UserBO.class);
	}
	
	@Test
	public void findUnread(){
		
		user = userBO.findByEmail("user1@user.com");
		
		List<Feed> unread = feedBO.findUnread(user);
		Assert.assertEquals(3, unread.size());
	}
	
	
	@Test
	public void markAllAsRead(){
		
		user = userBO.findByEmail("user1@user.com");
		
		feedBO.markAllAsRead(1, user);
		
		List<Feed> unread = feedBO.findUnread(user);
		Assert.assertEquals(1, unread.size());
		
		
	}
	
	@Test
	public void markAsRead(){
		
		user = userBO.findByEmail("user1@user.com");
		
		feedBO.markAsRead(4, user);
		
		List<Feed> unread = feedBO.findUnread(user);
		Assert.assertEquals(0, unread.size());
		
		
	}
	
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
	
}
