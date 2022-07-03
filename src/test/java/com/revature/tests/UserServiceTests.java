package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.UserDaoImpl;
import com.revature.exceptions.UserIsRegisteredException;
import com.revature.models.User;
import com.revature.models.UserType;
import com.revature.service.UserService;

public class UserServiceTests {
	private UserService userv;
	private UserDaoImpl mockDao;
	private User dummyUser;
	
	@Before
	public void setup() {
		mockDao = mock(UserDaoImpl.class);
		
		userv = new UserService(mockDao);
	}
	
	@After
	public void tearDown() {
		userv = null;
		mockDao = null;
		dummyUser = null;
	}
	
	@Test
	public void testSuccessfulLogin() {
		dummyUser = new User(1, "test", "test", "test", "test", "test@mail.com", new UserType());
		
		String username = "test";
		String password = "test";
		
		List<User> emps = new LinkedList<User>();
		emps.add(dummyUser);
		
		when(mockDao.findAll()).thenReturn(emps);
		
		User actualReturnedUser = userv.confirmLogin(username, password);
		User expectedUser = dummyUser;
		
		assertEquals(expectedUser, actualReturnedUser);
		
	}
	
	@Test
	public void testFailedLogin() {
		//dummyUser = new User(1, "test", "test", "test", "test", "test@mail.com", new UserType());
		
		String username = "test";
		String password = "incorrect";
		
		List<User> emps = new LinkedList<User>();
		
		when(mockDao.findAll()).thenReturn(emps);
		
		User actualReturnedUser = userv.confirmLogin(username, password);
		User expectedUser = new User();
		
		assertEquals(expectedUser, actualReturnedUser);
	}
	
	@Test(expected=UserIsRegisteredException.class)
	public void testRegisteredUser_idGreaterThanZero_throwsException() {
		dummyUser = new User(1, "test", "test", "test", "test", "test@mail.com", new UserType());
		
		when(mockDao.findByUsername("test")).thenReturn(dummyUser);
		
		userv.register(dummyUser);
	}
	
	@Test
	public void testRegisteredUser_returnNewPkAsId() {
		dummyUser = new User(0, "test", "test", "test", "test", "test@mail.com", new UserType());
		
		Random r = new Random();
		
		int expectedId = r.nextInt(100);
		
		when(mockDao.findByUsername("test")).thenReturn(new User());
		
		when(mockDao.insert(dummyUser)).thenReturn(expectedId);
		
		int actualId = userv.register(dummyUser);
		
		assertEquals(expectedId, actualId);
	}

}
