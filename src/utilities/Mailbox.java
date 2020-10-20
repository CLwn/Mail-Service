package utilities;

import java.util.List;

//els mailboxs han de ser iterables y sha d'aplicar decorator pattern?
public class Mailbox {
    private List<Message> box;

    public void updateMail(){ //utilitzar streams??

    }

    public void listMail(){} //retorna la llista de correus q ha fer el update

    public void sendMail(){} // envia el missatge al mailstore

    public void getMail(){} //li hem de passar un mètode de ordenació per paràmetre

    public void filterMail(){}
}
