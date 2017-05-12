package org.mislab.api;

public interface Config {
//    public static final String HOST = "http://192.168.0.10:8000";
    public static String ServerURL = "http://172.16.96.169:8000";
    public static String LocalIP = "http://192.168.0.1";
    public static int PORT = 3001;
    public static String LocalURL = LocalIP + ":" + PORT;
    
    public static String snapshotScale = "100"; // in percentage to the original
    public static String snapshotFreq = "1000"; // in milliseconds
}
