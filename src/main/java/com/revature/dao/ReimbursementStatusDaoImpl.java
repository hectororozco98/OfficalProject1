package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.util.HibernateUtil;

public class ReimbursementStatusDaoImpl implements IReimbursementStatusDao {

	@Override
	public int insert(ReimbursementStatus rs) {

		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.save(rs);

		tx.commit();

		return 1;
	}

	@Override
	public List<ReimbursementStatus> findAll() {
		
		Session ses = HibernateUtil.getSession();

		List<ReimbursementStatus> status = ses.createQuery("from ReimbursementStatus", ReimbursementStatus.class)
				.list();

		return status;
	}

	@Override
	public boolean update(ReimbursementStatus rs) {
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.saveOrUpdate(rs);

		tx.commit();
		
		return true;
	}

	@Override
	public boolean delete(ReimbursementStatus rs) {
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();
		
		ses.delete(rs);
		
		tx.commit();
		
		return true;
	}

}
