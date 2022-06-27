package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.IUserDao;
import com.revature.exceptions.UserIsRegisteredException;
import com.revature.models.User;

public class UserService {
	
	private static Logger logger = Logger.getLogger(UserService.class);
	
	private IUserDao udao;
	
	
	public UserService(IUserDao udao) {
		
		this.udao = udao;
		
	}
	
	
	public User confirmLogin(String username, String password) {
		
		// let's stream through all the employees that are returned
		Optional<User> possibleEmp = udao.findAll().stream()
				.filter(u -> (u.getUsername().equals(username) && u.getPassword().equals(password)))
				.findFirst();
		
		// IF the employee is present, return it, otherwise return empty Emp object (with id of 0)
		return (possibleEmp.isPresent() ? possibleEmp.get() : new User());
		// ideally you should optimize this and set up a custom exception to be returned
	}
	
	public List<User> getAll() {
		
		return udao.findAll();
		
	}
	
	public int register(User u) {
		
		String username = u.getUsername();
		
		User possibleUser = udao.findByUsername(username);
		
		try {
			
			if (possibleUser.getId() == 0) {
				
				logger.info("Registration successful for user: " + username);
				
				return udao.insert(u);	
			} else {
				throw new UserIsRegisteredException("Failed to register user " + username + "because that username already exists");
			}
		}  catch (UserIsRegisteredException e) {
			return 0;	
		}
	}

}