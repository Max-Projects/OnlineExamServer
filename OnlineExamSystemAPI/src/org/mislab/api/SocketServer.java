package org.mislab.api;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mislab.api.event.OnlineExamEvent;
import org.mislab.api.event.OnlineExamEventManager;

public class SocketServer extends Thread {
    private static final Logger LOGGER;
    
    static {
        LOGGER = Logger.getLogger(SocketServer.class.getName());
    }
    
    private ServerSocket server;
    private final OnlineExamEventManager eventManager;
    
    public SocketServer() {
        try {
            server = new ServerSocket(Config.PORT);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        eventManager = OnlineExamEventManager.getInstance();
    }
    
    @Override
    public void run() {
        while (!server.isClosed()) {
            try {
                Socket socket = server.accept();
                
                handleConnection(socket);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void handleConnection(Socket socket) {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            JsonObject json = (JsonObject) ois.readObject();
            
            OnlineExamEvent event = new OnlineExamEvent(json);
            
            eventManager.fireEvent(event);
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    protected void closeServer() {
        try {
            server.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
