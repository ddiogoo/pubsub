## Pub/Sub Messaging Server

This project implements a Publish/Subscribe (Pub/Sub) messaging server. In this architecture, "publishers" send messages to specific topics, while "subscribers" subscribe to those topics to receive the relevant messages. The server is built in Java, using Maven for dependency management and build processes, and communicates over TCP.

## Features

| Features          | Description                                                                       |
| ----------------- | --------------------------------------------------------------------------------- |
| Publish           | Clients can publish messages to a specific topic.                                 |
| Subscribe         | Clients can subscribe to topics to receive relevant messages.                     |
| Unsubscribe       | Clients can unsubscribe from topics they no longer wish to receive messages from. |
| TCP Communication | All communication between clients and the server happens over TCP.                |

## Project Structure

```folder
pubsub-server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/                                
├── pom.xml                                   
└── README.md          
```

## How to Run

### Clone the Repository

First, clone the repository to your local machine:

```bash
$ git clone https://github.com/ddiogoo/pubsub.git
$ cd pubsub
```

### Build and Run

1. Compile the project using Maven:

```bash
$ mvn clean install
```

2. Once compiled, run the server:

```bash
$ mvn exec:java -Dexec.mainClass="com.example.pubsub.Main"
```

The server will start and listen for client connections on a configurable TCP port.

### Configuration

You can customize the server's port and other settings in the application.properties file, which is located in the src/main/resources directory. For example, to change the TCP port:

```properties
server.port=12345
```

By default, the server listens on `localhost` and the specified port.

## Usage

Once the server is running, clients can connect via TCP and issue commands to publish messages to topics, subscribe to specific topics, or unsubscribe from them.

### Supported Commands

- `SUBSCRIBE [topic]`: Subscribe to a specific topic to receive messages.
- `UNSUBSCRIBE [topic]`: Unsubscribe from a topic.
- `PUBLISH [topic] [message]`: Publish a message to a specific topic.

## Architectural Design

![Architectural Design](./.github/publish-subscribe.png)

## Contribution

Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the Creative Commons NonCommercial 3.0. See the [LICENSE](https://creativecommons.org/licenses/by-nc-sa/3.0/legalcode.en) file for more information.
