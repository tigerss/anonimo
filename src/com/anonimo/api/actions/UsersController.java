package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.User;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({
    @Result(name="success", type="redirectAction", params = {"actionName" , "users"})
})
public class UsersController extends ValidationAwareSupport implements ModelDriven<Object>, Validateable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private User model = new User();
    private Collection<Object> list;
	
	public HttpHeaders index() {
		list = DatabaseHelper.getAllUsers();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // Handles /users/{id} GET requests
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    
    public HttpHeaders create() {
    	DatabaseHelper.save(model);
    	addActionMessage("New user created successfully");
    	return new DefaultHttpHeaders("create").setLocationId(model.getId());
    }

    // Handles /users/{id} PUT requests
    public DefaultHttpHeaders update() {
    	DatabaseHelper.update(model);
        return new DefaultHttpHeaders("update");
    }
    
    // Handles /users/{id} DELETE requests
    public DefaultHttpHeaders destroy() {
    	DatabaseHelper.destroy(model);
    	return new DefaultHttpHeaders("destroy");
    }
    
    public DefaultHttpHeaders messages() {
    	list = DatabaseHelper.getMessagesOfUser(model);
    	return new DefaultHttpHeaders("messages");
    }
    
    public DefaultHttpHeaders comments() {
    	return new DefaultHttpHeaders("comments");
    }

    public void setId(String id) {
    	this.id = id;
    	
        if (this.id != null) {
        	try {
        		long idAsLong = Long.valueOf(this.id);
        		this.model = DatabaseHelper.getUserById(idAsLong);
        	} catch (NumberFormatException ex) {
        		this.model = DatabaseHelper.getUserByName(id);
        	}
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }

	@Override
	public void validate() {
        if (model.getName() == null || model.getName().length() ==0) {
            addFieldError("name", "The client name is empty");
        }
	}
}
