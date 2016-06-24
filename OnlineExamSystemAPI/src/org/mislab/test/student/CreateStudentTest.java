package org.mislab.test.student;

import java.util.List;
import org.mislab.api.Response;
import org.mislab.api.Role;
import org.mislab.api.User;

/**
 *
 * @author Max
 */
public class CreateStudentTest {

    public void registerStudent(String username, String passwd, String name, Role role, 
            String id, String email, int year, byte[] img) {
        
        Response res = User.register(username, passwd, name, role, id, email, year, img);
        
        if (res.success()) {
            System.out.println("student "+ username+" registers successful");
        } else {
            System.out.println("student "+ username+", Error [" +res.getErrorCode()+"] student register FAILED");
        }
    }
    
    public void createCourse(String name, List<String> studentList) {
        
    }
        
    public static void main(String[] args) {
        CreateStudentTest cst = new CreateStudentTest();

        cst.registerStudent("ivy", "student", "任芝萱", Role.Student,
            "4025s", "ivy@student.com", 104, new byte[5]);
//        cst.registerStudent("chris", "student", "周意立", Role.Student,
//            "4026s", "christine@student.com", 104, new byte[5]);
//        cst.registerStudent("lili", "student", "黃莉玲", Role.Student,
//            "4027s", "lilian@student.com", 104, new byte[5]);
        
    }
}
