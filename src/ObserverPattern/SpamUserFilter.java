package ObserverPattern;

import Structure.Message;
import java.util.List;
import java.util.stream.Collectors;

public class SpamUserFilter extends Observer{

    @Override
    public List<Message> update(List<Message> box) {
        return box.stream().filter(message -> message.getSender().contains("spam")).collect(Collectors.toList());
    }
}
