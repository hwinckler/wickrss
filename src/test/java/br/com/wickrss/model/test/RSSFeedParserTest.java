package br.com.wickrss.model.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.model.Category;
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.ChannelException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.reader.RSSFeed;
import br.com.wickrss.reader.RSSFeedParserException;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;

public class RSSFeedParserTest extends DBUnitLoad {

	@Inject
	static RSSFeed rssFeed;
	
	@Inject
	static CategoryBO categoryBO;
	
	@Inject
	static ChannelBO channelBO;
	
	@Inject
	static FeedBO feedBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
	
	final static String dataSet = "/RSSFeedParserDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
		
		Injector injector = Guice.createInjector(new AppModule());
		
		rssFeed = injector.getInstance(RSSFeed.class);
		
		categoryBO = injector.getInstance(CategoryBO.class);
		channelBO = injector.getInstance(ChannelBO.class);
		feedBO = injector.getInstance(FeedBO.class);
		userBO = injector.getInstance(UserBO.class);
		
	}

	@Test
	public void parse() throws IOException, RSSFeedParserException, CategoryException, UserException, ChannelException{
		
		user = userBO.createsNotExist(new User("user1@user.com"));
		
		try(FileInputStream stream = new FileInputStream(getClass().getResource("/rssfeed-local.xml").getFile())){
			
			Channel channel = rssFeed.parse(stream, user);
			
			channelBO.insert(channel, user);
		
		}
		
	}
	
	@Test
	public void results() throws CategoryException{
		
		Category category = categoryBO.findById(1, user);
		
		Assert.assertEquals(Category.DEFAULT, category.getTitle());
		
		Channel channel = channelBO.findById(1, user);
		
		Assert.assertEquals("Teste do RSSFeedParser", channel.getTitle());
		Assert.assertEquals("http://github.com/hwinckler", channel.getLink());
		
		List<Feed> feeds = channel.getFeeds();

		Assert.assertEquals("Item 2", feeds.get(0).getTitle());
		Assert.assertEquals("http://item2.ccom", feeds.get(0).getLink());
		
		Assert.assertEquals("Item 1", feeds.get(1).getTitle());
		Assert.assertEquals("http://item1.ccom", feeds.get(1).getLink());
		
	}
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
}
