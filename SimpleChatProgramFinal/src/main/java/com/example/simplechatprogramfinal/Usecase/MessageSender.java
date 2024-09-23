package com.example.simplechatprogramfinal.Usecase;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class MessageSender implements Runnable {
    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());
    private final Socket clientSocket;
    private final String clientId;
    private final ClientManager clientManager;
    private final ClientMessageTypeHandler clientMessageTypeHandler;

    public MessageSender(Socket socket, ClientManager clientManager, String clientId) throws IOException {
        this.clientSocket = socket;
        this.clientId = clientId;
        this.clientManager = clientManager;
        this.clientMessageTypeHandler = new ClientMessageTypeHandler();


        clientManager.registerClient(clientId, new PrintWriter(socket.getOutputStream(), true));
    }

    /**
     * Handles client communication by prompting the user to select a message type
     * @throws IOException
     */
    @Override
    public void run() {
        try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            printWriter.println("Client ID: " + clientId);

            while (true) {
                printWriter.println("Choose message type: 1 for Text, 2 for File Transfer, 3 for Emoji:");
                String messageTypeChoice = in.readLine().trim();

                switch (messageTypeChoice) {
                    case "1":
                        handleTextMessage(in, printWriter);
                        break;
                    case "2":
                        handleFileTransfer(printWriter);
                        break;
                    case "3":
                        handleEmojiMessage(printWriter);
                        break;
                    default:
                        printWriter.println("Invalid option. Please enter 1 for Text, 2 for File Transfer, or 3 for Emoji.");
                        break;
                }
            }
        } catch (IOException e) {
            logger.severe("Error handling client: " + e.getMessage());
        } finally {
            clientManager.unregisterClient(clientId);
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.severe("Failed to close client socket: " + e.getMessage());
            }
        }
    }

    /**
     * Method for text type, either text one client or text all clients
     *
     * @throws IOException
     */
    private void handleTextMessage(BufferedReader in, PrintWriter printWriter) throws IOException {
        printWriter.println("Press 1 for Unicast message or 2 for Broadcast message:");
        String textMessageType = in.readLine().trim();

        if ("1".equals(textMessageType)) {
            printWriter.println("Enter the target client ID for unicast:");
            String targetClientID = in.readLine().trim();
            printWriter.println("Enter your message:");
            String unicastMessage = in.readLine().trim();
            String formattedMessage = MessageProtocol.createUnicastTextMessage(clientId, unicastMessage);
            clientMessageTypeHandler.messageTypeUnicast(clientId, formattedMessage, targetClientID, clientManager.getClients());
        } else if ("2".equals(textMessageType)) {
            printWriter.println("Enter your message for broadcast:");
            String broadcastMessage = in.readLine().trim();
            String formattedMessage = MessageProtocol.createBroadcastMessage(clientId, broadcastMessage);
            clientMessageTypeHandler.messageTypeBroadcastMessage(clientId, formattedMessage, clientManager.getClients());
        } else {
            printWriter.println("Invalid option. Please enter 1 for Unicast or 2 for Broadcast.");
        }
    }

    /**
     * File transfer feature is not implemented yet.
     *
     * @throws IOException
     */
    private void handleFileTransfer(PrintWriter printWriter) throws IOException {
        printWriter.println("File transfer feature is not implemented yet.");
    }

    /**
     * Emoji support feature is not implemented yet
     *
     * @throws IOException
     */
    private void handleEmojiMessage(PrintWriter printWriter) throws IOException {
        printWriter.println("Emoji support feature is not implemented yet");
    }
}