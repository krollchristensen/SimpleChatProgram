package com.example.simplechatprogramfinal.Usecase;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * ChatServer is responsible for managing client connections and message dispatching.
 */
public class ChatServer {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private final ExecutorService threadpool;
    private int clientCounter = 1;
    private final ClientManager clientManager;

    /**
     * Initializes the ChatServer with a thread pool and client manager.
     */
    public ChatServer() {
        threadpool = Executors.newCachedThreadPool();
        clientManager = new ClientManager();
    }

    /**
     * Starts the server
     * listens for incoming client connections.
     */
    public void ServerStart() {
        ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        int serverPort = readServerConfigFile.getServerPort();
        String serverHost = readServerConfigFile.getServerHost();

        try (ServerSocket serverSocket = new ServerSocket(serverPort, 50, InetAddress.getByName(serverHost))) {
            logger.info("ChatServer has been started on " + serverHost + ":" + serverPort);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientId = "client" + clientCounter++;
                logger.info("Client connected with ID: " + clientId);
                threadpool.execute(new MessageSender(clientSocket, clientManager, clientId));
            }
        } catch (UnknownHostException u) {
            logger.severe("Server not found: " + u.getMessage());
        } catch (IOException e) {
            logger.severe("Server failed to start: " + e.getMessage());
        }
    }

    /**
     * Start ChatServer.
     */
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.ServerStart();
    }
}