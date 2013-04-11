package com.anonimo.api.model;


public class Event {

	public final static String TAG = Event.class.getSimpleName();
	
	private Long id;
	private Long userId;
	private String description;
	private String latitude;
	private String longitude;
	private Long date;
	
	public Event() {
	}
	
	public Event(Event m) {
		this.id = m.id;
		this.userId = m.userId;
		this.description = m.description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String text) {
		this.description = text;
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
