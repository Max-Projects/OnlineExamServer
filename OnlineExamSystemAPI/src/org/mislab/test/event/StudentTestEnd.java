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
        
    public static TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };    
    
    public static void main(String[] args) {
        StudentAccount st = new StudentAccount(stdata[0].name, stdata[0].passwd);
        st.login();
        st.logout();
    }
}
