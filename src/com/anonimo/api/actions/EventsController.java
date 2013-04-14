package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.Event;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({
    @Result(name="success", type="redirectAction", params = {"actionName" , "messages"})
})
public class EventsController extends ValidationAwareSupport implements ModelDriven<Object>, Validateable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Event model = new Event();
    private Collection<Event> list;
	
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
    	addActionMessage("New message created successfully");
    	return new DefaultHttpHeaders("create").setLocationId(model.getId());
    }

    // Handles /orders/{id} PUT requests
    public DefaultHttpHeaders update() {
    	DatabaseHelper.update(model);
        return new DefaultHttpHeaders("update");
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

	@Override
	public void validate() {
        if (model.getDescription() == null || model.getDescription().length() ==0) {
            addFieldError("description", "The description is empty");
        }
	}
}
