package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.UserTypeDaoImpl;
import com.revature.exceptions.InsertUserTypeFailedException;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementStatusEnum;
import com.revature.models.User;
import com.revature.models.UserType;
import com.revature.models.UserTypeEnum;
import com.revature.service.UserTypeService;

public class UserTypeServiceTests {
	
	private UserTypeService utserv;
	
	private UserTypeDaoImpl mockDao;
	
	private UserType dummyType;
	
	@Before
	public void setup() {

		mockDao = mock(UserTypeDaoImpl.class);

		utserv = new UserTypeService(mockDao);

	}

	@After
	public void teardown() {

		utserv = null;
		mockDao = null;
		dummyType = null;
	}
	
	@Test
	public void testCreateUserTypesSuccess() {
		
		dummyType = new UserType(UserTypeEnum.EMPLOYEE, new ArrayList<User>());

		List<UserType> typeList = new ArrayList<UserType>();

		when(mockDao.findAll()).thenReturn(typeList);

		UserType returnedType = utserv.createUserType(dummyType);

		assertEquals(returnedType, dummyType);
	}
	
	@Test(expected = InsertUserTypeFailedException.class)
	public void testCreateUserType_Failed_ThrowsException() {
		
		dummyType = new UserType(UserTypeEnum.EMPLOYEE, new ArrayList<User>());
		UserType dummyType2 = new UserType(UserTypeEnum.EMPLOYEE, new ArrayList<User>());
		
		List<UserType> typeList = new ArrayList<UserType>();
		
		typeList.add(dummyType2);

		when(mockDao.findAll()).thenReturn(typeList);

		UserType returnedType = utserv.createUserType(dummyType);		
	}
	
	@Test
	public void testFindType_Success() {
		
		dummyType = new UserType(UserTypeEnum.EMPLOYEE, new ArrayList<User>());
		UserType dummyType2 = new UserType(UserTypeEnum.EMPLOYEE, new ArrayList<User>());


		List<UserType> typeList = new ArrayList<UserType>();
		
		typeList.add(dummyType2);

		when(mockDao.findAll()).thenReturn(typeList);

		UserType returnedType = utserv.findType(dummyType.getUser_type());

		assertEquals(returnedType.getUser_type(), dummyType.getUser_type());
	}
	
	@Test
	public void testFindType_Failed() {
		
		dummyType = new UserType(UserTypeEnum.EMPLOYEE, new ArrayList<User>());
		UserType dummyType2 = new UserType(UserTypeEnum.MANAGER, new ArrayList<User>());


		List<UserType> typeList = new ArrayList<UserType>();
		
		typeList.add(dummyType2);

		when(mockDao.findAll()).thenReturn(typeList);

		UserType returnedType = utserv.findType(dummyType.getUser_type());

		assertEquals(returnedType, new UserType());
	}
	
	

}
