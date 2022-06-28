package com.revature.dao;

import java.util.List;

import com.revature.models.ReimbursementStatus;

public interface IReimbursementStatusDao {
	
	int insert (ReimbursementStatus rs);
	
	List<ReimbursementStatus> findAll();
	
	boolean update(ReimbursementStatus rs);
	
	boolean delete(ReimbursementStatus rs);
	
	

}
