package br.com.wickrss.category.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.user.model.User;

public class Category {

	public final static String DEFAULT = "Default";
	
	private Integer id;
	private String title;
	private Date date;
	private User user;
	
	private Integer unread;
	
	private List<Channel> channels = new ArrayList<Channel>();
	
	public Category() {
	}
	
	public Category(Integer id) {
		this.id = id;
	}

	public Category(User user, String title) {
		super();
		this.title = title;
		this.date = new Date();
		this.user = user;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Channel> getChannels() {
		return channels;
	}
	
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	
	public static Category newDefault(User user){
		return new Category(user, Category.DEFAULT);
	}
	
	public Integer getUnread() {
		return unread;
	}
	
	public void setUnread(Integer unread) {
		this.unread = unread;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
