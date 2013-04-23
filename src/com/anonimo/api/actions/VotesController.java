package com.anonimo.api.actions;

import java.util.Collection;
import java.util.Iterator;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.Vote;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;

public class VotesController  implements ModelDriven<Object>{

	
	private String id;
	private Vote model = new Vote();
    private Collection<Vote> list;
   
	public HttpHeaders index() {
		list = DatabaseHelper.getAllVotes();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // Handles /userevents/{id} GET requests
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    
    public HttpHeaders create() {
    	Collection<Vote> result = DatabaseHelper.getVotesByUserAndMessage(model.getUserId(), model.getMessageId());
    	if (false == result.isEmpty()) {
    		if (model.hasValidValue()) {
    			Iterator<Vote> iterator = result.iterator();
    			Vote vote = iterator.next();
    			vote.setValue(model.getValue());
    			model = new Vote(vote);
    			DatabaseHelper.update(model);
    		}
    	} else {
    		DatabaseHelper.save(model);
    	}
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
            this.model = DatabaseHelper.getVoteById(Long.valueOf(this.id));
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }
}
