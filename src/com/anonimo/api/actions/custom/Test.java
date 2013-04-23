package com.anonimo.api.actions.custom;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void getMessages() {
		String id = ServletActionContext.getRequest().getParameter("id");
		writeJsonResponse("{\"success\":\"true\"}");
	}
	
	private void writeJsonResponse(String json) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json");
			response.getWriter().println(json);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
