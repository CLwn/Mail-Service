package observerpattern;

import structure.Message;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Class TooLongFilter
 * @author Marc García
 * @version 1.0
 */
public class TooLongFilter extends Observer{

    /**
     * Method to filter a messages that have more than 20 characters
     * @param box mailbox
     * @return a List of messages
     */
    @Override
    public List<Message> update(List<Message> box) {
        return box.stream().filter(message -> message.getBody().codePoints()
                .mapToObj(Character::toString).collect(Collectors.counting())>20).collect(Collectors.toList());
    }

}
