package com.example.pubsub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static class ServerThread implements Runnable {
        private Socket socket;
        private ServerSocket serverSocket;
        private BufferedReader bufferedReader;
        private InputStreamReader inputStreamReader;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(5000);
                System.out.println("Pub/Sub server starting!");
                while (true) { 
                    socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket.getLocalAddress().toString());

                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    String message = bufferedReader.readLine();
                    
                    while(message != null && !message.equals("/exit")) {
                        System.out.println("Received: " + message);
                        message = bufferedReader.readLine();
                    }

                    System.out.println("Client disconnected: " + socket.getLocalAddress().toString());
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Thread thread = new Thread(new ServerThread());
        thread.start();
    }
}
