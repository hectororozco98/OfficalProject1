package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReimbursementType;
import com.revature.models.UserType;
import com.revature.util.HibernateUtil;

public class UserTypeDaoImpl implements IUserTypeDao {

	@Override
	public int insert(UserType ut) {

		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.save(ut);

		tx.commit();
		
		ses.close();

		return 1;
	}

	@Override
	public List<UserType> findAll() {

		Session ses = HibernateUtil.getSession();

		List<UserType> types = ses.createQuery("from UserType", UserType.class).list();
		
		ses.close();

		return types;

	}

	@Override
	public boolean update(UserType ut) {
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		ses.saveOrUpdate(ut);

		tx.commit();
		
		ses.close();
		
		return true;
	}

	@Override
	public boolean delete(UserType rt) {
		
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();
		
		ses.delete(rt);
		
		tx.commit();
		
		ses.close();
		
		return true;
	}

}
