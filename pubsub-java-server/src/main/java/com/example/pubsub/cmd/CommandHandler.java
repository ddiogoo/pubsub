package com.example.pubsub.cmd;

import com.example.pubsub.mq.PubSubBroker;
import java.util.Arrays;
import java.util.List;

public class CommandHandler {
  private final PubSubBroker broker;

  public CommandHandler(PubSubBroker broker) {
    this.broker = broker;
  }

  public void handle(String[] command) {
    List<String> commandList = Arrays.asList(command);
    switch (commandList.get(0)) {
        case "subscribe" -> subscribe(commandList.get(1), commandList.get(2));
        case "unsubscribe" -> unsubscribe(commandList.get(1), commandList.get(2));
        case "publish" -> publish(commandList.get(1), commandList.get(2));
        case "consume" -> consume(commandList.get(1));
        default -> throw new IllegalArgumentException("Invalid command: " + commandList.get(0));
    }
  }

  private void subscribe(String topic, String clientId) {
    broker.subscribe(topic, clientId);
    System.out.println("Subscribed to topic: " + topic + " with client id: " + clientId);
  }

  private void unsubscribe(String topic, String clientId) {
    broker.unsubscribe(topic, clientId);
    System.out.println("Unsubscribed from topic: " + topic + " with client id: " + clientId);
  }

  private void publish(String topic, String message) {
    broker.publish(topic, message);
    System.out.println("Published message: " + message + " to topic: " + topic);
  }

  private void consume(String topic) {
    broker.consume(topic);
    System.out.println("Consumed message from topic: " + topic);
  }
}
