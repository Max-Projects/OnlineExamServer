package org.mislab.api;

import java.util.Map;

public class Response {
    private final ErrorCode code;
    private final Map<String, Object> content;
    
    public Response(Map<String, Object> content) {
        this.code = ErrorCode.OK;
        this.content = content;
    }
    
    public Response(ErrorCode code) {
        this.code = code;
        this.content = null;
    }
    
    public boolean success() {
        return code == ErrorCode.OK;
    }
    
    public ErrorCode getErrorCode() {
        return code;
    }
    
    public Map<String, Object> getContent() {
        return content;
    }
}
