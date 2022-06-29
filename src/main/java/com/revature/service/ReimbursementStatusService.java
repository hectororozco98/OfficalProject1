package com.revature.service;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.IReimbursementStatusDao;
import com.revature.exceptions.InsertReimbursementStatusFailedException;
import com.revature.exceptions.InsertReimbursementTypeFailedException;
import com.revature.models.ReimbursementStatus;

public class ReimbursementStatusService {

	private static Logger logger = Logger.getLogger(ReimbursementStatusService.class);

	private IReimbursementStatusDao rsdao;

	public ReimbursementStatusService(IReimbursementStatusDao rsdao) {

		this.rsdao = rsdao;
	}

	public ReimbursementStatus createReimbursementStatus(ReimbursementStatus rs) {

		Optional<ReimbursementStatus> possibleStatus = rsdao.findAll().stream()
				.filter(s -> (s.getReim_status().equals(rs.getReim_status()))).findFirst();

		try {

			if (!possibleStatus.isPresent()) {

				logger.info("Successfully inserted new Reimbursement Type " + rs.getReim_status() + " into the Database");

				return rs;
			} else if (possibleStatus.isPresent()) {
				return possibleStatus.get();
			} else {
				throw new InsertReimbursementTypeFailedException(
						"Failed to insert status " + rs.getReim_status() + " it exists");
			}
		} catch (InsertReimbursementStatusFailedException e) {
			return rs;
		}
	}

}
