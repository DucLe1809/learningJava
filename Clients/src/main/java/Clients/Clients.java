package Clients;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.io.*;

public class Clients {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 9000);
        System.out.println("Connected to server");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        ObjectMapper mapper = new ObjectMapper();

        Thread receiver = new Thread(new ClientReceiver(input));
        receiver.start();

        System.out.print("Enter your name: ");
        String name = console.readLine();
        output.println(mapper.writeValueAsString(new ChatMessage("login", name)));

        Thread sender = new Thread(new ClientSender(console, output));
        sender.start();

        sender.join();
        receiver.join();

        socket.close();
    }
}
