    package com.example.simplechatprogramfinal.Usecase;

    import java.io.*;
    import java.net.*;
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.logging.Logger;

    public class ChatServer {
        private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
        private static final int PORT = 8080;
        private ExecutorService threadpool;
        private int clientCounter = 1;

        private Map<String, PrintWriter> clients;

        public ChatServer() {
            threadpool = Executors.newCachedThreadPool();
            clients = new ConcurrentHashMap<>();
        }

        public void ServerStart() {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                logger.info("ChatServer has been started on PORT: " + PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    String clientId = "client" + clientCounter++;
                    logger.info("Client connected with ID: " + clientId);
                    threadpool.execute(new MessageProtocol(clientSocket, this,clientId));

                }
            } catch (UnknownHostException u){
                logger.severe("Server not found" + u.getMessage());
            } catch (IOException e) {
                logger.severe("Server failed to start: " + e.getMessage());
            }
        }


        public void registerClient(String clientId, PrintWriter printWriter) {
            clients.put(clientId, printWriter);
        }


        public void unregisterClient(String clientId) {
            clients.remove(clientId);
            logger.info(clientId + " has been unregistered");
        }
        public void UnicastMessage(String senderID, String message, String targetClientID) {

            logger.info("UnicastMessage called with senderID: " + senderID + ", message: " + message + ", targetClientID: " + targetClientID);


            PrintWriter targetClientWriter = clients.get(targetClientID);


            logger.info("Registered clients: " + clients.keySet());


            if (targetClientWriter != null) {
                targetClientWriter.println("Unicast message from " + senderID + ": " + message);
                targetClientWriter.flush();
                logger.info("Unicast message sent to " + targetClientID);
            } else {
                PrintWriter senderWriter = clients.get(senderID);
                if (senderWriter != null) {
                    senderWriter.println("Client " + targetClientID + " not found.");
                    senderWriter.flush();
                }
                logger.info("Client " + targetClientID + " not found in registered clients.");
            }
        }


        public void BroadcastMessage(String senderID, String message) {
            clients.forEach((clientId, clientWriter) -> {
                if (clientId.equals(senderID)) {

                    clientWriter.println("Your message: " + message);
                } else {

                    clientWriter.println("Broadcast message from " + senderID + ": " + message);
                }
                clientWriter.flush();
            });
        }

        public static void main(String[] args) {
            ChatServer server = new ChatServer();
            server.ServerStart();
        }
    }