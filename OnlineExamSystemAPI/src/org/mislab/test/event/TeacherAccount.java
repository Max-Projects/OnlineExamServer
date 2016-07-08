package org.mislab.test.event;

import org.mislab.api.event.EventAction;
import org.mislab.api.event.EventType;
import org.mislab.api.event.OnlineExamEvent;

/**
 *
 * @author Max
 */
public class TeacherAccount extends UserAccount {
    
    public TeacherAccount(String n, String pwd)  {
        super(n, pwd);
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
        System.out.println(String.format("%s got an event %s", this.getName(), e));        
    }
    
}
