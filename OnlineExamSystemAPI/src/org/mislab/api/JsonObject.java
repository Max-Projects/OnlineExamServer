package org.mislab.api;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObject extends JSONObject {
    public static final Logger LOGGER;
    
    static {
        LOGGER = Logger.getLogger(JsonObject.class.getName());
    }
    
    @Override
    public JsonObject put(String key, Object value) {
        try {
            return (JsonObject) super.put(key, value);
        } catch (JSONException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            
            return this;
        }
    }
    
    @Override
    public JsonObject put(String key, int value) {
        try {
            return (JsonObject) super.put(key, value);
        } catch (JSONException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            
            return this;
        }
    }
}
