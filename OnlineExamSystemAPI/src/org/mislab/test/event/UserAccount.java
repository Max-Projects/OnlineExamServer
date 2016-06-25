/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mislab.test.event;

import org.mislab.api.event.OnlineExamEventListener;
import org.mislab.api.event.OnlineExamEventManager;

/**
 *
 * @author Max
 */
public abstract class UserAccount implements OnlineExamEventListener {
    protected OnlineExamEventManager evMgr = null;    
    
    public UserAccount() {
        evMgr = OnlineExamEventManager.getInstance();
        setupEventListener();
    }

    public abstract void setupEventListener();
    public abstract void login();
    public abstract void logout();    
}
