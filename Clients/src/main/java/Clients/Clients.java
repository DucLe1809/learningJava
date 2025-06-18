package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Clients {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        System.out.println("Connected to server");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Message received from Server
        Thread receiveThread = new Thread(() -> {
            try {
                String fromServer;
                while ((fromServer = input.readLine()) != null) {
                    System.out.println("[Hệ thống]: " + fromServer);
                }
            } catch (IOException e) {
                System.out.println("Server closed connection");
            }
        });

        receiveThread.start();

        System.out.print("Enter your name: ");
        String name = console.readLine();
        // Send the name to serverSocket
        output.println(name);

        String fromUser;
        while ((fromUser = console.readLine()) != null) {
            if (fromUser.equalsIgnoreCase("/exit")) {
                break;
            }
            output.println(fromUser);
        }
        socket.close();
    }
}
