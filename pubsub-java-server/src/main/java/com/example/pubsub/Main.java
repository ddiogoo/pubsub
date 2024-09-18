package com.example.pubsub;

import java.io.IOException;
import java.net.ServerSocket;

import com.example.pubsub.handler.ServerHandler;

public class Main {
    public static void main(String[] args) {
        try {
            new ServerHandler(new ServerSocket(5000)).listen();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
