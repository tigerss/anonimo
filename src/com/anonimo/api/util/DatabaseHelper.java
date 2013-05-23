package com.anonimo.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.anonimo.api.model.EventParticipants;
import com.anonimo.api.model.MessageVotes;
import com.anonimo.api.model.Photos;
import com.anonimo.api.model.database.Comment;
import com.anonimo.api.model.database.Event;
import com.anonimo.api.model.database.Message;
import com.anonimo.api.model.database.Photo;
import com.anonimo.api.model.database.User;
import com.anonimo.api.model.database.UserEvent;
import com.anonimo.api.model.database.Vote;

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

	public static Vote getVoteById(long id) {
		try {
			return getObjectById(id, Vote.TAG);
		} catch (ClassCastException ex) {
			return new Vote();
		}
	}

	public static Photos getPhotoById(long id) {
		try {
			Photo photo = getObjectById(id, Photo.TAG);
			return new Photos(photo);
		} catch (ClassCastException ex) {
			return new Photos();
		}
	}

	@SuppressWarnings("unchecked")
	public static Collection<Object> getAllUsers() {
		Collection<Object> users = new ArrayList<Object>();
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

	@SuppressWarnings("unchecked")
	public static Collection<Object> getMessagesOfUser(User user) {
		return (Collection<Object>) queryDatabase("from Message m where m.userId="
				+ user.getId());
	}

	@SuppressWarnings("unchecked")
	public static Collection<Object> getCommentsOfMessage(Message model) {
		return (Collection<Object>) queryDatabase("from Comment c where c.messageId = "
				+ model.getId());
	}
	
	public static Collection<Object> getVotesOfMessage(Message model) {
		List<?> upVotes = queryDatabase("select COUNT(*) from Vote v where v.messageId=" + model.getId() + " and v.value='" + Vote.UP + "'");
		List<?> downVotes = queryDatabase("select COUNT(*) from Vote v where v.messageId=" + model.getId() + " and v.value='" + Vote.DOWN + "'");
		
		Number ups = (Number) upVotes.get(0);
		Number downs = (Number) downVotes.get(0);
		
		MessageVotes messageStats = new MessageVotes();
		messageStats.setUpVotes(ups);
		messageStats.setDownVotes(downs);
		
		Collection<Object> result = new ArrayList<Object>();
		result.add(messageStats);
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Collection<Object> getPhotoOfMessage(Message model) {
		try {
			List<?> photoOfMessage = queryDatabase("from Photo p where p.messageId=" + model.getId());
			
//			Photo photo = (Photo)photoOfMessage.get(0);
			
			Collection<Object> result = new ArrayList<Object>();
			
			for (Photo p : (List<Photo>)photoOfMessage) {
				result.add(new Photos(p));
			}
			
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Object>();
		}
	}
	
	public static Collection<Object> getParticipants(Event model) {
		List<?> participants = queryDatabase("SELECT COUNT(*) from UserEvent e where e.eventId="+model.getId());
		Number numberOfParticipants = (Number) participants.get(0);
		
		EventParticipants eventStats = new EventParticipants(model.getId(), numberOfParticipants.intValue());
		
		Collection<Object> result = new ArrayList<Object>();
		result.add(eventStats);
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Collection<Object> getEventsJoinedByUser(User user) {
		return (Collection<Object>) queryDatabase("from UserEvent e where e.userId="
				+ user.getId());
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
	public static Collection<Object> getAllMessages() {
		return (Collection<Object>) queryDatabase("from Message");
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
	public static Collection<Object> getAllEvents() {
		return (Collection<Object>) queryDatabase("from Event");
	}

	@SuppressWarnings("unchecked")
	public static Collection<Vote> getAllVotes() {
		return (Collection<Vote>) queryDatabase("from Vote");
	}

	@SuppressWarnings("unchecked")
	public static Collection<Object> getAllPhotos() {
		return (Collection<Object>) queryDatabase("from Photo");
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<Vote> getVotesByUserAndMessage(long userId, long messageId) {
		return (Collection<Vote>) queryDatabase("from Vote v where v.userId=" + userId + " and messageId=" + messageId);
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
