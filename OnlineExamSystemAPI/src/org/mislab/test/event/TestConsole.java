package org.mislab.test.event;

import java.util.Timer;

/**
 *
 * @author Max
 */
public abstract class TestConsole implements Runnable {
    
    public void pause(int millsec) {
        try {
            Thread.currentThread().sleep(millsec);
        } catch(Exception e) {
            System.out.println("TestTerminal pause error!");
            e.printStackTrace();
        }
    }
}
