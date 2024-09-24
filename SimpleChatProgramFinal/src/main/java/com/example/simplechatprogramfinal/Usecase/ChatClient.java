package com.example.simplechatprogramfinal.Usecase;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * ChatClient is responsible for establishing a connection to the chat server
 * and initiating communication.
 */
public class ChatClient {

    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    /**
     * Starts the chat client by connecting to the specified server and port.
     *
     * @param serverHost the host address of the server
     * @param serverPort the port number on which the server is listening
     */
    public static void startChatClient(String serverHost, int serverPort) {
        try {
            ClientCommunicationHandler communicationHandler = new ClientCommunicationHandler(serverHost, serverPort);
            logger.info("Connected to the server at " + serverHost + " and Port: " + serverPort);
            communicationHandler.sendMessageToServer();
        } catch (UnknownHostException e) {
            logger.severe("Unknown host: " + serverHost);
        } catch (ConnectException e) {
            logger.severe("Connection to the server failed: " + e.getMessage());
        } catch (IOException e) {
            logger.severe("I/O error occurred when connecting to the server: " + e.getMessage());
        }
    }

    /**
     * Starts the chat client.
     */
    public static void main(String[] args) {
        ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        String serverHost = readServerConfigFile.getServerHost();
        int serverPort = readServerConfigFile.getServerPort();

        startChatClient(serverHost, serverPort);
    }
}