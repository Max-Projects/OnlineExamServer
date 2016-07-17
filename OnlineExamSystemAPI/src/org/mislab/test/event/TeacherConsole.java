package org.mislab.test.event;

/**
 *
 * @author Max
 */
public class TeacherConsole extends UserConsole {
    public static int COURSE_ID, EXAM_ID;
    public static TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };
    
    public static void main(String[] args) {
        TeacherAccount tch = new TeacherAccount(tdata[0].name, tdata[0].passwd);
        TeacherConsole tcon = new TeacherConsole();
        
        tcon.scheduledTaskRelTime(
            () -> {
                tch.login();
                COURSE_ID = tch.getCourseId();
                EXAM_ID = tch.getExamId(COURSE_ID);
                System.out.println(String.format("t:cid: %d, eid:%d", COURSE_ID, EXAM_ID));
            },
            0);
        
        
        tcon.scheduledTaskRelTime(
            ()-> {
                tch.getUser().sendMessage(COURSE_ID, EXAM_ID, "hello");        
            },
            1);
        
        tcon.scheduledTaskRelTime(
            () -> 
                tch.logout(), 
            1);
    }
}
