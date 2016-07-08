package org.mislab.test.event;

import org.mislab.api.Student;

/**
 *
 * @author Max
 */
public class StudentConsole extends TestConsole {
    public static StData[] stdata = { 
        new StData("alan", "alan"),
        new StData("bob", "bob") 
    };
        
    public static void main(String[] args) {
        StudentAccount st = new StudentAccount(stdata[0].name, stdata[0].passwd);
        st.login();
        
//        scon.pause(1000);
        
        int courseId = st.getCourseId();
        int examId = st.getExamId(courseId);
        
        System.out.println(String.format("cid: %d, eid:%d", courseId, examId));
        
        st.getUser().sendMessage(courseId, examId, String.format("%s sends a message", st.getName()));
//        scon.pause(2000);
        
        st.logout();
    }
}
