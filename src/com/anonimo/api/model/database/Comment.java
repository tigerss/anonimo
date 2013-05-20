package com.anonimo.api.model.database;


public class Comment {

	public final static String TAG = Comment.class.getSimpleName();
	
	private Long id;
	private Long userId;
	private Long messageId;
	private String text;
	private Long date;
	
	public Comment() {
	}
	
	public Comment(Comment m) {
		this.id = m.id;
		this.userId = m.userId;
		this.text = m.text;
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
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
}
