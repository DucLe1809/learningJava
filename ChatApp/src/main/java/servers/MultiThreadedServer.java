package servers;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.*;

public class MultiThreadedServer implements Runnable {
    protected int           serverPort = 9000;
    protected ServerSocket  serverSocket = null;
    protected boolean       isStopped = false;
    protected Thread        runningThread = null;

    protected List<WorkerRunnable> clients = Collections.synchronizedList(new ArrayList<>());


    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized(this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (! isStopped)
        {
            Socket clientsocket = null;
            try {
                clientsocket = serverSocket.accept();
            }
            catch (IOException e)
            {
                if (isStopped) {
                    System.out.println("Server stopped");
                    return;
                }
                throw new RuntimeException("Error accepting client socket", e);
            }
        new Thread(
                new WorkerRunnable(
                        clientsocket, "Multithreaded server", clients)
        ).start();
        }
        System.out.println("Server stopped");
    }

    public void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    private void openServerSocket() {
        try  {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Error opening server socket", e);
        }
    }
}
