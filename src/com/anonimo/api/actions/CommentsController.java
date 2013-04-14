package com.anonimo.api.actions;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.Comment;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({
    @Result(name="success", type="redirectAction", params = {"actionName" , "messages"})
})
public class CommentsController extends ValidationAwareSupport implements ModelDriven<Object>, Validateable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Comment model = new Comment();
    private Collection<Comment> list;
	
	public HttpHeaders index() {
		list = DatabaseHelper.getAllComments();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // Handles /comments/{id} GET requests
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    
    public HttpHeaders create() {
    	DatabaseHelper.save(model);
    	addActionMessage("New comment created successfully");
    	return new DefaultHttpHeaders("create").setLocationId(model.getId());
    }

    // Handles /comments/{id} PUT requests
    public DefaultHttpHeaders update() {
    	DatabaseHelper.update(model);
        return new DefaultHttpHeaders("update");
    }

    public void setId(String id) {
    	this.id = id;
    	
        if (this.id != null) {
            this.model = DatabaseHelper.getCommentById(Long.valueOf(this.id));
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }

	@Override
	public void validate() {
        if (model.getText() == null || model.getText().length() ==0) {
            addFieldError("comment", "The comment text is empty");
        }
	}
}
