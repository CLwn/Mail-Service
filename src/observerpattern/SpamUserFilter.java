package observerpattern;

import structure.Message;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Class SpamUserFilter
 * @author Marc García
 * @version 1.0
 */
public class SpamUserFilter extends Observer{
    /**
     * Method to get a list of messages that don't contain the word spam in their usernames
     * @param box mailbox
     * @return a List of messages
     */
    @Override
    public List<Message> update(List<Message> box) {
        return box.stream().filter(message -> message.getSender().contains("spam")).collect(Collectors.toList());
    }
}
