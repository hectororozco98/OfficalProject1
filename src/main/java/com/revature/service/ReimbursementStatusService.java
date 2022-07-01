package com.revature.service;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.IReimbursementStatusDao;
import com.revature.exceptions.InsertReimbursementStatusFailedException;
import com.revature.exceptions.InsertReimbursementTypeFailedException;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementStatusEnum;
import com.revature.models.ReimbursementType;

public class ReimbursementStatusService {

	private static Logger logger = Logger.getLogger(ReimbursementStatusService.class);

	private IReimbursementStatusDao rsdao;

	public ReimbursementStatusService(IReimbursementStatusDao rsdao) {

		this.rsdao = rsdao;
	}

	public ReimbursementStatus createReimbursementStatus(ReimbursementStatus rs) {

		Optional<ReimbursementStatus> possibleStatus = rsdao.findAll().stream()
				.filter(s -> (s.getReim_status().equals(rs.getReim_status()))).findFirst();

		if (!possibleStatus.isPresent()) {

			logger.info("Successfully inserted new Reimbursement Type " + rs.getReim_status() + " into the Database");

			return rs;
		} else {
			throw new InsertReimbursementStatusFailedException(
					"Failed to insert status " + rs.getReim_status() + " it exists");
		}
	}
	
	public ReimbursementStatus findStatusByStatus(ReimbursementStatusEnum status) {
		
		Optional<ReimbursementStatus> possibleStatus = rsdao.findAll().stream()
				.filter(t -> (t.getReim_status().equals(status))).findFirst();
		
			if (possibleStatus.isPresent()) {
				return possibleStatus.get();
			}
			else {
				return new ReimbursementStatus();
			}
	}

}
