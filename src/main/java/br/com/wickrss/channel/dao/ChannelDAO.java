package br.com.wickrss.channel.dao;

import java.util.List;

import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.user.model.User;

public interface ChannelDAO {

	public void insert(Channel channel);

	public List<Channel> findAll(User user);

	public Channel findById(Integer id, User user);

	public List<Channel> findByCategory(Integer categoryID, User user);

	public void delete(Integer id, User user);

	public void update(Integer id, Integer categoryID, User user);

	public void updateToDefaultCategory(Integer categoryID, Integer defaultCategoryID, User user);

	public List<Channel> findAllWithLastPubDate(User user);
}
