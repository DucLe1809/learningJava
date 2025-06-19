package Clients;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

import java.io.PrintWriter;
public class ClientSender implements Runnable {
    private final BufferedReader console;
    private final PrintWriter output;
    private final ObjectMapper mapper = new ObjectMapper();

    public ClientSender(BufferedReader console, PrintWriter output) {
        this.console = console;
        this.output = output;
    }

    public void run() {
        try {
            String message;
            while ((message = console.readLine()) != null) {
                ChatMessage msg;
                if (message.equalsIgnoreCase("/exit")) {
                    msg = new ChatMessage("exit", null);
                }
                else {
                    msg = new ChatMessage("chat", message);
                }
                output.println(mapper.writeValueAsString(msg));
            }
        } catch (IOException e) {
            System.out.println("Error reading message");
        }
    }
}
