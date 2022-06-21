package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDao {
	
	int insert(Reimbursement r);
	
	Reimbursement findById(int i);
	
	Reimbursement findByAuthorId(int id);
	
	List<Reimbursement> findAll();
	
	boolean update(Reimbursement e);
	
	boolean delete(Reimbursement e);
}
