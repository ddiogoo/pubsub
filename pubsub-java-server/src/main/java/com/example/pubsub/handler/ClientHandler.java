package com.example.pubsub.handler;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final static Logger logger = Logger.getLogger(ClientHandler.class.toString());

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "ClientHandler started");
    }
}
