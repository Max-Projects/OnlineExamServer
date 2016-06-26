package org.mislab.test.event;

import org.mislab.api.User;
import org.mislab.api.event.OnlineExamEventListener;
import org.mislab.api.event.OnlineExamEventManager;

/**
 *
 * @author Max
 */
public abstract class UserAccount implements OnlineExamEventListener {
    protected String name, password;
    
    protected OnlineExamEventManager evMgr = null;    
    
    public UserAccount(String n, String pw) {
        name = n; password = pw;
        
        evMgr = OnlineExamEventManager.getInstance();
        setupEventListener();
    }

    public String getName() { return name; }
    public abstract void setupEventListener();
    public abstract User login();
    public abstract void logout();
    
    public String toString() { return "<"+name+">"; }
}
