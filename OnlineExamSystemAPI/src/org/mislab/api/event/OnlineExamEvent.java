package org.mislab.api.event;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.EventObject;
import java.util.Map;

public class OnlineExamEvent extends EventObject {
    private static final Type STR_STR_MAP_TYPE;
    
    static {
        STR_STR_MAP_TYPE = new TypeToken<Map<String, String>>(){}.getType();
    }
    
    private final EventType type;
    private final EventAction action;
    private final Map<String, String> content;
    
    public OnlineExamEvent(JsonObject json) {
        super(json);
        
        type = EventType.valueOf(json.get("type").getAsString());
        action = EventAction.valueOf(json.get("action").getAsString());
        
        String contentStr = json.get("content").getAsString();
        
        if (contentStr == null || contentStr.length() == 0) {
            this.content = null;
        } else {
            Gson gson = new Gson();
            
            this.content = gson.fromJson(contentStr, STR_STR_MAP_TYPE);
        }
    }
    
    public EventAction getTarget() {
        return action;
    }
    
    public EventType getType() {
        return type;
    }
    
    public Map<String, String> getContent() {
        return content;
    }
}
