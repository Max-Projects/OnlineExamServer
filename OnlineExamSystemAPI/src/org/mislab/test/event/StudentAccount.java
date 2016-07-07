package org.mislab.test.event;

import org.mislab.api.Response;
import org.mislab.api.Student;
import org.mislab.api.User;
import org.mislab.api.event.EventAction;
import org.mislab.api.event.EventType;
import org.mislab.api.event.OnlineExamEvent;

/**
 *
 * @author Max
 */
public class StudentAccount extends UserAccount {
    private Student student;

    public StudentAccount(String n, String pw) {
        super(n, pw);
    }

    @Override
    public void setupEventListener() {
        evMgr.addEventListener(this, EventType.Exam, EventAction.Extend);
        evMgr.addEventListener(this, EventType.Exam, EventAction.Halt);
        evMgr.addEventListener(this, EventType.Exam, EventAction.Pause);
        evMgr.addEventListener(this, EventType.Exam, EventAction.Resume);
        evMgr.addEventListener(this, EventType.Exam, EventAction.Start);
        evMgr.addEventListener(this, EventType.Exam, EventAction.Stop);
        evMgr.addEventListener(this, EventType.Chat, EventAction.NewMessage);
        evMgr.addEventListener(this, EventType.Monitor, EventAction.RequestSnapshot);        
    }
    
    @Override
    public User login() {
        Response res = User.login(name, password);
        
        if (res.success()) {
            student =  (Student) res.getContent().get("user");
        } else {
            System.out.println(String.format("%s login FAILS!", name));
        }
        return student;
    }
    
    @Override
    public void logout() {
        if (student != null) {
            student.logout();
            student = null;
        } else {
            System.out.println(String.format("%s logout FAILS", name));
        }
    }
    
    @Override
    public void handleOnlineExamEvent(OnlineExamEvent e) {
        System.out.println(String.format("%s got an event %s", name, e));
    }
}
