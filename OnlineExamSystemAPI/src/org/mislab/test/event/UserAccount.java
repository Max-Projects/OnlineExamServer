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
public abstract class UserAccount implements OnlineExamEventListener {
    protected OnlineExamEventManager evMgr = null;    
    private String tname = "max", tpasswd = "max";
    
    public UserAccount() {
        evMgr = OnlineExamEventManager.getInstance();
        setupEventListener();
    }

    public abstract void setupEventListener();
    public abstract void login();
    public abstract void logout();    
}
