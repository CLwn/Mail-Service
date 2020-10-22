package utilities;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//els mailboxs han de ser iterables y sha d'aplicar decorator pattern?
public class Mailbox{
    private List<Message> box = new LinkedList<>();
    private MailStore store;

    public Mailbox(MailStore store) {
        this.store = store;
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

    //li hem de passar un mètode de ordenació per paràmetre STREAMS
    public void getMail(String value){

    }

    //filtramos por Sender
    public List<Message> filterMail(String value){
        List<Message> filter = box.stream().filter(x->value.equals(x.getSender())).collect(Collectors.toList());
        return filter;
    }

}
