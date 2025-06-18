package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class ClientSender implements Runnable {
    private final BufferedReader console;
    private final PrintWriter output;

    public ClientSender(BufferedReader console, PrintWriter output) {
        this.console = console;
        this.output = output;
    }

    public void run() {
        try {
            String message;
            while ((message = console.readLine()) != null) {
                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }
                output.println(message);
            }
        } catch (IOException e) {
            System.out.println("Error reading message");
        }
    }
}
