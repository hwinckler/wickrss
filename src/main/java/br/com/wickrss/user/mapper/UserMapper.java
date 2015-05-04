package br.com.wickrss.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import br.com.wickrss.user.model.User;

public interface UserMapper {

	@Insert("INSERT INTO user(id, email, date) VALUES(#{id}, #{email}, #{date})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public void insert(User user);
	
	@Select("SELECT * FROM user WHERE email = #{email}")
	public User findByEmail(String email);
	
	@Select("SELECT * FROM user")
	public List<User> findAll();

	@Delete("DELETE FROM user WHERE email = #{email}")
	public void delete(String email);

}
