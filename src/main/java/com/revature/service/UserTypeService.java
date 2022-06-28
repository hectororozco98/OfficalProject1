package com.revature.service;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.IUserTypeDao;
import com.revature.exceptions.InsertReimbursementTypeFailedException;
import com.revature.exceptions.InsertUserTypeFailedException;
import com.revature.models.ReimbursementType;
import com.revature.models.UserType;

public class UserTypeService {

	private static Logger logger = Logger.getLogger(UserTypeService.class);

	private IUserTypeDao utdao;

	public UserTypeService(IUserTypeDao utdao) {

		this.utdao = utdao;
	}

	public UserType createUserType(UserType ut) {

		Optional<UserType> possibleType = utdao.findAll().stream()
				.filter(t -> (t.getUser_type().equals(ut.getUser_type()))).findFirst();

		try {

			if (!possibleType.isPresent()) {

				logger.info("Successfully inserted new user Type " + ut.getUser_type() + " into the Database");

				return ut;
			} else if (possibleType.isPresent()) {
				return possibleType.get();
			} else {
				throw new InsertUserTypeFailedException(
						"Failed to insert type " + ut.getUser_type() + " it exists");
			}
		} catch (InsertUserTypeFailedException e) {
			return ut;
		}

	}

}
