package br.com.wickrss.channel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import br.com.wickrss.category.model.Category;
import br.com.wickrss.channel.model.Channel;
import br.com.wickrss.feed.model.Feed;
import br.com.wickrss.user.model.User;

public interface ChannelMapper {

	@Insert("INSERT INTO channel(id, title, description, link, lastSynchronize, synchronize, category_id, user_id) VALUES(#{id}, #{title}, #{description}, #{link}, #{lastSynchronize}, #{synchronize}, #{category.id}, #{user.id})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public void insert(Channel channel);
	
	@Select("SELECT * FROM channel WHERE user_id = #{id} ORDER BY id DESC")
	public List<Channel> findAll(User user);

	@Select("SELECT * FROM channel WHERE id = #{param1} and user_id = #{param2.id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="title", column="title"),
			@Result(property="description", column="description"),
			@Result(property="link", column="link"),
			@Result(property="lastSynchronize", column="lastSynchronize"),
			@Result(property="synchronize", column="synchronize"),
            @Result(property="category", javaType=Category.class, column="category_id",
                   many=@Many(select="getCategory")),
            @Result(property="feeds", javaType=List.class, column="id",
            		many=@Many(select="getFeeds"))
            })	
	public Channel findById(Integer id, User user);
	
	@Select("SELECT * FROM category WHERE id = #{id}")
	public Category getCategory(Integer id);
	
	@Select("SELECT * FROM feed WHERE channel_id = #{id}")
	public List<Feed> getFeeds(Integer id);

	@Update("UPDATE channel SET category_id = #{param2} WHERE id = #{param1} AND user_id = #{param3.id}")
	public void update(Integer id, Integer categoryID, User user);
	
	@Update("UPDATE channel SET category_id = #{param2} WHERE category_id = #{param1} AND user_id = #{param3.id}")
	public void updateToDefaultCategory(Integer categoryID, Integer defaultCategoryID, User user);

	@Select("SELECT * FROM channel WHERE category_id = #{param1} AND user_id =#{param2.id}")
	public List<Channel> findByCategory(Integer categoryID, User user);

	@Delete("DELETE FROM channel WHERE id = #{param1} AND user_id = #{param2.id}")
	public void delete(Integer id, User user);

	@Select("SELECT ch.id, ch.title, ch.description, ch.link, (SELECT max(f.pubDate) FROM feed f WHERE f.channel_id = ch.id) as lastPubDate FROM channel ch WHERE ch.user_id = #{id}")
	public List<Channel> findAllWithLastPubDate(User user);

}
