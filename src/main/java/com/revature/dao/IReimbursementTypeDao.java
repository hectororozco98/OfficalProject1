package com.revature.dao;

import java.util.List;

import com.revature.models.ReimbursementType;

public interface IReimbursementTypeDao {
	
	int insert (ReimbursementType rt);
	
	ReimbursementType findById(int i);
	
	List<ReimbursementType> findAll();
	
	boolean update(ReimbursementType rt);
	
	boolean delete(ReimbursementType rt);

}
