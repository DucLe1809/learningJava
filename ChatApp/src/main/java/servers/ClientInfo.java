package servers;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientInfo {
    private final String name;
    private final String ipAddress;

    public ClientInfo(String name, Socket socket) {
        this.name = name;
        this.ipAddress = socket.getInetAddress().getHostAddress();
    }

    // Getter function
    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
