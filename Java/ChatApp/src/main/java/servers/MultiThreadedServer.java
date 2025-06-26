package servers;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadedServer implements Runnable {

    protected int           serverPort;
    protected ServerSocket  serverSocket = null;
    protected boolean       isStopped = false;
    protected Thread        runningThread = null;
    private final List<Thread> clientThreads = new ArrayList<>();
    private final List<WorkerRunnable> workerRunnables = new ArrayList<>();

    // Constructor
    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized(this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (! isStopped) {
            Socket clientsocket;
            try {
                clientsocket = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("Server stopped");
                    return;
                }
                throw new RuntimeException("Error accepting client socket", e);
            }

            WorkerRunnable worker = new WorkerRunnable(clientsocket, this);
            Thread clientThread = new Thread(
                worker
            );

            addThread(clientThread);
            addWorker(worker);

            clientThread.start();
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
    private void addThread(Thread thread) {
        synchronized (this) {
            this.clientThreads.add(thread);
        }

    }

    private void addWorker(WorkerRunnable worker) {
        synchronized (this) {
            this.workerRunnables.add(worker);
        }

    }

    private void openServerSocket() {
        try  {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Error opening server socket", e);
        }
    }

    public void removeClientsSocket(WorkerRunnable worker) {
        synchronized (this) {
            int index = workerRunnables.indexOf(worker);
            if (index >= 0) {
                workerRunnables.remove(index);
                clientThreads.remove(index);
            }
        }
    }
}
