package Structure;

import ObserverPattern.Observable;
import ObserverPattern.Observer;
import ObserverPattern.SpamUserFilter;
import ObserverPattern.TooLongFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//els mailboxs han de ser iterables y sha d'aplicar decorator pattern?
public class Mailbox implements Observable {
    private String username;
    private List<Message> box = new LinkedList<>();
    private MailStore store;
    private List<Observer> filterList = new LinkedList<>();
    private List<Message> spam = new LinkedList<>();



    public Mailbox(MailStore store, String username){
        this.store = store;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void updateMail(String username) throws Exception { //utilitzar streams??
        box = store.getMail(username);
        notifyAllObservers();
    }
    //retorna la llista de correus q ha fer el update
    public List<Message> listMail(){
        return box;
    }

    // envia el missatge al mailstore
    public void sendMail(Message message) throws IOException {
        store.sendMail(message);
    }


    public void getMail(Comparator comparator){
        box.stream().sorted(comparator).forEach(System.out::println);
    }


    public List<Message> filterMail(Predicate predicate){
        List<Message> list = (List<Message>) box.stream().filter(predicate).collect(Collectors.toList());
        return list;
    }

    public void setStore(MailStore store) {
        this.store = store;
    }


    @Override
    public void attach(Observer o) {
        filterList.add(o);
    }

    @Override
    public void detach(Observer o) {
        filterList.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        List<Message> partial;
        for (Observer observer: filterList){
            System.out.println("hola");
            partial = observer.update(box);
            for (Message message: partial) System.out.println(message.toString());
            spam = Stream.concat(spam.stream(),partial.stream()).collect(Collectors.toList());
        }
        for (Message message: spam) box.remove(message);

    }

    public List<Message> getSpam() {
        return spam;
    }
}
