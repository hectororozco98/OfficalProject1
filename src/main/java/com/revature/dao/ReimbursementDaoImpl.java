package com.revature.dao;

import java.util.List;
import java.util.Optional;

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
	public Reimbursement findById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		Optional<Reimbursement> returnedReim = ses.createQuery("from reimbursements WHERE id = " + id, Reimbursement.class).stream().findFirst();
		
		return null;
	}

	@Override
	public Reimbursement findByAuthorId(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		Optional<Reimbursement> returnedReim = ses.createQuery("from reimbursements WHERE author_id = " + id, Reimbursement.class).stream().findFirst();
		
		return null;
	}

	@Override
	public List<Reimbursement> findAll() {
	
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> emps = ses.createQuery("from reimbursements", Reimbursement.class).list();
		
		return emps;
	}

	@Override
	public boolean update(Reimbursement r) {
		// TODO Auto-generated method stub
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		ses.update(r);
		
		tx.commit();
		
		return true;
	}

	@Override
	public boolean delete(Reimbursement r) {
		// TODO Auto-generated method stub
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		ses.delete(r);
		
		tx.commit();
		
		return true;
	}

}
