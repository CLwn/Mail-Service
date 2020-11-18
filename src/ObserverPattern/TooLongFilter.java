package ObserverPattern;

import Structure.Message;
import java.util.List;
import java.util.stream.Collectors;

public class TooLongFilter extends Observer{

    @Override
    public List<Message> update(List<Message> box) {
        return box.stream().filter(message -> message.getBody().codePoints()
                .mapToObj(Character::toString).collect(Collectors.counting())>20).collect(Collectors.toList());
    }

}
