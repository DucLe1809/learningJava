package Clients;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;


public class ClientReceiver implements Runnable {
    private final BufferedReader input;
    private final ObjectMapper mapper = new ObjectMapper();

    // Constructor
    public ClientReceiver(BufferedReader input) {
        this.input = input;
    }

    public void run() {
        try {
            String jsonFromServer;
            while ((jsonFromServer = input.readLine()) != null) {
                ChatMessage msgFromServer = mapper.readValue(jsonFromServer, ChatMessage.class);
                System.out.println("[Hệ thống]: " + msgFromServer.content);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
