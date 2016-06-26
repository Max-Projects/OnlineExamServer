package org.mislab.test.event;

import org.mislab.api.Teacher;

/**
 *
 * @author Max
 */
public class TeacherTestEnd {
    public static TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };    
    
//    public void setupCourseAndExam(Teacher t) {
//        t.sendMessage(0, 0, "hello");
//    }
//    
    public static void main(String[] args) {
        TeacherAccount t = new TeacherAccount(tdata[0].name, tdata[0].passwd);
        Teacher tch = (Teacher) t.login();
        tch.sendMessage(0, 0, "hello");
        
    }
}
