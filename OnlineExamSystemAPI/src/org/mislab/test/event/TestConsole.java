package org.mislab.test.event;

/**
 *
 * @author Max
 */
public class TestConsole {
    public void pause(int millsec) {
        try {
            Thread.sleep(millsec);
        } catch(Exception e) {
            System.out.println("TestTerminal pause error!");
            e.printStackTrace();
        }
    }
}
