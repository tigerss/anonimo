package com.anonimo.api.model.database;

import javax.persistence.Lob;

import com.anonimo.api.model.Photos;

public class Photo {

	public final static String TAG = Photo.class.getSimpleName();
	
	private Long id;
	private Long messageId;
	@Lob private byte[] image;
	
	public Photo() {}
	
	public Photo(Photos photos) {
		this.id = photos.getId();
		this.messageId = photos.getMessageId();
		this.image = photos.getImage().getBytes();
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
