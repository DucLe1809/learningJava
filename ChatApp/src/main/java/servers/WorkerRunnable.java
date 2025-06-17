package servers;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class WorkerRunnable implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText = null;
    private   BufferedReader input;
    private   PrintWriter output;
    private   List<WorkerRunnable> clients;

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
            clients.add(this); // add self to client list
            String msg;
            while ((msg = input.readLine()) != null) {
                System.out.println("Received: " + msg);
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
            if (client != this) {
                client.output.println(message);
            }
        }
    }
}

