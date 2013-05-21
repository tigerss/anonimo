package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.database.UserEvent;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;

@Results({
    @Result(name="success", type="redirectAction", params = {"actionName" , "messages"})
})
public class User_eventsController implements ModelDriven<Object> {

	private String id;
	private UserEvent model = new UserEvent();
    private Collection<UserEvent> list;
	
	public HttpHeaders index() {
		list = DatabaseHelper.getAllUserEvents();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // Handles /userevents/{id} GET requests
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    
    public HttpHeaders create() {
    	DatabaseHelper.save(model);
    	return new DefaultHttpHeaders("create").setLocationId(model.getId());
    }

    // Handles /orders/{id} PUT requests
    public DefaultHttpHeaders update() {
    	DatabaseHelper.update(model);
        return new DefaultHttpHeaders("update");
    }
    
    // Handles /userevents/{id} DELETE requests
    public DefaultHttpHeaders destroy() {
    	DatabaseHelper.destroy(model);
    	return new DefaultHttpHeaders("destroy");
    }

    public void setId(String id) {
    	this.id = id;
    	
        if (this.id != null) {
            this.model = DatabaseHelper.getUserEventsById(Long.valueOf(this.id));
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }
}
