package ObserverPattern;

import Structure.Message;
import java.util.List;

public interface Observable {
    void attach(Observer o);
    void detach(Observer o);
    void notifyAllObservers();
}
