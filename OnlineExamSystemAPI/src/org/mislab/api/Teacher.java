package org.mislab.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Timestamp;
import java.util.List;
import org.mislab.api.Problem.TestData;

public class Teacher extends User {
    public Teacher(int uid) {
        super(uid);
    }
    
    public Response createCourse(String courseName, int year, int semester,
            List<String> studentIds) {
        JsonObject json = new JsonObject();
        
        json.addProperty("userId", super.userId);
        json.addProperty("courseName", courseName);
        json.addProperty("year", year);
        json.addProperty("semester", semester);
        
        JsonArray studentIdsJson = new JsonArray();
        
        for (String studentId: studentIds) {
            studentIdsJson.add(studentId);
        }
        
        json.add("studentIds", studentIdsJson);
        
        return Utils.post(CLIENT, "/course/create", json);
    }
    
    public Response removeCourse(int courseId) {
        String uri = String.format("/course/%d/remove", courseId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response createExam(int courseId, String examName,
            List<Problem> problems, Timestamp begin, int durationInMinutes) {
        String uri = String.format("/course/%d/exam/add", courseId);
        
        JsonObject json = new JsonObject();
        
        json.addProperty("examName", examName);
        json.addProperty("beginTime", begin.toString());
        json.addProperty("duration", durationInMinutes);
        
        JsonArray problemsJson = new JsonArray();
        
        for (Problem problem: problems) {
            JsonObject probJson = new JsonObject();
            
            probJson.addProperty("title", problem.getTitle());
            probJson.addProperty("description", problem.getDescription());
            
            JsonArray testdataJson = new JsonArray();
            
            for (TestData td: problem.getTestData()) {
                JsonObject tdJson = new JsonObject();
                
                tdJson.addProperty("input", td.input);
                tdJson.addProperty("output", td.output);
                
                testdataJson.add(tdJson);
            }
        }
        
        json.add("problems", problemsJson);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response removeExam(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/remove", courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response queryStudents(int courseId) {
        String uri = String.format("/course/%d/student", courseId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response getSnapshots(int courseId, int examId, String studentId) {
        String uri = String.format("/course/%d/exam/%d/student/%s/snapshot",
                courseId, examId, studentId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response requestCurrentSnapshot(int courseId, int examId,
            String studentId) {
        String uri = String.format("/course/%d/exam/%d/student/%s/request-current-snapshot",
                courseId, examId, studentId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response startMonitor(int courseId, int examId, String studentId) {
        String uri = String.format("/course/%d/exam/%d/student/%s/monitor/start",
                courseId, examId, studentId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response stopMonitor(int courseId, int examId, String studentId) {
        String uri = String.format("/course/%d/exam/%d/student/%s/monitor/stop",
                courseId, examId, studentId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response pauseExam(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/pause",
                courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response resumeExam(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/resume",
                courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response haltExam(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/halt",
                courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response extendExam(int courseId, int examId, int extendMinutes) {
        String uri = String.format("/course/%d/exam/%d/extend",
                courseId, examId);
        
        JsonObject json = new JsonObject();
        
        json.addProperty("extendMinutes", extendMinutes);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response getSourceCode(int courseId, int examId, int problemId,
            String studentId) {
        String uri = String.format("/course/%d/exam/%d/problem/%d/student/%s/source-code",
                courseId, examId, problemId, studentId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response getStudentResult(int courseId, int examId, int problemId,
            String studentId) {
        String uri = String.format("/course/%d/exam/%d/problem/%d/student/%s/test-result",
                courseId, examId, problemId, studentId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response scoreAndComment(int courseId, int examId, int problemId,
            String studentId, int score, String comment) {
        String uri = String.format("/course/%d/exam/%d/problem/%d/student/%s/score",
                courseId, examId, problemId, studentId);
        
        JsonObject json = new JsonObject();
        
        json.addProperty("score", score);
        json.addProperty("comment", comment);
        
        return Utils.post(CLIENT, uri, json);
    }
    
    public Response finishScoring(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/finish-scoring",
                courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }
    
    public Response queryExamAllResults(int courseId, int examId) {
        String uri = String.format("/course/%d/exam/%d/result",
                courseId, examId);
        
        return Utils.get(CLIENT, uri);
    }
}
