package com.revature.service;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.IReimbursementTypeDao;
import com.revature.exceptions.InsertReimbursementTypeFailedException;
import com.revature.models.ReimbursementType;

public class ReimbursementTypeService {
	
	private static Logger logger = Logger.getLogger(ReimbursementTypeService.class);
	
	private IReimbursementTypeDao rtdao;
	
	public ReimbursementTypeService(IReimbursementTypeDao rtdao) {
		
		this.rtdao = rtdao;
	}
	
	public ReimbursementType createReimbursementType(ReimbursementType rt) {
		
		Optional<ReimbursementType> possibleType = rtdao.findAll().stream()
				.filter(t -> (t.getReim_type().equals(rt.getReim_type())))
				.findFirst();
								
		try {
			
			if (!possibleType.isPresent()) {
				
				logger.info("Successfully inserted new Reimbursement Type " + rt.getReim_type() + " into the Database");
				
				return rt;
			} else if (possibleType.isPresent() ){
				return possibleType.get();
			} else {
				throw new InsertReimbursementTypeFailedException("Failed to insert type " + rt.getReim_type() + " it exists");
			}
		} catch (InsertReimbursementTypeFailedException e) {
			return rt;
		}
	}
}
