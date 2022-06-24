package com.revature.service;

import java.util.List;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementTypeEnum;

public class ReimbursementService {
	
	private IReimbursementDao rdao;
	
	
	//For Review
	public ReimbursementService(IReimbursementDao rdao) {
		
		this.rdao = rdao;
	}
	
	public int createReimbursement(Reimbursement r) {
		
		// Might have to make some type of check to see if there is a reimbursement with the same 
		// information in the DB already
		
		return rdao.insert(r);
	}
	
	public List<Reimbursement> getAll() {
		
		return rdao.findAll();
	}

}
