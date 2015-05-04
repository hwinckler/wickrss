package br.com.wickrss.reader;

import java.io.InputStream;
import java.util.Date;

import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.user.model.User;

public interface RSSFeed {

	public Channel parse(InputStream stream, User user) throws RSSFeedParserException;

	Channel parse(InputStream stream, User user, Date pubDate)throws RSSFeedParserException;
}
