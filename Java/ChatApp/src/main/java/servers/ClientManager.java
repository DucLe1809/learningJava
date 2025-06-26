package servers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private static final ClientManager manager = new ClientManager();
    private final List<PrintWriter> clientOutputs = new ArrayList<>();

    private ClientManager() {}

    public static ClientManager getInstance() {
        return manager;
    }

    public void addClients(PrintWriter out) {
        synchronized (clientOutputs) {
            clientOutputs.add(out);
        }
    }

    public void removeClients(PrintWriter out) {
        synchronized (clientOutputs) {
            clientOutputs.remove(out);
        }
    }

    public void broadcast(PrintWriter sender, String senderName, String jsonMsg) {
        synchronized (clientOutputs) {
            if (!clientOutputs.contains(sender)) {
                System.out.println("Unknow client " + senderName);
            }

            for (PrintWriter client : clientOutputs) {
                client.println(jsonMsg);
            }
        }
    }
}
