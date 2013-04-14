package com.anonimo.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.anonimo.api.model.Comment;
import com.anonimo.api.model.Event;
import com.anonimo.api.model.Message;
import com.anonimo.api.model.User;
import com.anonimo.api.model.UserEvent;

public class DatabaseHelper {

	private static Logger logger = Logger.getLogger(DatabaseHelper.class);
	
	public static User getUserById(long id) {
		try {
			return getObjectById(id, User.TAG);
		} catch (ClassCastException ex) {
			return new User();
		}
	}
	
	public static User getUserByName(String name) {
		User user = new User();
		
		String queryString = "from User u where u.name='" + name + "'";
		List<?> result = queryDatabase(queryString);
		
		if (result.size() > 0) {
			user = (User) result.get(0);
		} else {
			logger.warn("!!!No objects found in DB!!!");
		}
		
		return user;
	}
	
	public static Message getMessageById(long id) {
		try {
			return getObjectById(id, Message.TAG);
		} catch (ClassCastException ex) {
			return new Message();
		}
	}

	public static Comment getCommentById(long id) {
		try {
			return getObjectById(id, Comment.TAG);
		} catch (ClassCastException ex) {
			return new Comment();
		}
	}

	public static UserEvent getUserEventsById(long id) {
		try {
			return getObjectById(id, UserEvent.TAG);
		} catch (ClassCastException ex) {
			return new UserEvent();
		}
	}
	
	public static Event getEventById(long id) {
		try {
			return getObjectById(id, Event.TAG);
		} catch (ClassCastException ex) {
			return new Event();
		}
	}

	@SuppressWarnings("unchecked")
	public static Collection<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List result = session.createQuery("from User")
					.list();
			if (result.size() > 0) {
				List<User> tempUsers = (List<User>)result;
				for (User u : tempUsers) {
					users.add(new User(u));
				}
			} else {
				logger.warn("!!!THIS USER DOES NOT HAVE AN ACCOUNT, DEFAULT IS USED!!!");
			}
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) tx.rollback();
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		
		return users;
	}

	public static void save(Object model) {
		HibernateUtil.insertObject(model);
	}
	
	public static void update(Object o) {
		HibernateUtil.updateObject(o);
	}
	
	public static void destroy(Object o) {
		HibernateUtil.deleteObject(o);
	}

	@SuppressWarnings("unchecked")
	public static Collection<Message> getAllMessages() {
		return (Collection<Message>) queryDatabase("from Message");
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<Comment> getAllComments() {
		return (Collection<Comment>) queryDatabase("from Comment");
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<UserEvent> getAllUserEvents() {
		return (Collection<UserEvent>) queryDatabase("from UserEvent");
	}

	@SuppressWarnings("unchecked")
	public static Collection<Event> getAllEvents() {
		return (Collection<Event>) queryDatabase("from Event");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObjectById(Long id, String tableName)
			throws ObjectNotFoundException {
		Object object = new Object();
		
		String queryString = "from " + tableName + " a where a.id=" + id;
		List<?> result = queryDatabase(queryString);
		
		if (result.size() > 0) {
			object = result.get(0);
		} else {
			logger.warn("!!!No objects found in DB!!!");
		}
		
		return (T) object;
	}
	
	public static List<?> queryDatabase(String query) {
		List<Object> results = new ArrayList<Object>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> result = session.createQuery(query).list();
			for (Object o : result) {
				results.add(o);
			}
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) tx.rollback();
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		
		return results;
	}

}
