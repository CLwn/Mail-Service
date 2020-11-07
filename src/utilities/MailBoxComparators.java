package utilities;

import Structure.Message;

import java.util.Comparator;

public class MailBoxComparators {

    public static Comparator<Message> orderBySender(){
        return Comparator.comparing(Message::getSender);
    }

    public static Comparator<Message> orderBySubject(){
        return Comparator.comparing(Message::getSubject);
    }

    public static Comparator<Message> orderByTimestamp(){
        return Comparator.comparing(Message::getTimestamp);
    }
}
