package org.mislab.api.event;

import com.google.gson.JsonObject;
import java.util.EventObject;

public class OnlineExamEvent extends EventObject {
    private final EventTarget target;
    private final EventType type;
    private final String content;
    
    public OnlineExamEvent(JsonObject json) {
        super(json);
        
        target = EventTarget.valueOf(json.get("eventTarget").getAsString());
        type = EventType.valueOf(json.get("eventType").getAsString());
        content = json.get("content").getAsString();
    }
    
    public EventTarget getTarget() {
        return target;
    }
    
    public EventType getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
}
