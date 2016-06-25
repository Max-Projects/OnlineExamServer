/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mislab.test.event;

import org.mislab.api.Response;
import org.mislab.api.Teacher;
import org.mislab.api.User;
import org.mislab.api.event.EventAction;
import org.mislab.api.event.EventType;
import org.mislab.api.event.OnlineExamEvent;
import org.mislab.api.event.OnlineExamEventListener;
import org.mislab.api.event.OnlineExamEventManager;

/**
 *
 * @author Max
 */
public class UserAccount implements OnlineExamEventListener {
    class UserData {
        public String name;
        public String passwd;
        public boolean isAdmin;
        
        public UserData(String n, String pw, boolean adm) {
            name = n; passwd = pw; isAdmin = isAdmin;
        }
        public String toString() {
            return String.format("[%s, %s]", name, isAdmin ? "T" : "S");
        }
    }
    
    class StData extends UserData {
        public StData(String n, String pw) {
            super(n, pw, false);
        }
    }
    
    class TData extends UserData {
        public TData(String n, String pw) {
            super(n, pw, true);
        }
    }
    
    private Teacher tcher = null;
    private OnlineExamEventManager evMgr = null;
    private String tname = "max", tpasswd = "max";
    private TData[] tdata = {
        new TData("max", "max"),
        new TData("chico", "chico")
    };
    
    private StData[] stdata = { 
        new StData("alan", "alan"),
        new StData("bob", "bob") 
    };
    
    public UserAccount(OnlineExamEventManager mgr) {
        evMgr = mgr;
        evMgr.addEventListener(this, EventType.User, EventAction.Login);
        evMgr.addEventListener(this, EventType.User, EventAction.Logout);
    }

    public void handleOnlineExamEvent(OnlineExamEvent e) {
        System.out.println(String.format("rcv: %s", e));
    }
    
    public void loginTeacher() {
        Response res = User.login(tdata[0].name, tdata[0].passwd);
        
        if (res.success()) {
            System.out.println("max login success!");
            tcher =  (Teacher) res.getContent().get("user");
        } else {
            System.out.println("max login FAILED!!!");
        }
    }

    public void logout() {
        if (tcher != null) {
            tcher.logout();
            tcher = null;
        } else {
            System.out.println("T: max - logout error");
        }
    }
}
