package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Session ses;
	// hibernate.xml needs to be mapped to any classes we want to get with hibernate
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public static Session getSession() { 

		if (ses == null) {
			
			ses = sf.openSession();

		}
		return ses; 

	}

	public static void closeSes() {
		/**
		 * Ideally when we close a session it frees up that connection to the DB and
		 * returns is to the connection pool so that is can be used by another thread or
		 * operation.
		 */
		ses.close();

	}
}
