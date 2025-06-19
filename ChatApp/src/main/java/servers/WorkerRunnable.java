package servers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class WorkerRunnable implements Runnable {
    protected Socket clientSocket;
    private   PrintWriter output;
    private   BufferedReader input;

    private final ObjectMapper mapper = new ObjectMapper();

    // Constructor
    public WorkerRunnable(Socket clientsocket) {
        this.clientSocket = clientsocket;
        try {
            this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error setting up streams");
        }
    }

    @Override
    public void run() {
        try {
            // Adding the name for client
            String rawMsg = input.readLine();
            ChatMessage loginMsg = mapper.readValue(rawMsg, ChatMessage.class);

            String clientName = "Unknown";
            if ("login".equalsIgnoreCase(loginMsg.cmd)) {
                clientName = loginMsg.content;
            }

            System.out.println(clientName + " connect to the server.");
            ClientManager.getInstance().addClients(output);

            new ClientInfo(clientName, clientSocket.getInetAddress().getHostAddress());

            String jsonMsg;
            while ((jsonMsg = input.readLine()) != null) {
                ChatMessage msg = mapper.readValue(jsonMsg, ChatMessage.class);
                switch (msg.cmd) {
                    case "chat":
                        System.out.println("[" + clientName + "] " + msg.content);
                        ClientManager.getInstance().broadcast(output, clientName, msg.content);
                        break;
                    case "exit":
                        return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ClientManager.getInstance().removeClients(output);
            System.out.println("Clients disconnected.");
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket.");
            }
        }
    }
}

