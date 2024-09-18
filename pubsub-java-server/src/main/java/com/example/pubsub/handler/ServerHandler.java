package com.example.pubsub.handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public final class ServerHandler {
  private final ServerSocket serverSocket;

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
        System.out.println(
            "New client connected: " +
                client.getInetAddress().getHostAddress());
        new Thread(new ClientHandler(client)).start();
      }
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException e) {
          throw new RuntimeException(e.getMessage());
        }
      }
    }
  }
}
