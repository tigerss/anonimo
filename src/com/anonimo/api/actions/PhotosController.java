package com.anonimo.api.actions;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.anonimo.api.model.Photos;
import com.anonimo.api.model.database.Photo;
import com.anonimo.api.util.DatabaseHelper;
import com.opensymphony.xwork2.ModelDriven;

public class PhotosController implements ModelDriven<Object> {

	private String id;
	private Photos model = new Photos();
    private Collection<Object> list;
	
	public HttpHeaders index() {
		Collection<Object> blobPhotos = DatabaseHelper.getAllPhotos();
		list = new ArrayList<Object>();
		for (Object o : blobPhotos) {
			if (!(o instanceof Photo))
				continue;
			
			Photo raw = (Photo) o;
			list.add(new Photos(raw));
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
    // Handles /photos/{id} GET requests
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    
    public HttpHeaders create() {
    	Photo photo = new Photo(model);
    	DatabaseHelper.save(photo);
    	return new DefaultHttpHeaders("create").setLocationId(model.getId());
    }

    // Handles /orders/{id} PUT requests
    public DefaultHttpHeaders update() {
    	Photo photo = new Photo(model);
    	DatabaseHelper.update(photo);
        return new DefaultHttpHeaders("update");
    }
    
    // Handles /messages/{id} DELETE requests
    public DefaultHttpHeaders destroy() {
    	Photo photo = new Photo(model);
    	DatabaseHelper.destroy(photo);
    	return new DefaultHttpHeaders("destroy");
    }
    
    public void setId(String id) {
    	this.id = id;
    	
        if (this.id != null) {
            this.model = DatabaseHelper.getPhotoById(Long.valueOf(this.id));
        }
    }
    
    @Override
    public Object getModel() {
        return (list != null ? list : model);
    }
}
