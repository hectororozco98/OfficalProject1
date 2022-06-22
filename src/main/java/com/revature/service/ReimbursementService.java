package com.revature.service;

import java.util.List;

import com.revature.dao.IReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private IReimbursementDao rdao;
	
	
	//For Review
	public ReimbursementService(IReimbursementDao rdao) {
		
		this.rdao = rdao;
	}
	
	public Reimbursement createReimbursement(double amount, String description, int authorId) {
		
		
		return null;
	}
	
	public List<Reimbursement> getAll() {
		
		return rdao.findAll();
	}

}
