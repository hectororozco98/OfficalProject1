package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements IReimbursementDao {

	@Override
	public int insert(Reimbursement r) {
	
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();	

		int pk = (int) ses.save(r);
		
		tx.commit();
		
		return pk;
	}

	@Override
	public Reimbursement findById(int i) {
		
		Session ses = HibernateUtil.getSession();
		
		//Optional<Reimbursement> returnedReim = ses.createQuery("from reimbursements WHERE id = " + i, Reimbursement.class).stream().findFirst();
		
		return null;
	}

	@Override
	public List<Reimbursement> findByAuthorId(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> returnedReim = ses.createQuery("from Reimbursement WHERE id = " + id, Reimbursement.class).list();
		
		return returnedReim;
	}

	@Override
	public List<Reimbursement> findAll() {
	
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> emps = ses.createQuery("from Reimbursement", Reimbursement.class).list();
			
		return emps;
	}

	@Override
	public boolean update(Reimbursement e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Reimbursement e) {
		// TODO Auto-generated method stub
		return false;
	}

}
