package org.mislab.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;

public class Response {
    private final ErrorCode errorCode;
    private final Map<String, Object> content;
    
    public Response(JsonObject json) {
        this.errorCode = ErrorCode.values()[json.get("errorCode").getAsInt()];
        
        if (errorCode == ErrorCode.OK) {
            String contentStr = json.get("content").getAsString();
            
            if (contentStr == null || contentStr.length() == 0) {
                this.content = null;
            } else {
                Gson gson = new Gson();

                this.content = (Map) gson.fromJson(contentStr, Object.class);
            }
        } else {
            this.content = null;
        }
    }
    
    public Response(ErrorCode code) {
        this.errorCode = code;
        this.content = null;
    }
    
    public boolean success() {
        return errorCode == ErrorCode.OK;
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    public Map<String, Object> getContent() {
        return content;
    }
    
    protected void addContent(String key, Object obj) {
        content.put(key, obj);
    }
}
