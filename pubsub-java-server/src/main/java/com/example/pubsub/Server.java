package com.example.pubsub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.pubsub.handler.ClientHandler;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
            serverSocket.setReuseAddress(true);
            while(true) {
                Socket client = serverSocket.accept();
                System.out.println(
                    "New client connected: " + 
                    client.getInetAddress().getHostAddress()
                );
                new Thread(new ClientHandler(client)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) { 
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }
}
