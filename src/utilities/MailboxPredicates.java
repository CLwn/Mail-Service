package utilities;

import java.sql.Timestamp;
import java.util.function.Predicate;

public class MailboxPredicates {

    public static Predicate<Message> filterBySender(String sender){
        return message -> message.getSender().equalsIgnoreCase(sender);
    }

    public static Predicate<Message> filterBySubject(String subject){
        return  message -> message.getSubject().contains(subject);
    }

    public static Predicate<Message> filterByTimestamp(Timestamp timestamp){
        return message -> message.getTimestamp().equals(timestamp);
    }
}
