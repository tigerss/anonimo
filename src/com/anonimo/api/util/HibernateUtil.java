package com.anonimo.api.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jboss.logging.Logger;

public class HibernateUtil {

	private static final Logger logger = (Logger) Logger.getLogger(HibernateUtil.class);
	
    private static final SessionFactory sessionFactory = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configureSessionFactory() {
    	try {
	        Configuration configuration = new Configuration();
	        configuration.configure();
	        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	        return configuration.buildSessionFactory(serviceRegistry);
    	} catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
		}
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void insertObject(Object o) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Transaction tx = null;
    	try {
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
    	} catch (Exception ex) {
    		if (null != tx) tx.rollback();
    		logger.error(ex.getMessage(), ex);
    	} finally {
    		if (session.isOpen())
    			session.close();
    	}
    }
    
    public static void updateObject(Object o) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Transaction tx = null;
    	try {
			tx = session.beginTransaction();
			session.update(o);
			tx.commit();
    	} catch (Exception ex) {
    		if (null != tx) tx.rollback();
    		logger.error(ex.getMessage(), ex);
    	} finally {
    		if (session.isOpen())
    			session.close();
    	}
    }

	public static void deleteObject(Object o) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Transaction tx = null;
    	try {
			tx = session.beginTransaction();
			session.delete(o);
			tx.commit();
    	} catch (Exception ex) {
    		if (null != tx) tx.rollback();
    		logger.error(ex.getMessage(), ex);
    	} finally {
    		if (session.isOpen())
    			session.close();
    	}
	}
}

