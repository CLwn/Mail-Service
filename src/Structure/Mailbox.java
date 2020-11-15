package Structure;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//els mailboxs han de ser iterables y sha d'aplicar decorator pattern?
public class Mailbox{
    private String username;
    private List<Message> box = new LinkedList<>();
    private MailStore store;

    public Mailbox(MailStore store, String username){
        this.store = store;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void updateMail(String username) throws Exception { //utilitzar streams??
        box = store.getMail(username);
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

    public void setUsername(String username) {
        this.username = username;
    }

}
