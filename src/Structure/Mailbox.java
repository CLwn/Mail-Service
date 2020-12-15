package Structure;

import ObserverPattern.Observable;
import ObserverPattern.Observer;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Mailbox
 * @author Marc García
 * @version 1.0
 */
public class Mailbox implements Observable {
    private String username;
    private List<Message> box = new LinkedList<>();
    private MailStore store;
    private List<Observer> filterList = new LinkedList<>();
    private List<Message> spam = new LinkedList<>();


    /**
     *Constructor with 2 parameters
     * @param store
     * @param username
     */
    public Mailbox(MailStore store, String username){
        this.store = store;
        this.username = username;
    }

    /**
     * Method to get Username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to get a message's list from mail store
     * @param username
     * @throws Exception
     */
    public void updateMail(String username) throws Exception {
        box = store.getMail(username);
        notifyAllObservers();
    }


    /**
     * Method to get a messages list from this mailbox
     * @return messages list
     */
    public List<Message> listMail(){
        return box;
    }

    /**
     * Method to send a message to mailstore and save it
     * @param message
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public void sendMail(Message message) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {
        store.sendMail(message);
    }

    /**
     * Method to get a messages list sorted
     * @param comparator
     */
    public void getMail(Comparator comparator){
        box.stream().sorted(comparator).forEach(System.out::println);
    }

    /**
     * Method to get a messages list filtered
     * @param predicate
     * @return
     */
    public List<Message> filterMail(Predicate predicate){
        List<Message> list = (List<Message>) box.stream().filter(predicate).collect(Collectors.toList());
        return list;
    }

    /**
     * Method to modify mailstore
     * @param store
     */
    public void setStore(MailStore store) {
        this.store = store;
    }

    /**
     * Method to attach new filters
     * @param o object observer
     */
    @Override
    public void attach(Observer o) {
        filterList.add(o);
    }

    /**
     * Method to detach filters
     * @param o object observer
     */
    @Override
    public void detach(Observer o) {
        filterList.remove(o);
    }

    /**
     * Method to notify all observers
     */
    @Override
    public void notifyAllObservers() {
        List<Message> partial;
        for (Observer observer: filterList){
            partial = observer.update(box);
            spam = Stream.concat(spam.stream(),partial.stream()).collect(Collectors.toList());
        }
        for (Message message: spam) box.remove(message);

    }

    /**
     * Method to get a messages list considered spam
     * @return messages list
     */
    public List<Message> getSpam() {
        return spam;
    }


}
