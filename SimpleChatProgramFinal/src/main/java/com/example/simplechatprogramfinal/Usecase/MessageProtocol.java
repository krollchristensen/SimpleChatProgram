package com.example.simplechatprogramfinal.Usecase;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageProtocol implements Runnable {
    private static final Logger logger = Logger.getLogger(MessageProtocol.class.getName());
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader in;
    private String clientId;

    private ChatServer chatServer;

    public MessageProtocol(Socket socket,ChatServer chatServer, String clientId) throws IOException {
        this.clientSocket = socket;
        this.clientId = clientId;
        this.chatServer = chatServer;

        this.printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        chatServer.registerClient(clientId,printWriter);
    }
    @Override
    public void run() {
        try {
            printWriter.println("Client id: " + clientId);
            printWriter.flush();


            printWriter.println("Unicast or broadcast message? Enter 1 for unicast, 2 for broadcast:");
            printWriter.flush();

            String messageType = in.readLine().trim();

            while (messageType != null) {
                switch (messageType) {
                    case "1":
                        printWriter.println("Enter the target client ID for unicast:");
                        printWriter.flush();
                        String targetClientID = in.readLine().trim();


                        printWriter.println("Enter your message:");
                        printWriter.flush();
                        String unicastMessage = in.readLine().trim();


                        chatServer.UnicastMessage(clientId, unicastMessage, targetClientID);
                        break;

                    case "2":
                        printWriter.println("Enter your message for broadcast:");
                        printWriter.flush();
                        String broadcastMessage = in.readLine().trim();
                        chatServer.BroadcastMessage(clientId,broadcastMessage);
                        break;

                    default:
                        printWriter.println("Invalid option. Please enter 1 for unicast or 2 for broadcast.");
                        printWriter.flush();
                        break;
                }

                printWriter.println("Unicast or broadcast message? Enter 1 for unicast, 2 for broadcast:");
                printWriter.flush();
                messageType = in.readLine().trim();
            }
        } catch (IOException e) {
            logger.severe("Error handling client: " + e.getMessage());
        } finally {
            chatServer.unregisterClient(clientId);
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.severe("Failed to close client socket: " + e.getMessage());
            }
        }
    }




}
