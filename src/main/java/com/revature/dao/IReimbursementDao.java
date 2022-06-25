package com.revature.dao;

import java.util.List;
import java.util.Optional;

import com.revature.models.Reimbursement;

public interface IReimbursementDao {
	
	int insert(Reimbursement r);
	
	Reimbursement findById(int i);
	
	List<Reimbursement> findByAuthorId(int id);
	
	List<Reimbursement> findAll();
	
	boolean update(Reimbursement e);
	
	boolean delete(Reimbursement e);
}
