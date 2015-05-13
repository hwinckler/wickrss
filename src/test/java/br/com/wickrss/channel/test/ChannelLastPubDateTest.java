package br.com.wickrss.channel.test;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.ChannelException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.guice.AppModule;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.model.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

import dbunit.dataset.load.DBUnitLoad;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChannelLastPubDateTest extends DBUnitLoad{

	@Inject
	static ChannelBO channelBO;
	
	@Inject
	static CategoryBO categoryBO;
	
	@Inject
	static UserBO userBO;
	
	static User user;
		
	final static String dataSet = "src/test/resources/ChannelLastPubDateDataset.xml";
	
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
		
		List<Channel> channels = channelBO.findAllWithLastPubDate(user);
		
		Assert.assertEquals(2, channels.size());
		
		Assert.assertEquals("channel1", channels.get(0).getTitle());
		Assert.assertEquals("13/03/2015 13:00:00", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(channels.get(0).getLastPubDate()));

		Assert.assertEquals("channel2", channels.get(1).getTitle());
		Assert.assertEquals("14/03/2015 21:00:00", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(channels.get(1).getLastPubDate()));
		
	}
	
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
	
}
