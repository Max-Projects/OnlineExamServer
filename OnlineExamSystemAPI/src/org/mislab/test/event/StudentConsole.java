package org.mislab.test.event;

/**
 *
 * @author Max
 */

public class StudentConsole extends UserConsole {
    public static int COURSE_ID, EXAM_ID;

    public static StData[] stdata = { 
        new StData("alan", "alan"),
        new StData("bob", "bob") 
    };
    
    public static void main(String[] args) {
        StudentAccount st = new StudentAccount(stdata[0].name, stdata[0].passwd);
        StudentConsole scon = new StudentConsole();

        scon.scheduledTaskRelTime(
            () -> {
                st.login();
                COURSE_ID = st.getCourseId();
                EXAM_ID = st.getExamId(COURSE_ID);
                System.out.println(String.format("s:cid: %d, eid:%d", COURSE_ID, EXAM_ID));
            }, 
            1);
        
        
        scon.scheduledTaskRelTime(
            ()-> {
                st.getUser().sendMessage(COURSE_ID, EXAM_ID, "hello");        
            }, 
            1);
        
        scon.scheduledTaskRelTime(
            () -> 
            st.logout(), 
            1);
    }
}

