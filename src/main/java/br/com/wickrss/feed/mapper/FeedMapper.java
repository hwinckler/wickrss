package br.com.wickrss.feed.mapper;

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

public interface FeedMapper {

	@Insert("INSERT INTO feed(id, channel_id, title, description, pubDate, link, visualized, user_id) VALUES(#{id}, #{channel.id}, #{title}, #{description}, #{pubDate}, #{link}, #{visualized}, #{user.id})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public void insert(Feed feed);
	
	@Select("SELECT * FROM feed WHERE user_id = #{id} ORDER BY id DESC")
	public List<Feed> findAll(User user);

	@Select("SELECT * FROM feed WHERE id = #{param1} AND user_id = #{param2.id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="title", column="title"),
			@Result(property="description", column="description"),
			@Result(property="pubDate", column="pubDate"),
			@Result(property="link", column="link"),
			@Result(property="visualized", column="visualized"),
            @Result(property="channel", javaType=Channel.class, column="channel_id",
                   many=@Many(select="getChannel"))		
            })
	public Feed findById(Integer id, User user);
	
	@Select("SELECT * FROM channel WHERE id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="title", column="title"),
			@Result(property="link", column="link"),
			@Result(property="lastSynchronize", column="lastSynchronize"),
			@Result(property="synchronize", column="synchronize"),
            @Result(property="category", javaType=Category.class, column="category_id",
                   many=@Many(select="getCategory"))
            })	
	public Channel getChannel(Integer id);
	
	@Select("SELECT * FROM category WHERE id = #{id}")
	public Category getCategory(Integer id);

	@Delete("DELETE FROM feed WHERE channel_id = #{param1} AND user_id = #{param2.id}")
	public void deleteByChannel(Integer id, User user);

	@Select("SELECT * FROM feed f inner join channel c on (f.channel_id = c.id) WHERE c.category_id = #{param1} AND c.user_id = #{param2.id} ORDER BY f.pubDate DESC")
	public List<Feed> findByCategory(Integer categoryID, User user);

	@Update("UPDATE feed set visualized = true WHERE id = #{param1} AND user_id = #{param2.id}")
	public void markAsRead(Integer feedID, User user);

	@Update("UPDATE feed f INNER JOIN channel ch ON(f.channel_id = ch.id) set f.visualized = true WHERE ch.category_id = #{param1} AND ch.user_id = #{param2.id}")
	public void markAllAsRead(Integer categoryID, User user);

	@Select("SELECT * FROM feed WHERE visualized = false AND user_id = #{id}")
	public List<Feed> findUnread(User user);	
}
