package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.ReimbursementTypeDaoImpl;
import com.revature.exceptions.InsertReimbursementTypeFailedException;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.ReimbursementTypeEnum;
import com.revature.service.ReimbursementTypeService;

public class ReimbursementTypeServiceTests {
	
	private ReimbursementTypeService rtserv;
	
	private ReimbursementTypeDaoImpl mockDao;
	
	private ReimbursementType dummyType;
	
	
	@Before
	public void setup() {
		
		mockDao = mock(ReimbursementTypeDaoImpl.class);
		
		rtserv = new ReimbursementTypeService(mockDao);
		
		
	}
	
	@After 
	public void teardown() {
		
		rtserv = null;
		mockDao = null;
		dummyType = null;
	}
	
	@Test
	public void testCreateReimbursementType_Success() {
		
		dummyType = new ReimbursementType(3, ReimbursementTypeEnum.LODGING, new ArrayList<Reimbursement>());
		
		List<ReimbursementType> types = new ArrayList<ReimbursementType>();
		
		when(mockDao.findAll()).thenReturn(types);
		
		ReimbursementType returnedType = rtserv.createReimbursementType(dummyType);
		
		assertEquals(returnedType, dummyType);
	}
	
	@Test(expected=InsertReimbursementTypeFailedException.class)
	public void testCreateReimbursementType_Failed_ThrowsException() {
		
		dummyType = new ReimbursementType(0, ReimbursementTypeEnum.LODGING, new ArrayList<Reimbursement>());
		ReimbursementType dummyType2 = new ReimbursementType(3, ReimbursementTypeEnum.LODGING, new ArrayList<Reimbursement>());
		
		
		List<ReimbursementType> types = new ArrayList<ReimbursementType>();
		
		types.add(dummyType2);
		
		when(mockDao.findAll()).thenReturn(types);
		
		ReimbursementType returnedType = rtserv.createReimbursementType(dummyType);
	}
	
	@Test
	public void testFindReimbursementTypeByType_Success() {
		
		dummyType = new ReimbursementType(0, ReimbursementTypeEnum.LODGING, new ArrayList<Reimbursement>());
		ReimbursementType dummyType2 = new ReimbursementType(3, ReimbursementTypeEnum.LODGING, new ArrayList<Reimbursement>());
		
		
		List<ReimbursementType> types = new ArrayList<ReimbursementType>();
		
		types.add(dummyType2);
		
		when(mockDao.findAll()).thenReturn(types);
		
		ReimbursementType returnedType = rtserv.findReimbursementTypeByType(dummyType.getReim_type());
		
		assertEquals(returnedType.getReim_type(), dummyType.getReim_type());
	}
	
	@Test
	public void testFindReimbursementTypeByType_Failed() {
		
		dummyType = new ReimbursementType(0, ReimbursementTypeEnum.FOOD, new ArrayList<Reimbursement>());
		ReimbursementType dummyType2 = new ReimbursementType(3, ReimbursementTypeEnum.LODGING, new ArrayList<Reimbursement>());
		
		
		List<ReimbursementType> types = new ArrayList<ReimbursementType>();
		
		types.add(dummyType2);
		
		when(mockDao.findAll()).thenReturn(types);
		
		ReimbursementType returnedType = rtserv.findReimbursementTypeByType(dummyType.getReim_type());
		
		assertEquals(returnedType, new ReimbursementType());
	}
	
	

}
