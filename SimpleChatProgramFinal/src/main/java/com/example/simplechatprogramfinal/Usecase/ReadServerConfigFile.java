package com.example.simplechatprogramfinal.Usecase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Reads server configuration from a properties file.
 */
public class ReadServerConfigFile {

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private Properties properties;

    /**
     * Constructor initializes properties and loads them from the configuration file.
     */
    public ReadServerConfigFile() {
        properties = new Properties();
        loadProperties();
    }
    /**
     * Loads properties from the specified configuration file.
     */
    private void loadProperties() {
        File file = new File("SimpleChatProgramFinal/src/main/java/com/example/simplechatprogramfinal/Config/Server.properties");

        try (InputStream input = new FileInputStream(file)) {
            properties.load(input);
        } catch (IOException ex) {
          logger.info(("Error loading properties file: " + ex.getMessage()));
        }
    }
    /**
     * Gets the server host from the properties.
     */
    public String getServerHost() {
        return properties.getProperty("serverHost");
    }


    /**
     * Gets the server port from the properties.
     */
    public int getServerPort() {
        return Integer.parseInt(properties.getProperty("serverPort"));
    }


}