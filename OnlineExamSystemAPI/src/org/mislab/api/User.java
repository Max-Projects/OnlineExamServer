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
        
        json.put("userName", userName);
        json.put("password", password);
        
        Response res = Utils.post(CLIENT, "/user/login", json);
        
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
    
    public static Response register(String userName, String password, Role role,
            String email, int graduateYear, byte[] image) {
        JsonObject json = new JsonObject();
        
        json.put("userName", userName);
        json.put("password", password);
        json.put("role", role.toString());
        json.put("email", email);
        json.put("graduateYear", graduateYear);
        json.put("profilePhoto", image);
        
        return Utils.post(CLIENT, "/user/register", json);
    }
    
    public static Response forgetPassword(String userName) {
        JsonObject json = new JsonObject();
        
        json.put("userName", userName);
        
        return Utils.post(CLIENT, "/user/forget-password", json);
    }
    
    public Response resetPassword(String oldPassword, String newPassword) {
        JsonObject json = new JsonObject();
        
        json.put("userId", userId);
        json.put("oldPassword", oldPassword);
        json.put("newPassword", newPassword);
        
        return Utils.post(CLIENT, "/user/reset-password", json);
    }
    
    public Response getProfile() {
        String uri = String.format("/user/%d", userId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response getProfilePhoto() {
        String uri = String.format("/user/%d/photo", userId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response resetProfilePhoto(byte[] image) {
        String uri = String.format("/user/%d/photo", userId);
        
        JsonObject json = new JsonObject();
        
        json.put("userId", userId);
        json.put("profilePhoto", image);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response logout() {
        JsonObject json = new JsonObject();
        
        json.put("userId", userId);
        
        return Utils.post(CLIENT, "/user/logout", json);
    }
    
    public Response queryCourses() {
        return Utils.get(CLIENT, "/course");
    }
    
    public Response queryExams(int courseId) {
        String uri = String.format("/course/%d/exam", courseId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response queryHistoryMessages(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/chat/history",
                courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }

    public Response sendMessage(int courseId, int examId, String message) {
        String uri = String.format("/course/%d/exam/%d/chat/send-message",
                courseId, examId);
        
        JsonObject json = new JsonObject();
        
        json.put("userId", userId);
        json.put("message", message);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response getTestData(int courseId, int examId, int problemId) {
        String uri = String.format("/course/%d/exam/%d/problem/%d/testdata",
                courseId, examId, problemId);
        
        return Utils.get(CLIENT, uri);
    }
}
