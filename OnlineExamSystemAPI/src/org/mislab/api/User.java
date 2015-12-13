package org.mislab.api;

import com.squareup.okhttp.OkHttpClient;
import java.util.Map;
import java.util.logging.Logger;

public abstract class User {
    public static final Logger LOGGER;
    protected final static OkHttpClient CLIENT;
    
    protected final int userId;
    
    static {
        LOGGER = Logger.getLogger(JsonObject.class.getName());
        CLIENT = new OkHttpClient();
    }
    
    public User(int uid) {
        this.userId = uid;
    }
    
    public static Response login(String userName, String password) {
        JsonObject json = new JsonObject();
        
        json.put("username", userName);
        json.put("password", password);
        
        Response res = Utils.send(CLIENT, "/user/login", json);
        
        if (res.success()) {
            Map content = res.getContent();
            int uid = Integer.valueOf(content.get("userId").toString());
            
            switch (content.get("role").toString()) {
                case "teacher":
                    content.put("user", new Teacher(uid));
                    break;
                case "student":
                    content.put("user", new Student(uid));
                    break;
            }
            
            return new Response(content);
        } else {
            return new Response(res.getErrorCode());
        }
    }
    
    public Response logout() {
        JsonObject json = new JsonObject();
        
        json.put("userId", userId);
        
        return Utils.send(CLIENT, "/user/logout", json);
    }
}
