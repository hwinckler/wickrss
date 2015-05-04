package br.com.wickrss.feed.model;

import java.util.Date;

import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.user.model.User;

public class Feed {
	
	public static final int MAX_DESCRIPTION = 255;
	private Integer id;
	private String title;
	private String description;
	private Date pubDate;
	private String link;
	private Boolean visualized = Boolean.FALSE;
	
	private Channel channel;
	private User user;
	
	public Feed() {
	}
	
	public Feed(User user, Channel channel, String title, String description, Date pubDate, String link, Boolean visualized) {
		super();
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		this.link = link;
		this.channel = channel;
		this.visualized = visualized;
		this.user = user;
	}
	
	public Feed(String title, String description, Date pubDate, String link, Boolean visualized) {
		super();
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		this.link = link;
		this.visualized = visualized;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getVisualized() {
		return visualized;
	}
	
	public void setVisualized(Boolean visualized) {
		this.visualized = visualized;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
