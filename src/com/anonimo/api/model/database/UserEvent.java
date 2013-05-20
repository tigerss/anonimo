package com.anonimo.api.model.database;


public class UserEvent {

	public final static String TAG = UserEvent.class.getSimpleName();
	
	private Long id;
	private Long userId;
	private Long eventId;
	private String text;
	
	public UserEvent() {
	}
	
	public UserEvent(UserEvent m) {
		this.id = m.id;
		this.userId = m.userId;
		this.eventId = m.eventId;
		this.text = m.text;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long user) {
		this.userId = user;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long event) {
		this.eventId = event;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
