package com.revature.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.util.HibernateUtil;

public class ReimbursementTypeDaoImpl implements IReimbursementTypeDao {

	@Override
	public int insert(ReimbursementType rt) {
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
	
		ses.save(rt);
		
		tx.commit();
		
		return 1;
	}

	@Override
	public ReimbursementType findById(int i) {
		
		Session ses = HibernateUtil.getSession();
				
		ReimbursementType returnedReimType = ses.get(ReimbursementType.class, i);
		
		return returnedReimType;
	}

	@Override
	public List<ReimbursementType> findAll() {
		
		Session ses = HibernateUtil.getSession();
		
		List<ReimbursementType> types = ses.createQuery("from ReimbursementType", ReimbursementType.class).list();
 		
		return types;
	}

	@Override
	public boolean update(ReimbursementType rt) {

		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.saveOrUpdate(rt);

		tx.commit();
		
		return true;
	}

	@Override
	public boolean delete(ReimbursementType rt) {
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();
		
		ses.delete(rt);
		
		tx.commit();
		
		return true;
	}

}
