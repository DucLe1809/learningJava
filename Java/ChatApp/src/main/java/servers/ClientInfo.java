package servers;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientInfo {
    private final String name;
    private final String ipAddress;

    public ClientInfo(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }

    // Getter function
    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
