package com.example.pubsub.handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServerHandler {
  private final ServerSocket serverSocket;
  private final static Logger logger = 
        Logger.getLogger(ServerHandler.class.toString());

  public ServerHandler(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
    config();
  }

  private void config() {
    try {
      serverSocket.setReuseAddress(true);
    } catch (SocketException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public void listen() {
    try {
      while (true) {
        Socket client = serverSocket.accept();
        logger.log(Level.INFO, "New client connected: {0}\n", 
            client.getInetAddress().getHostAddress());
        new Thread(new ClientHandler(client)).start();
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage());
      throw new RuntimeException(e.getMessage());
    } finally {
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException e) {
          logger.log(Level.SEVERE, e.getMessage());
          throw new RuntimeException(e.getMessage());
        }
      }
    }
  }
}
