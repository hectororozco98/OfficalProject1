package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementTypeEnum;

public interface IReimbursementDao {
	
	int insert(Reimbursement r);
	
	Reimbursement findById(int i);
	
	List<Reimbursement> findByAuthorId(int id);
	
	List<Reimbursement> findByType(ReimbursementTypeEnum type);
	
	List<Reimbursement> findByStatus(ReimbursementStatus status);
	
	List<Reimbursement> findAll();
	
	boolean update(Reimbursement e);
	
	boolean delete(Reimbursement e);
}
