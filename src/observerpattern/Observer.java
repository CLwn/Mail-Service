package observerpattern;

import structure.Message;

import java.util.List;

public abstract class Observer {
    public abstract List<Message> update(List<Message> box);
}
