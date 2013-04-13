package com.anonimo.java.test.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpWrapper {
	
	public static String httpGet(String url, HttpEntity entity) {
		HttpGet method = new HttpGet(url);
		
		return executeMethod(method);
	}
	
	public static String httpPost(String url, HttpEntity entity) {
		HttpPost method = new HttpPost(url);
		method.setEntity(entity);
		
		return executeMethod(method);
	}

	public static String httpDelete(String url) {
		HttpDelete method = new HttpDelete(url);
		
		return executeMethod(method);
	}
	
	public static String executeMethod(HttpUriRequest request) {
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(request);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		
		return null;
	}

}
