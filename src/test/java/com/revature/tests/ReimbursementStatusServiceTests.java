package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.ReimbursementStatusDaoImpl;
import com.revature.exceptions.InsertReimbursementStatusFailedException;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementStatusEnum;
import com.revature.models.ReimbursementType;
import com.revature.models.ReimbursementTypeEnum;
import com.revature.service.ReimbursementStatusService;

public class ReimbursementStatusServiceTests {

	private ReimbursementStatusService rsserv;

	private ReimbursementStatusDaoImpl mockDao;

	private ReimbursementStatus dummyStatus;

	@Before
	public void setup() {

		mockDao = mock(ReimbursementStatusDaoImpl.class);

		rsserv = new ReimbursementStatusService(mockDao);

	}

	@After
	public void teardown() {

		rsserv = null;
		mockDao = null;
		dummyStatus = null;
	}

	@Test
	public void testCreateReimbursementStatus_Success() {

		dummyStatus = new ReimbursementStatus(ReimbursementStatusEnum.PENDING, new ArrayList<Reimbursement>());

		List<ReimbursementStatus> statusList = new ArrayList<ReimbursementStatus>();

		when(mockDao.findAll()).thenReturn(statusList);

		ReimbursementStatus returnedStatus = rsserv.createReimbursementStatus(dummyStatus);

		assertEquals(returnedStatus, dummyStatus);
	}

	@Test(expected = InsertReimbursementStatusFailedException.class)
	public void testCreateReimbursementType_Failed_ThrowsException() {

		dummyStatus = new ReimbursementStatus(ReimbursementStatusEnum.PENDING, new ArrayList<Reimbursement>());
		ReimbursementStatus dummyStatus2 = new ReimbursementStatus(ReimbursementStatusEnum.PENDING,
				new ArrayList<Reimbursement>());

		List<ReimbursementStatus> statusList = new ArrayList<ReimbursementStatus>();

		statusList.add(dummyStatus2);

		when(mockDao.findAll()).thenReturn(statusList);

		ReimbursementStatus returnedStatus = rsserv.createReimbursementStatus(dummyStatus);
	}

	@Test
	public void testFindReimbursementStatusByStatus_Success() {

		dummyStatus = new ReimbursementStatus(0, ReimbursementStatusEnum.PENDING, new ArrayList<Reimbursement>());
		ReimbursementStatus dummyStatus2 = new ReimbursementStatus(3, ReimbursementStatusEnum.PENDING,
				new ArrayList<Reimbursement>());

		List<ReimbursementStatus> statusList = new ArrayList<ReimbursementStatus>();

		statusList.add(dummyStatus2);

		when(mockDao.findAll()).thenReturn(statusList);

		ReimbursementStatus returnedStatus = rsserv.findStatusByStatus(dummyStatus.getReim_status());

		assertEquals(returnedStatus.getReim_status(), dummyStatus.getReim_status());
	}
	
	@Test
	public void testFindReimbursementTypeByType_Failed() {
		
		dummyStatus = new ReimbursementStatus(0, ReimbursementStatusEnum.APPROVED, new ArrayList<Reimbursement>());
		ReimbursementStatus dummyStatus2 = new ReimbursementStatus(3, ReimbursementStatusEnum.PENDING,
				new ArrayList<Reimbursement>());

		List<ReimbursementStatus> statusList = new ArrayList<ReimbursementStatus>();

		statusList.add(dummyStatus2);

		when(mockDao.findAll()).thenReturn(statusList);

		ReimbursementStatus returnedStatus = rsserv.findStatusByStatus(dummyStatus.getReim_status());

		assertEquals(returnedStatus, new ReimbursementStatus());
	
	}

}
