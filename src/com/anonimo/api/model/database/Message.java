package com.anonimo.api.model.database;


public class Message {

	public final static String TAG = Message.class.getSimpleName();
	
	private Long id;
	private Long userId;
	private String text;
	private String latitude;
	private String longitude;
	private Long date;
	
	public Message() {
	}
	
	public Message(Message m) {
		this.id = m.id;
		this.userId = m.userId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long user) {
		this.userId = user;
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
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
}
