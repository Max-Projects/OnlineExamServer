package org.mislab.api.event;

import java.util.ArrayList;
import java.util.List;

public class OnlineExamEventManager {
    private static OnlineExamEventManager INSTANCE = null;
    
    public static OnlineExamEventManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OnlineExamEventManager();
        }
        
        return INSTANCE;
    }
    
    private final List<OnlineExamEventListener> listeners;

    private OnlineExamEventManager() {
        listeners = new ArrayList<>();
    }
    
    public synchronized void addEventListener(OnlineExamEventListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeEventListener(OnlineExamEventListener listener) {
        listeners.remove(listener);
    }

    private synchronized void fireEvent(OnlineExamEvent e) {
        for (OnlineExamEventListener listener: listeners) {
            listener.handleOnlineExamEvent(e);
        }
    }
}
