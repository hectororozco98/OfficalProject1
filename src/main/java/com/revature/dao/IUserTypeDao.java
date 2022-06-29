package com.revature.dao;

import java.util.List;

import com.revature.models.UserType;

public interface IUserTypeDao {
	
	int insert (UserType ut);
	
	List<UserType> findAll();
	
	boolean update(UserType ut);
	
	boolean delete(UserType rt);

}
