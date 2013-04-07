package com.anonimo.api.handlers;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.StrutsConstants;
import org.apache.struts2.rest.handler.ContentTypeHandler;

import com.opensymphony.xwork2.inject.Inject;

public class MyJsonContentHandler implements ContentTypeHandler {
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private String defaultEncoding = "ISO-8859-1";

    public void toObject(Reader in, Object target) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            sb.append(buffer, 0, len);
        }
        if (target != null && sb.length() > 0 && sb.charAt(0) == '[') {
            JSONArray jsonArray = JSONArray.fromObject(sb.toString(), newJsonConfig());
            if (target.getClass().isArray()) {
                JSONArray.toArray(jsonArray, target, newJsonConfig());
            } else {
                JSONArray.toList(jsonArray, target, newJsonConfig());
            }

        } else {
            JSONObject jsonObject = JSONObject.fromObject(sb.toString(), newJsonConfig());
            JSONObject.toBean(jsonObject, target, newJsonConfig());
        }
    }

    public String fromObject(Object obj, String resultCode, Writer stream) throws IOException {
        if (obj != null) {
            if (isArray(obj)) {
                JSONArray jsonArray = JSONArray.fromObject(obj, newJsonConfig());
                stream.write(jsonArray.toString());
            } else {
                JSONObject jsonObject = JSONObject.fromObject(obj, newJsonConfig());
                stream.write(jsonObject.toString());
            }
        }
        return null;


    }

    private boolean isArray(Object obj) {
        return obj instanceof Collection || obj.getClass().isArray();
    }

    public String getContentType() {
        return DEFAULT_CONTENT_TYPE+";charset=" + this.defaultEncoding;
    }
    
    public String getExtension() {
        return "json";
    }
    
    @Inject(StrutsConstants.STRUTS_I18N_ENCODING)
    public void setDefaultEncoding(String val) {
        this.defaultEncoding = val;
    } 
    
    public JsonConfig newJsonConfig() {
    	JsonConfig config = new JsonConfig();
    	config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    	return config;
    }
}
