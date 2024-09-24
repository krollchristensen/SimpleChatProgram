package com.example.simplechatprogramfinal.Usecase;

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


/**
 * ClientManager manages the connected clients and their PrintWriters.
 */

public class ClientManager {
    private static final Logger logger = Logger.getLogger(ClientManager.class.getName());
    private Map<String, PrintWriter> clients;

    /**
     * Initializes the ClientManager with a ConcurrentHashMap.
     */

    public ClientManager() {
        clients = new ConcurrentHashMap<>();
    }

    /**
     * Gets the map of registered clients.
     */
    public Map<String, PrintWriter> getClients() {
        return clients;
    }


    /**
     * Registers a client by adding their ID and PrintWriter to the map.
     */
    public void registerClient(String clientId, PrintWriter printWriter) {
        clients.put(clientId, printWriter);
        logger.info(clientId + " has been registered");
    }


    /**
     * Unregisters a client by removing their ID and PrintWriter from the map.
     */
    public void unregisterClient(String clientId) {
        clients.remove(clientId);
        logger.info(clientId + " has been unregistered");
    }
}