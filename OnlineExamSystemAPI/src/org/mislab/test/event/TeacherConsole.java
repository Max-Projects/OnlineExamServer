package org.mislab.test.event;

import java.util.ArrayList;
import java.util.Map;
import org.mislab.api.Response;
import org.mislab.api.Teacher;

/**
 *
 * @author Max
 */
public class TeacherConsole extends TestConsole {
    
    public static TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };    
    
    public int getCourseId(Teacher t) {
        Response resp = t.queryCourses();
        int courseId = -1;
        
        if (resp.success()) {
            ArrayList courses = (ArrayList) resp.getContent().get("courses");
            
            courseId = (int) ((Map)courses.get(0)).get("courseId");
        } else {
            System.out.println("query course FAILED!");            
        }
        return courseId;
    }
    
    public int getExamId(Teacher t, int cid) {
        Response resp = t.queryExams(cid);
        int examId = -1;
        
        if (resp.success()) {
            ArrayList exams = (ArrayList) resp.getContent().get("exams");
            System.out.println("exams"+exams.toString());
            examId = (int) ((Map)exams.get(0)).get("id");
        } else {
            System.out.println("query course FAILED!");            
        }
        return examId;
    }
    
    public static void main(String[] args) {
        TeacherConsole tcon = new TeacherConsole();
        
        TeacherAccount t = new TeacherAccount(tdata[0].name, tdata[0].passwd);
        Teacher tch = (Teacher) t.login();
        
        int courseId = tcon.getCourseId(tch);
        int examId = tcon.getExamId(tch, courseId);
        
        System.out.println(String.format("cid: %d, eid:%d", courseId, examId));
        tch.sendMessage(courseId, examId, "hello");
        
    }
}
