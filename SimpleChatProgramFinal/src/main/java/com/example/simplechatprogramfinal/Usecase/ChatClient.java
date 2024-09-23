package com.example.simplechatprogramfinal.Usecase;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class ChatClient {

    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader in;
    private String clientId;

    public ChatClient(String serverHost, int serverPort) throws IOException {
        this.socket = new Socket(serverHost, serverPort);
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.clientId = in.readLine().split(": ")[1];
        logger.info("Connected as " + clientId);

        new Thread(this::readMessagesFromServer).start();
    }

    private void readMessagesFromServer() {
        try {
            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println("Server: " + serverResponse);
            }
        } catch (IOException e) {
            logger.severe("Failed to read from server: " + e.getMessage());
        }
    }

    public void sendMessageToServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String message = scanner.nextLine();
                sendMessageContent(message);
            }
        } catch (Exception e) {
            logger.info("Failed to send message: " + e.getMessage());
        }
    }

    private void sendMessageContent(String messageContent) {
        printWriter.println(messageContent);
        printWriter.flush();
    }

    public static void startChatClient(String serverHost, int serverPort) {
        try {
            ChatClient client = new ChatClient(serverHost, serverPort);
            logger.info("Connected to the server at " + serverHost + ":" + serverPort);
            client.sendMessageToServer(); // Optional: Start sending messages if needed
        } catch (IOException e) {
            logger.severe("Connection to the server failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        String serverHost = readServerConfigFile.getServerHost();
        int serverPort = readServerConfigFile.getServerPort();

        try {
            ChatClient client = new ChatClient(serverHost, serverPort);
            logger.info("Connected to the server at " + serverHost + ":" + serverPort);
            client.sendMessageToServer();
        } catch (IOException e) {
            logger.severe("Connection to the server failed: " + e.getMessage());
        }
    }
}