package servers;

import java.io.IOException;

public class ChatServer {

    public static void main(String[] args) {
        MultiThreadedServer threadedServer = new MultiThreadedServer(9000);
        new Thread(threadedServer).start();
    }
}
