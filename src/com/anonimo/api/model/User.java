package com.anonimo.api.model;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private Set<Message> messages = new HashSet<Message>(0);

	public User() { }
	
	public User(User u) {
		this.id = u.id;
		this.name = u.name;
		this.password = u.password;
		this.email = u.email;
		
		// TODO copy messages
		this.messages.addAll(u.messages);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
}
