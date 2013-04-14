package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.Message;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({
    @Result(name="success", type="redirectAction", params = {"actionName" , "messages"})
})
public class MessagesController extends ValidationAwareSupport implements ModelDriven<Object>, Validateable {

	private static final long serialVersionUID = 1L;
	
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
    	addActionMessage("New message created successfully");
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

	@Override
	public void validate() {
        if (model.getText() == null || model.getText().length() ==0) {
            addFieldError("text", "The message text is empty");
        }
	}
}
