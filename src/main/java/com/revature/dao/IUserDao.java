package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface IUserDao {

	int insert(User u);
	
	User findByUsername(String username);

	List<User> findAll();

	boolean deleteUser(User u);

	boolean updateUser(User u);

}
