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
				.filter(u -> (u.getUsername().equals(username) && u.getPassword().equals(password))).findFirst();

		logger.info("Logging employee with username: " + username + "into the server");

		// IF the employee is present, return it, otherwise return empty Emp object
		// (with id of 0)
		return (possibleEmp.isPresent() ? possibleEmp.get() : new User());
		// ideally you should optimize this and set up a custom exception to be returned
	}
	
	public User findById(int id) {
		
		Optional<User> possibleEmp = udao.findAll().stream()
				.filter(u -> (u.getId() == id)).findFirst();
		
		logger.info("Searching for employee with id of " + id);
		
		return (possibleEmp.isPresent() ? possibleEmp.get() : new User());
	}

	public List<User> getAll() {

		return udao.findAll();

	}
	
	public List<User> getAllEmps() {

		return udao.findAllEmps();
	}

	public int register(User u) {

		String username = u.getUsername();

		User possibleUser = udao.findByUsername(username);

		if (possibleUser.getId() == 0) {

			logger.info("Registration successful for user: " + username);

			return udao.insert(u);
		} else {
			throw new UserIsRegisteredException(
					"Failed to register user " + username + " because that username already exists");
		}
	}

	public boolean updateUser(User u) {

		if (u.getId() == 0) {

			logger.warn("Can not update User: " + u.getUsername() + ". They do not exist");
			return false;
		} else if (u.getId() > 0) {

			logger.info("Updating user with the id of " + u.getId());

			return udao.updateUser(u);
		} else {
			logger.warn("Unable to update user: " + u.getUsername() + ". They do not exist");
			return false;
		}
	}

	public boolean deleteUser(User u) {

		if (u.getId() == 0) {

			logger.warn("Could not delete user: " + u.getUsername() + "Not in the database.");
			return false;
		}

		return udao.deleteUser(u);
	}
}