    package com.example.simplechatprogramfinal.Usecase;

    import java.io.*;
    import java.net.*;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.logging.Logger;

    public class ChatServer {
        private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
        private ExecutorService threadpool;
        private  int clientCounter = 1;
        private ClientManager clientManager;

        public ChatServer() {
            threadpool = Executors.newCachedThreadPool();
            clientManager = new ClientManager();
        }

        public void ServerStart() {
            ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
            int serverPort = readServerConfigFile.getServerPort();

            try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
                logger.info("ChatServer has been started on PORT: " + serverPort);

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
        public static void main(String[] args) {
            ChatServer server = new ChatServer();
            server.ServerStart();
        }
    }