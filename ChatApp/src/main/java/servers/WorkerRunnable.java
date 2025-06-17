package servers;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class WorkerRunnable implements Runnable {
    protected Socket clientSocket;
    protected String serverText;
    private String clientName;
    private   BufferedReader input;
    private   PrintWriter output;
    private   List<WorkerRunnable> clients;

    // Constructor
    public WorkerRunnable(Socket clientsocket, String serverText, List<WorkerRunnable> clients) {
        this.clientSocket = clientsocket;
        this.serverText = serverText;
        this.clients = clients;
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

            clients.add(this); // add self to client list
            System.out.println(clientName + " connect to the server.");

            String msg;
            while ((msg = input.readLine()) != null) {
                System.out.println(clientName + " : " + msg);
                broadcast(msg);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } finally {
            clients.remove(this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket.");
            }
        }
    }
    private void broadcast(String message) {
        for (WorkerRunnable client : clients) {
                client.output.println(clientName + " : " + message);
        }
    }
}

