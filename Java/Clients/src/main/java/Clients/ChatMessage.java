package Clients;

public class ChatMessage {
    public String cmd;
    public String content;

    public ChatMessage() {}

    public ChatMessage(String cmd, String content) {
        this.cmd = cmd;
        this.content = content;
    }
}
