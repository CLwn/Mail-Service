package structure;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Message
 * @author Marc García
 * @version 1.0
 */
public class Message {
    private String sender, receiver;
    private String body, subject;
    private Timestamp timestamp;

    /**
     * Constructor with 5 parameters
     * @param sender username of user
     * @param receiver username of user
     * @param subject title of mail
     * @param body message's body
     * @param timestamp date of mail
     */
    public Message(String sender, String receiver, String subject, String body, Timestamp timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.subject = subject;
        this.timestamp = timestamp;
    }

    /**
     * Method to get a sender
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Method to get a receiver
     * @return receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Method to get a body
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * Method to modify a body
     * @param body message's body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Method to get a subject
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Method to get a timestamp
     * @return timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Method to modify a timestamp
     * @param timestamp timestamp object
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Method to print information about instances from this class
     * @return String
     */
    @Override
    public String toString() {
        return "-------Message----------" +"\n"+
                "sender: " + sender + "\n" +
                "receiver: " + receiver + "\n" +
                "subject: " +subject + "\n" +
                body + "\n" +
                timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) && Objects.equals(receiver, message.receiver) && Objects.equals(body, message.body) && Objects.equals(subject, message.subject);// && Objects.equals(timestamp, message.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, body, subject, timestamp);
    }
}
