package br.com.wickrss.reader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wickrss.exception.ChannelException;

public class ChannelStream {

	private static final Logger logger = LoggerFactory.getLogger(ChannelStream.class);
	
	public InputStream getStream(String link) throws ChannelException{
		logger.debug("getStream()...");
		
		if(link == null ||link.isEmpty()){
			throw new ChannelException("Link cannot be empty");
		}
		
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) new URL(link).openConnection();
			httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			
			if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new Exception("Failed to obtain feed of url: " + link);
			}
						
			return httpConnection.getInputStream();
			
		} catch (Exception e) {
			logger.error("parse", e);
			
			throw new ChannelException(e);
		}
	}
}
