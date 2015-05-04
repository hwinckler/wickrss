package br.com.wickrss.guice;

import br.com.wickrss.category.controller.CategoryController;
import br.com.wickrss.channel.controller.ChannelController;
import br.com.wickrss.controller.AboutController;
import br.com.wickrss.controller.IndexController;
import br.com.wickrss.feed.controller.FeedController;
import br.com.wickrss.filter.UserFilter;
import br.com.wickrss.login.controller.LoginController;
import br.com.wickrss.login.controller.Oauth2Callback;
import br.com.wickrss.logout.controller.LogoutController;

import com.google.inject.servlet.ServletModule;

class MyServletModule extends ServletModule {
	
	  protected void configureServlets() {
		  filter("/*").through(UserFilter.class);
		  serve("/about").with(AboutController.class);
		  serve("/category").with(CategoryController.class);
		  serve("/feed").with(FeedController.class);
		  serve("/channel").with(ChannelController.class);
		  serve("/").with(IndexController.class);
		  serve("/index").with(IndexController.class);
		  serve("/login").with(LoginController.class);
		  serve("/logout").with(LogoutController.class);
		  serve("/oauth2callback").with(Oauth2Callback.class);
	  }
}
