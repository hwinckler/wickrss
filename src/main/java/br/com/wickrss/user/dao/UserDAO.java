package br.com.wickrss.user.dao;

import java.util.List;

import br.com.wickrss.user.model.User;

public interface UserDAO {

	void insert(User user);
	
	void delete(String email);

	User findByEmail(String email);

	List<User> findAll();

}
