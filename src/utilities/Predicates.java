package utilities;

import structure.Message;
import structure.User;

import java.sql.Timestamp;
import java.util.function.Predicate;

public class Predicates {

    public static Predicate<Message> filterBySender(String sender){
        return message -> message.getSender().equalsIgnoreCase(sender);
    }

    public static Predicate<Message> filterBySubject(String subject){
        return  message -> message.getSubject().contains(subject);
    }

    public static Predicate<Message>filterSubjectSingleWord(String word){
        return message -> message.getSubject().equalsIgnoreCase(word);
    }

    public static Predicate<Message> filterByTimestamp(Timestamp timestamp){
        return message -> message.getTimestamp().equals(timestamp);
    }

    public static Predicate<User> filterByAge(int year){
        return  user -> user.getYearOfBirth()>year;
    }

    public static Predicate<Message> filterWordBody(String word){
        return message -> message.getBody().contains(word);
    }

}
