package org.mislab.test.event;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Max
 */
class LogoutTimerTest extends TimerTask {
    StudentAccount st;
    
    public LogoutTimerTest(StudentAccount s) {
        st = s;
    }
    
    public void run() {
        st.logout();
        System.out.println("alan logout!");
    }
}

public class StudentConsole extends TestConsole {
    public static StData[] stdata = { 
        new StData("alan", "alan"),
        new StData("bob", "bob") 
    };
    
    
    public StudentConsole() {}

    @Override
    public void run() {
        StudentAccount st = new StudentAccount(stdata[0].name, stdata[0].passwd);
        Timer timer = new Timer();
        
        st.login();

        int courseId = st.getCourseId();
        int examId = st.getExamId(courseId);
        
        System.out.println(String.format("cid: %d, eid:%d", courseId, examId));
        
        st.getUser().sendMessage(courseId, examId, String.format("%s sends a message", st.getName()));
//        pause(1000);
//        timer.schedule(new LogoutTimerTest(st), 1000);        
    }
    
    public static void main(String[] args) {
//        StudentAccount st = new StudentAccount(stdata[0].name, stdata[0].passwd);
        StudentConsole scon = new StudentConsole();
        Thread th = new Thread(scon);
        th.start();
        
        
//        st.login();
        
//        scon.pause(1000);
        
//        int courseId = st.getCourseId();
//        int examId = st.getExamId(courseId);
        
//        System.out.println(String.format("cid: %d, eid:%d", courseId, examId));
//        
//        st.getUser().sendMessage(courseId, examId, String.format("%s sends a message", st.getName()));
//        scon.pause(1000);
//        
//        st.logout();
    }
}
