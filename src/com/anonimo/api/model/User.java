package com.anonimo.api.model;


public class User {
	
	public static final String TAG = User.class.getSimpleName();
	
	private Long id;
	private String name;
	private String password;
	private String email;

	public User() { }
	
	public User(User u) {
		this.id = u.id;
		this.name = u.name;
		this.password = u.password;
		this.email = u.email;
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
}
