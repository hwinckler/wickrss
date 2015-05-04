package br.com.wickrss.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.user.model.User;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class RSSFeedParserRome implements RSSFeed{

	private static final Logger logger = LoggerFactory.getLogger(RSSFeedParserRome.class);

	@Override
	public Channel parse(InputStream stream, User user)throws RSSFeedParserException {

		return parse(stream, user, null);
	}

	@Override
	public Channel parse(InputStream stream, User user, Date pubDate)throws RSSFeedParserException {

		Channel channel = null;
		
		try {
			
			SyndFeed feed = new SyndFeedInput().build(new XmlReader(stream));
			
			if(feed != null){
				channel = new Channel(feed.getTitle(), feed.getDescription(), (feed.getLink() != null) ? feed.getLink(): feed.getUri(), new Date());
				
				List<SyndEntry> entries = feed.getEntries();
				for (SyndEntry entry : entries) {
					if(pubDate == null || (pubDate != null && entry.getPublishedDate().after(pubDate))){
						channel.getFeeds().add(new Feed(user, channel, entry.getTitle(), (entry.getDescription() != null) ? StringUtils.abbreviate(entry.getDescription().getValue().replaceAll("\\<.*?\\>", ""), Feed.MAX_DESCRIPTION) : "", entry.getPublishedDate(), entry.getLink(), false));
					}
				}
			}
			
		} catch (IllegalArgumentException | FeedException | IOException e) {
			logger.error("error", e);

			throw new RSSFeedParserException(e);
		}
		finally{
			if(stream != null){
				try {
					stream.close();
				} catch (IOException e) {
					logger.error("stream.close()", e);
				}
			}
		}
		
		return channel;
	}
}
