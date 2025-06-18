package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientReceiver implements Runnable {
    private final BufferedReader input;

    // Constructor
    public ClientReceiver(BufferedReader input) {
        this.input = input;
    }
    public void run() {
        try {
            String fromServer;
            while ((fromServer = input.readLine()) != null) {
                System.out.println("[Hệ thống]: " + fromServer);
            }
        } catch (IOException e) {
            System.out.println("Server closed connection");
        }
    }


}
