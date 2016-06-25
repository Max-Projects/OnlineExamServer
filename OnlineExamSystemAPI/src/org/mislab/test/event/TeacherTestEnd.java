package org.mislab.test.event;

/**
 *
 * @author Max
 */
public class TeacherTestEnd {
    public static TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };    
    
    public static void main(String[] args) {
        TeacherAccount t = new TeacherAccount(tdata[0].name, tdata[0].passwd);
        t.login();
    }
}
