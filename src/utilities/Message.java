package utilities;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private String sender, receiver; //we have to use their usernames
    private String body, subject;
    private LocalDateTime timestamp; //message creation

    public Message(String sender, String receiver, String body, String subject, LocalDateTime timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.subject = subject;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
