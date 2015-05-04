package br.com.wickrss.feed.test;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;

public class FeedTest  extends DBUnitLoad{

	@Inject
	static FeedBO feedBO;

	@Inject
	static ChannelBO channelBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
	
	final static String dataSet = "/FeedDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
		
		Injector injector = Guice.createInjector(new AppModule());
		
		feedBO = injector.getInstance(FeedBO.class);
		channelBO = injector.getInstance(ChannelBO.class);
		userBO = injector.getInstance(UserBO.class);
	}
	
	@Test
	public void addFeed(){
		
		user = userBO.findByEmail("user1@user.com");
		
		Channel channel1 = channelBO.findById(1, user);
		
		Feed feed1 = new Feed(user, channel1, "Nova versão do JUnit", "", new Date(), "http://feed1.com", false);
		Feed feed2 = new Feed(user, channel1, "CDI compatível com MyBatis", "", new Date(), "http://feed2.com", false);
		
		Channel channel2 = channelBO.findById(2, user);
		
		Feed feed3 = new Feed(user, channel2, "Tutorial de BootStrap com MyBatis", "", new Date(), "http://feed3.com", false);
		
		Channel channel3 = channelBO.findById(3, user);
		
		Feed feed4 = new Feed(user, channel3, "Entrevista com Kent Beck sobre Junit e TDD", "", new Date(), "http://feed4.com", false);

		feedBO.insert(feed1);
		feedBO.insert(feed2);
		feedBO.insert(feed3);
		feedBO.insert(feed4);
	}
	
	@Test
	public void findAll(){
		
		List<Feed> feeds = feedBO.findAll(user);
		
		Assert.assertEquals(4, feeds.size());
	}
	
	@Test
	public void findById(){

		Feed feed = feedBO.findById(4, user);
		
		Assert.assertEquals("Entrevista com Kent Beck sobre Junit e TDD", feed.getTitle());
	}
	
	@Test
	public void findByIdWithChannel(){

		Feed feed = feedBO.findById(4, user);
		
		Assert.assertEquals("Entrevista com Kent Beck sobre Junit e TDD", feed.getTitle());
		
		Assert.assertEquals("Agile News", feed.getChannel().getTitle());
		
		Assert.assertEquals("Agile e TDD", feed.getChannel().getCategory().getTitle());
		
	}
	
	@Test
	public void findByCategory(){

		List<Feed> feed = feedBO.findByCategory(3, user);
		
		Assert.assertEquals(1, feed.size());
		
	}
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
	
}
