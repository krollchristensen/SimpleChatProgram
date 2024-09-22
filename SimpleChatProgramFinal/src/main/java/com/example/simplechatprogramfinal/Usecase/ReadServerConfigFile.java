package com.example.simplechatprogramfinal.Usecase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadServerConfigFile {
    private Properties properties;


    public ReadServerConfigFile() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        File file = new File("SimpleChatProgramFinal/src/main/java/com/example/simplechatprogramfinal/Config/Server.properties");
        System.out.println("File exists: " + file.exists());

        try (InputStream input = new FileInputStream(file)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


        public String getServerHost() {
        return properties.getProperty("serverHost");
    }

    public int getServerPort() {
        return Integer.parseInt(properties.getProperty("serverPort"));
    }
}