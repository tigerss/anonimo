package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.database.Event;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;

@Results({
    @Result(name="success", type="redirectAction", params = {"actionName" , "messages"})
})
public class EventsController implements ModelDriven<Object> {

	private String id;
	private Event model = new Event();
    private Collection<Object> list;
	
	public HttpHeaders index() {
		list = DatabaseHelper.getAllEvents();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // Handles /messages/{id} GET requests
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
    
    // Handles /events/{id} DELETE requests
    public DefaultHttpHeaders destroy() {
    	DatabaseHelper.destroy(model);
    	return new DefaultHttpHeaders("destroy");
    }
    
    public DefaultHttpHeaders participants() {
    	list = DatabaseHelper.getParticipants(model);
    	return new DefaultHttpHeaders("participants");
    }

    public void setId(String id) {
    	this.id = id;
    	
        if (this.id != null) {
            this.model = DatabaseHelper.getEventById(Long.valueOf(this.id));
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }
}
