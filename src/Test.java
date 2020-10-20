import utilities.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        MailStore mail = new MailStoreFiles();
        List<Message> list;
        Mailbox pepebox = new Mailbox();
        Mailbox juanbox = new Mailbox();
        User user1 = new User("pepe","pedro García",1991, pepebox);
        User user2 = new User("juan","Juan Arcos",1979,juanbox);

        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        Message msg = new Message(user1.getUsername(), user2.getUsername(), "Buenas Juan, como estás?", "Bienvenido!",date);

        mail.sendMail(msg); //s'ha d'utilitzar el send mail del mailbox
        list = mail.getMail("pepe");
        for (Message message: list){
            System.out.println(message.getSender());
            System.out.println(message.getReceiver());
            System.out.println(message.getSubject());
            System.out.println(message.getBody());
        }
    }
}
