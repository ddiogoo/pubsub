package com.example.pubsub.mq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PubSubSubscriber {
  private final static Logger logger = 
    Logger.getLogger(PubSubSubscriber.class.getName());
  private final String topic;
  private final String clientId;
  private final BlockingQueue<String> messages;


  public PubSubSubscriber(String topic, String clientId) {
    this.topic = topic;
    this.clientId = clientId;
    this.messages = new LinkedBlockingQueue<>();
  }

  public void add(String message) {
    try {
      messages.put(message);
      logger.log(Level.INFO, "Message added to queue: {0}", message);
    } catch (InterruptedException e) {
      logger.severe(e.getMessage());
    }
  }

  public String consume() {
    try {
      return messages.take();
    } catch (InterruptedException e) {
      logger.severe(e.getMessage());
      return null;
    }
  }

  public String getTopic() {
    return topic;
  }

  public String getClientId() {
      return clientId;
  }
}
