package org.mislab.api;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServer extends Thread {
    private static final Logger LOGGER;
    
    static {
        LOGGER = Logger.getLogger(SocketServer.class.getName());
    }
    
    private ServerSocket server;
    
    public SocketServer() {
        try {
            server = new ServerSocket();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while (true) {
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
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
}
