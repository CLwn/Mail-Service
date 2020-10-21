package utilities;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//els mailboxs han de ser iterables y sha d'aplicar decorator pattern?
public class Mailbox {
    private List<Message> box = new LinkedList<>();
    private MailStore store;

    public Mailbox(MailStore store) {
        this.store = store;
    }

    public void updateMail(String username) throws Exception { //utilitzar streams??
        box = store.getMail(username);
    }
    //retorna la llista de correus q ha fer el update
    public void listMail(){
        for(Message m: box){
            System.out.println(m.getSubject());
        }
    }

    // envia el missatge al mailstore
    public void sendMail(Message message) throws IOException {
        store.sendMail(message);
    }

    public void getMail(){} //li hem de passar un mètode de ordenació per paràmetre

    public void filterMail(){}

    public List<Message> getBox() {
        return box;
    }

    public void setBox(List<Message> box) {
        this.box = box;
    }
}
