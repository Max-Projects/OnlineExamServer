package org.mislab.test.event;

import org.mislab.api.Response;
import org.mislab.api.Teacher;
import org.mislab.api.User;
import org.mislab.api.event.EventAction;
import org.mislab.api.event.EventType;
import org.mislab.api.event.OnlineExamEvent;

/**
 *
 * @author Max
 */
public class TeacherAccount extends UserAccount {
    private Teacher tcher = null;
    private String name, passwd;

    public TeacherAccount(String n, String pw) {
        name = n; passwd = pw;
    }

    @Override
    public void login() {
        Response res = User.login(name, passwd);
        
        if (res.success()) {
            System.out.println(name+" login success!");
            tcher =  (Teacher) res.getContent().get("user");
        } else {
            System.out.println(name+" login FAILED!!!");
        }
    }
    
    @Override
    public void logout() {
        if (tcher != null) {
            tcher.logout();
            tcher = null;
        } else {
            System.out.println(String.format("%s logout FAILS", name));
        }
    }

    @Override
    public void setupEventListener() {
        evMgr.addEventListener(this, EventType.User, EventAction.Login);
        evMgr.addEventListener(this, EventType.User, EventAction.Logout);
        evMgr.addEventListener(this, EventType.Chat, EventAction.NewMessage);
        evMgr.addEventListener(this, EventType.Monitor, EventAction.KeyEvent);
        evMgr.addEventListener(this, EventType.Monitor, EventAction.RequestSnapshot);
        evMgr.addEventListener(this, EventType.Exam, EventAction.Submit);        
    }
    
    @Override
    public void handleOnlineExamEvent(OnlineExamEvent e) {
        System.out.println(String.format("%s got an event %s", name, e));        
    }
}
