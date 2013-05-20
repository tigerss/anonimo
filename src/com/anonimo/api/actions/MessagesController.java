package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.database.Message;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;

public class MessagesController implements ModelDriven<Object> {

	private String id;
	private Message model = new Message();
    private Collection<Object> list;
	
	public HttpHeaders index() {
		list = DatabaseHelper.getAllMessages();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // GET /orders/new
    public String editNew() {
        model = new Message();
        return "editNew";
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
    
    // Handles /messages/{id} DELETE requests
    public DefaultHttpHeaders destroy() {
    	DatabaseHelper.destroy(model);
    	return new DefaultHttpHeaders("destroy");
    }
    
    public DefaultHttpHeaders comments() {
    	list = DatabaseHelper.getCommentsOfMessage(model);
    	return new DefaultHttpHeaders("comments");
    }
    
    public DefaultHttpHeaders votes() {
    	list = DatabaseHelper.getVotesOfMessage(model);
    	return new DefaultHttpHeaders("votes");
    }
    
    public void setId(String id) {
    	this.id = id;
    	
        if (this.id != null) {
            this.model = DatabaseHelper.getMessageById(Long.valueOf(this.id));
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }
}
