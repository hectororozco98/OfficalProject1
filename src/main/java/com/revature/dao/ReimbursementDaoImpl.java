package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementTypeEnum;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements IReimbursementDao {
	
	private static Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

	@Override
	public int insert(Reimbursement r) {

		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		int pk = (int) ses.save(r);

		tx.commit();

		return pk;
	}

	@Override
	public Reimbursement findById(int id) {

		Session ses = HibernateUtil.getSession();

		Optional<Reimbursement> returnedReim = ses
				.createQuery("from reimbursements WHERE id = " + id, Reimbursement.class).stream().findFirst();

		return null;
	}

	@Override
	public List<Reimbursement> findByAuthorId(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> returnedReim = ses.createQuery("from Reimbursement WHERE id = " + id, Reimbursement.class).list();
		
		return returnedReim;
	}
	
	@Override
	public List<Reimbursement> findByType(ReimbursementTypeEnum type) {
		
		List<Reimbursement> possibleReims = findAll().stream()
				.filter(r -> (r.getTypeId().equals(type))).toList();
		
		logger.info("Gathering reimbursements of type: " + type.toString());
		
		return possibleReims;
	}
	
	@Override
	public List<Reimbursement> findByStatus(ReimbursementStatus status) {
		
		List<Reimbursement> possibleReims = findAll().stream()
				.filter(r -> (r.getStatusId().equals(status))).toList();
		
		logger.info("Gathering reimbursements of status: " + status.toString());
		
		return possibleReims;
	}

	@Override
	public List<Reimbursement> findAll() {

		Session ses = HibernateUtil.getSession();

		List<Reimbursement> emps = ses.createQuery("from Reimbursement", Reimbursement.class).list();
		return emps;
	}

	@Override
	public boolean update(Reimbursement r) {
		// TODO Auto-generated method stub

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		if (r != null) {

			try {

				tx = ses.beginTransaction();

				ses.update(r);

				tx.commit();

				return true;
			} catch (TransientObjectException e) {
				if (tx != null)
					tx.rollback();
				return false;
			}
		}

		else {
			return false;
		}

	}

	@Override
	public boolean delete(Reimbursement r) {
		// TODO Auto-generated method stub

		Session ses = HibernateUtil.getSession();

		if (r != null) {

			Reimbursement temp = ses.get(Reimbursement.class, r.getId());

			if (temp != null) {

				Transaction tx = ses.beginTransaction();

				ses.delete(r);

				tx.commit();

				return true;
			}
			
			else {
				return false;
			}
		}

		else {
			return false;
		}

	}

}
