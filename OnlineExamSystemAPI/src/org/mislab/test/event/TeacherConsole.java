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
    
    static TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };    
    
    public static void main(String[] args) {
//        TeacherConsole tcon = new TeacherConsole();
        
        TeacherAccount tch = new TeacherAccount(tdata[0].name, tdata[0].passwd);
        tch.login();
        
        int courseId = tch.getCourseId();
        int examId = tch.getExamId(courseId);
        
        System.out.println(String.format("cid: %d, eid:%d", courseId, examId));
        tch.getUser().sendMessage(courseId, examId, "hello");
        tch.logout();
        
    }
}
