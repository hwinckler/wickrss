package br.com.wickrss.user.bo;

import java.util.List;

import br.com.wickrss.exception.CategoryException;
import br.com.wickrss.exception.UserException;
import br.com.wickrss.user.model.User;

public interface UserBO {

	void insert(User user) throws UserException;
	
	void delete(String email);

	List<User> findAll();

	User findByEmail(String email);

	User createsNotExist(User user) throws CategoryException, UserException;

}
