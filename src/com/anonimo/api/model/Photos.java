package com.anonimo.api.model;

import com.anonimo.api.model.database.Photo;


public class Photos {
	public final static String TAG = Photos.class.getSimpleName();
	
	private Long id;
	private Long messageId;
	private String image;
	
	public Photos() {}
	
	public Photos(Photo photo) {
		this.id = photo.getId();
		this.messageId = photo.getMessageId();
		this.image = new String(photo.getImage());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
