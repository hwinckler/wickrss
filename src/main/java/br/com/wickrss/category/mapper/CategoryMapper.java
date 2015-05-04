package br.com.wickrss.category.mapper;

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
import br.com.wickrss.user.model.User;

public interface CategoryMapper {

	@Insert("INSERT INTO category(id, user_id, title, date) VALUES(#{id}, #{user.id}, #{title}, #{date})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public void insert(Category category);
	
	@Select("SELECT * FROM category WHERE user_id = #{id} ORDER BY id DESC")
	public List<Category> findAll(User user);
	
	@Select("SELECT c.id, c.title, (SELECT count(*) FROM feed f INNER JOIN channel ch ON(f.channel_id = ch.id) WHERE f.visualized = 0 AND ch.category_id = c.id) as unread FROM category c WHERE c.user_id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="title", column="title"),
			@Result(property="unread", column="unread")
            })	
	public List<Category> findAllWithUnRead(User user);

	@Select("SELECT * FROM category WHERE title = #{title} and user_id = #{user.id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="title", column="title"),
			@Result(property="date", column="date"),
            @Result(property="user", javaType=User.class, column="user_id",
            		many=@Many(select="getUser")),			
            @Result(property="channels", javaType=List.class, column="id",
                   many=@Many(select="getChannels"))
            })
	public Category find(Category category);

	@Select("SELECT * FROM category WHERE id = #{param1} and user_id = #{param2.id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="title", column="title"),
			@Result(property="date", column="date"),
            @Result(property="user", javaType=User.class, column="user_id",
    				many=@Many(select="getUser")),
            @Result(property="channels", javaType=List.class, column="id",
                   many=@Many(select="getChannels"))
            })
	public Category findById(Integer id, User user);
	
	@Select("SELECT * FROM channel WHERE category_id = #{id}")
	public List<Channel> getChannels(Integer id);
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	public User getUser(Integer id);

	@Delete("DELETE FROM category WHERE id = #{id} and user_id = #{user.id}")
	public void delete(Category category);

	@Update("UPDATE category set title = #{title} WHERE id = #{id} and user_id = #{user.id}")
	public void update(Category category);

	@Select("SELECT * FROM category where title = '" + Category.DEFAULT + "' and user_id = #{id}")
	public Category findDefaultCategory(User user);
}
