package Solutions.uebung_04.a_04_messaging.serverPack;

import java.util.Date;
import java.util.UUID;

public class Message {
    private String id;
    private String text;
    private Date timeSent;
    private String senderUsername;
    private String receiverUsername;

    public Message(String text, String senderUsername, String receiverUsername) {
        this.text = text;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.id = UUID.randomUUID().toString();
        this.timeSent = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id + "#" + senderUsername + "#" + receiverUsername + "#" +  text + "#" + timeSent;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }
}
