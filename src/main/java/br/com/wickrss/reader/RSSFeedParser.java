package br.com.wickrss.reader;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.user.model.User;

public class RSSFeedParser implements RSSFeed{

	private static final Logger logger = LoggerFactory.getLogger(RSSFeedParser.class);

	private XMLInputFactory inputFactory = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);

	@Override
	public Channel parse(InputStream stream, User user)throws RSSFeedParserException {

		Channel channel = null;
		XMLEventReader eventReader = null;
		boolean isFeedHeader = true;

		try{

			inputFactory = XMLInputFactory.newInstance();
			eventReader = inputFactory.createXMLEventReader(stream);

			String title = "";
			String link = "";
			String description = "";
			Calendar pubDate = null;

			while (eventReader.hasNext()) {
				
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();

					switch (localPart.toLowerCase()) {
					case "item":
						if(isFeedHeader){
							channel = new Channel(title, description, link, new Date());
							isFeedHeader = false;
						}
						event = eventReader.nextEvent();
						break;
					case "title":
						title = getCharacterData(eventReader.nextEvent());
						break;
					case "description":
						description = getCharacterData(eventReader.nextEvent());
						break;						
					case "link":
						link = getCharacterData(eventReader.nextEvent());
						break;
					case "pubdate":
						pubDate = Calendar.getInstance();
						pubDate.setTime(sdf.parse(getCharacterData(eventReader.nextEvent())));
						break;			            
					}

				}
				else if (event.isEndElement()) {
					
					if (event.asEndElement().getName().getLocalPart().equalsIgnoreCase("item")) {

						Feed feed = new Feed(user, channel, title, description, pubDate.getTime(), link, Boolean.FALSE);
						channel.getFeeds().add(feed);

						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		}
		catch(Exception e){
			logger.error("error", e);

			throw new RSSFeedParserException(e);
		}
		finally{
			if(eventReader != null){
				try {
					eventReader.close();
				} catch (XMLStreamException e) {
					logger.error("error", e);
				}
			}
		}

		return channel;
	}

	  private String getCharacterData(XMLEvent nextEvent)throws XMLStreamException {
		return (nextEvent instanceof Characters) ? nextEvent.asCharacters().getData() : ""; 
	  }

	@Override
	public Channel parse(InputStream stream, User user, Date pubDate) throws RSSFeedParserException {
		
		return parse(stream, user);
	}
}
