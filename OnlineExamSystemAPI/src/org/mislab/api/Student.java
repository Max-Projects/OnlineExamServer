package org.mislab.api;

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
        
        json.put("sourceCode", sourceCode);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response sendSnapshot(int courseId, int examId, byte[] snapshot) {
        String uri = String.format("/course/%d/exam/%d/student/%d/snapshot",
                courseId, examId, super.userId);
        
        JsonObject json = new JsonObject();
        
        json.put("snapshot", snapshot);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response sendKeyEvent(int courseId, int examId, KeyEvent keyEvent) {
        String uri = String.format("/course/%d/exam/%d/student/%d/monitor/send-key-event",
                courseId, examId, super.userId);
        
        JsonObject json = new JsonObject();
        
        json.put("keyEvent", keyEvent);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response queryExamResults(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/student/%d/score",
                courseId, examId, super.userId);
        
        return Utils.get(CLIENT, uri);
    }
}
