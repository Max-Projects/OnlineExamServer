package org.mislab.test.event;

import org.mislab.api.Student;

/**
 *
 * @author Max
 */
public class StudentTestEnd {
    public static StData[] stdata = { 
        new StData("alan", "alan"),
        new StData("bob", "bob") 
    };
        
    public static void main(String[] args) {
        StudentAccount stAccount = new StudentAccount(stdata[0].name, stdata[0].passwd);        
        Student st = (Student) stAccount.login();
        
        st.sendMessage(0, 0, String.format("%s sends a message", stAccount.getName()));
        stAccount.logout();
    }
}
