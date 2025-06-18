package servers;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class WorkerRunnable implements Runnable {
    protected Socket clientSocket;
    private   String clientName;
    private   PrintWriter output;
    private   BufferedReader input;

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
            clientName = input.readLine();
            if (clientName == null || clientName.trim().isEmpty()) {
                clientName = "Ẩn danh";
            }

            System.out.println(clientName + " connect to the server.");
            ClientManager.getInstance().addClients(output);
            new ClientInfo(clientName, clientSocket);

            String msg;
            while ((msg = input.readLine()) != null) {
                System.out.println("[" + clientName + "] " + msg );
                ClientManager.getInstance().broadcast(output, clientName, msg);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } finally {
            ClientManager.getInstance().removeClients(output);
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket.");
            }
        }
    }
}

