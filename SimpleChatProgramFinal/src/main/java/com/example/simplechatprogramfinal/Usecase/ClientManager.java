package com.example.simplechatprogramfinal.Usecase;

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ClientManager {
    private static final Logger logger = Logger.getLogger(ClientManager.class.getName());
    private Map<String, PrintWriter> clients;

    public ClientManager() {
        clients = new ConcurrentHashMap<>();
    }

    public Map<String, PrintWriter> getClients() {
        return clients;
    }

    public void registerClient(String clientId, PrintWriter printWriter) {
        clients.put(clientId, printWriter);
        logger.info(clientId + " has been registered");
    }

    public void unregisterClient(String clientId) {
        clients.remove(clientId);
        logger.info(clientId + " has been unregistered");
    }
}