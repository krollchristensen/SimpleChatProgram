package com.example.simplechatprogramfinal.Usecase;

import java.io.IOException;
import java.util.logging.Logger;

public class ChatClient {

    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    public static void startChatClient(String serverHost, int serverPort) {
        try {
            ClientCommunicationHandler communicationHandler = new ClientCommunicationHandler(serverHost, serverPort);
            logger.info("Connected to the server at " + serverHost + ":" + serverPort);
            communicationHandler.sendMessageToServer();
        } catch (IOException e) {
            logger.severe("Connection to the server failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        String serverHost = readServerConfigFile.getServerHost();
        int serverPort = readServerConfigFile.getServerPort();

        startChatClient(serverHost, serverPort);
    }
}