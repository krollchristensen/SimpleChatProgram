package com.example.simplechatprogramfinal.Usecase;

import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;

public class ClientMessageTypeHandler {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    public void messageTypeUnicast(String senderID, String message, String targetClientID, Map<String, PrintWriter> clients) {
        logger.info("UnicastMessage called with senderID: " + senderID + ", message: " + message + ", targetClientID: " + targetClientID);

        PrintWriter senderWriter = clients.get(senderID);
        PrintWriter targetClientWriter = clients.get(targetClientID);

        logger.info("Registered clients: " + clients.keySet());

        if (targetClientWriter != null) {
            targetClientWriter.println(message);
            targetClientWriter.flush();
            logger.info("Unicast message sent to " + targetClientID);
        } else {
            if (senderWriter != null) {
                senderWriter.println("Client " + targetClientID + " not found.");
                senderWriter.flush();
            }
            logger.info("Client " + targetClientID + " not found in registered clients.");
        }

        if (senderWriter != null) {
            senderWriter.println(message);
            senderWriter.flush();
        }
    }

    public void messageTypeBroadcastMessage (String senderID, String message, Map<String, PrintWriter> clients){
        clients.forEach((clientId, clientWriter) -> {
            if (clientId.equals(senderID)) {

                clientWriter.println("Your message: " + message);
            } else {

                clientWriter.println("Broadcast message from " + senderID + ": " + message);
            }
            clientWriter.flush();
        });
    }

    public void fileTransfer(){

    }

    public void emojiSupportText(){

    }

}
