package com.anonimo.api.model;

import java.util.Date;

public class Message {

	private Long id;
	private User user;
	private String text;
	private String latitude;
	private String longitude;
	private Date date;
	
	public Message() { }
	
	public Message(Message m) {
		this.id = m.id;
		this.user = m.user;
		this.text = m.text;
		this.latitude = m.latitude;
		this.longitude = m.longitude;
		this.date = m.date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
