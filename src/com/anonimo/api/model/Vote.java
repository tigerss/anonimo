package com.anonimo.api.model;


public class Vote {

	public final static String TAG = Vote.class.getSimpleName();
	
	public final static String UP = "up";
	public final static String DOWN = "down";
	
	private Long id;
	private Long userId;
	private Long messageId;
	private String value;
	
	public Vote() {
	}
	
	public Vote(Vote m) {
		this.id = m.id;
		this.userId = m.userId;
		this.messageId = m.messageId;
		this.value = m.value;
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

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean hasValidValue() {
		return (null != value && (UP.equals(value) || DOWN.equals(value)));
	}
}
