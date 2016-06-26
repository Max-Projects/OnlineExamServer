package org.mislab.test.event;

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
        stAccount.login();
        stAccount.logout();
    }
}
