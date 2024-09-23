package com.example.simplechatprogramfinal.Usecase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageProtocol {

    private static final String SEPARATOR = "|";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String createUnicastTextMessage(String clientId, String message) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        return String.format("%s|%s|TEXT|Unicast message from You: %s", clientId, timestamp, message);
    }

    public static String createBroadcastMessage(String clientId, String message) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        return String.format("%s|%s|TEXT|Broadcast message from You: %s", clientId, timestamp, message);
    }

    public static String createFileTransferMessage(String clientId, String fileName, long fileSize) {
        String timestamp = dateFormat.format(new Date());
        return clientId + SEPARATOR + timestamp + SEPARATOR + "FILE_TRANSFER" + SEPARATOR + fileName + SEPARATOR + fileSize;
    }

    public static String createEmojiMessage(String clientId, String emojiCode) {
        String timestamp = dateFormat.format(new Date());
        return clientId + SEPARATOR + timestamp + SEPARATOR + "EMOJI" + SEPARATOR + emojiCode;
    }

}