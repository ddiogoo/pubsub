package com.example.pubsub.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PubSubBroker {
  private static final Logger logger = 
    Logger.getLogger(PubSubBroker.class.getName());
  private final Map<String, List<PubSubSubscriber>> subscribers;

  public PubSubBroker() {
    this.subscribers = new HashMap<>();
  }

  public void subscribe(String topic, String clientId) {
    subscribers.computeIfAbsent(topic, k -> new ArrayList<>(){
      {
        add(new PubSubSubscriber(topic, clientId));
      }
    }).add(new PubSubSubscriber(topic, clientId));
    logger.log(Level.INFO, "New subscriber: {0} to topic: {1}", new Object[]{clientId, topic});
  }

  public void unsubscribe(String topic, String clientId) {
    List<PubSubSubscriber> subs = this.subscribers.get(topic);
    if(subs == null) {
      logger.log(Level.INFO, "No subscribers for topic: {0}", topic);
      return;
    }
    subs.removeIf(subscriber -> subscriber.getClientId().equals(clientId));
    logger.log(Level.INFO, "Subscriber: {0} unsubscribed from topic: {1}", new Object[]{clientId, topic});
  }

  public void publish(String topic, String message) {
    List<PubSubSubscriber> subs = this.subscribers.get(topic);
    if (subscribers == null) {
      logger.log(Level.INFO, "No subscribers for topic: {0}", topic);
      return;
    }
    subs.forEach(subscriber -> subscriber.add(message));
  }

  public void consume(String topic) {
    List<PubSubSubscriber> subs = this.subscribers.get(topic);
    if (subs == null) {
      logger.log(Level.INFO, "No subscribers for topic: {0}", topic);
      return;
    }
    subs.forEach(subscriber -> {
      String message = subscriber.consume();
      logger.log(Level.INFO, "Message consumed: {0} by subscriber: {1}", new Object[]{
        message == null ? message : "empty", subscriber.getClientId()
      });
    });
  }
}
