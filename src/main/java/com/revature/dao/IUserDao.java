package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface IUserDao {

	int insert(User u);

	List<User> findAll();

	boolean delete(int id);

	boolean update(User u);

}
