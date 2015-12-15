package org.mislab.api;

import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import javafx.scene.input.KeyEvent;

public class Student extends User {
    public Student(int uid) {
        super(uid);
    }
    
    public Response attendExam(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/student/%d/attend",
                courseId, examId, super.userId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response submitSourceCode(int courseId, int examId, int problemId,
            String sourceCode) {
        String uri = String.format("/course/%d/exam/%d/problem/%d/student/%d/source-code",
                courseId, examId, problemId, super.userId);
        
        JsonObject json = new JsonObject();
        
        json.addProperty("sourceCode", sourceCode);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response sendSnapshot(int courseId, int examId, byte[] snapshot) {
        String uri = String.format("/course/%d/exam/%d/student/%d/snapshot",
                courseId, examId, super.userId);
        
        JsonObject json = new JsonObject();
        
        try {
            json.addProperty("snapshot", new String(snapshot, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            json.addProperty("snapshot", "");
            
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response sendTestResult(int courseId, int examId, int problemId,
            String result) {
        String uri = String.format("/course/%d/exam/%d/problem/%d/student/%s/test-result",
                courseId, examId, problemId, super.profile.getStudentId());
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response sendKeyEvent(int courseId, int examId, KeyEvent keyEvent) {
        String uri = String.format("/course/%d/exam/%d/student/%d/monitor/send-key-event",
                courseId, examId, super.userId);
        
        JsonObject json = new JsonObject();
        
        json.addProperty("keyCode", keyEvent.getCode().ordinal());
        json.addProperty("keyEventType", keyEvent.getEventType().getName());
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response queryExamResults(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/student/%d/score",
                courseId, examId, super.userId);
        
        return Utils.get(CLIENT, uri);
    }
}
