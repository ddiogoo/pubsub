package com.example.pubsub.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
  private final Socket clientSocket;

  public ClientHandler(Socket clientSocket) {
      this.clientSocket = clientSocket;
  }

  @Override
  public void run() {
      PrintWriter out = null;
      BufferedReader in =  null;
      try {
          out = new PrintWriter(clientSocket.getOutputStream(), true);
          in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

          String command;
          while((command = in.readLine()) != null) {
              System.out.printf("Sent from client: %s\n", command);
              out.print(command);
          }
      } catch (IOException e) {
          throw new RuntimeException(e.getMessage());
      } finally {
          try {
              if(out != null) out.close();
              if(in != null) {
                  in.close();
                  clientSocket.close();
              }  
          } catch (IOException e) {
              throw new RuntimeException(e.getMessage());
          }
      }
  }
}