package com.anonimo.java.test;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.anonimo.api.model.User;
import com.anonimo.java.test.util.HttpWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestUser extends TestCase {

	private static final String USERS_URL = "http://localhost:8080/Anonimo/users";
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	@Test
	public void testCreateUser() throws UnsupportedEncodingException {
		JsonObject userJSON = new JsonObject();
		userJSON.addProperty("name", "test");
		userJSON.addProperty("password", "test");
		userJSON.addProperty("email", "email");
		
		HttpEntity user = new StringEntity(userJSON.toString());
		String response = HttpWrapper.httpPost(USERS_URL, user);
		
		System.out.println(response);
		
		String actual = HttpWrapper.httpGet(USERS_URL + "/3", null);
		Gson gson = new Gson();
		User actualUser = gson.fromJson(actual, User.class);
		
		Assert.assertEquals("test", actualUser.getName());
	}
}
