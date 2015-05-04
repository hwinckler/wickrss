package br.com.wickrss.guice;

import org.apache.ibatis.session.SqlSessionFactory;

import br.com.wickrss.category.bo.CategoryBO;
import br.com.wickrss.category.bo.impl.CategoryBOImpl;
import br.com.wickrss.category.dao.CategoryDAO;
import br.com.wickrss.category.dao.impl.CategoryDAOImpl;
import br.com.wickrss.channel.bo.ChannelBO;
import br.com.wickrss.channel.bo.impl.ChannelBOImpl;
import br.com.wickrss.channel.dao.ChannelDAO;
import br.com.wickrss.channel.dao.impl.ChannelDAOImpl;
import br.com.wickrss.dao.factory.SqlSessionFactoryProvider;
import br.com.wickrss.feed.bo.FeedBO;
import br.com.wickrss.feed.bo.impl.FeedBOImpl;
import br.com.wickrss.feed.dao.FeedDAO;
import br.com.wickrss.feed.dao.impl.FeedDAOImpl;
import br.com.wickrss.reader.RSSFeed;
import br.com.wickrss.reader.RSSFeedParserRome;
import br.com.wickrss.user.bo.UserBO;
import br.com.wickrss.user.bo.impl.UserBOImpl;
import br.com.wickrss.user.dao.UserDAO;
import br.com.wickrss.user.dao.impl.UserDAOImpl;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
	
	  protected void configure() {
		  
		  bind(CategoryDAO.class).to(CategoryDAOImpl.class);
		  bind(CategoryBO.class).to(CategoryBOImpl.class);
		  
		  bind(ChannelDAO.class).to(ChannelDAOImpl.class);
		  bind(ChannelBO.class).to(ChannelBOImpl.class);
		  
		  bind(FeedDAO.class).to(FeedDAOImpl.class);
		  bind(FeedBO.class).to(FeedBOImpl.class);
		  
		  bind(RSSFeed.class).to(RSSFeedParserRome.class);
		  
		  bind(UserDAO.class).to(UserDAOImpl.class);
		  bind(UserBO.class).to(UserBOImpl.class);
		  
		  bind(SqlSessionFactory.class).toProvider(SqlSessionFactoryProvider.class);
	  }
}
