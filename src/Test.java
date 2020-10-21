import utilities.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        MailStore mail = new MailStoreFiles();
        MailStore mail2 = new MailStoreMemory();

        List<Message> list;
        Mailbox pepebox = new Mailbox(mail2);
        Mailbox juanbox = new Mailbox(mail2);
        User user1 = new User("pepe", "pedro García", 1991, pepebox);
        User user2 = new User("juan", "Juan Arcos", 1979, juanbox);

        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message msg = new Message(user1.getUsername(), user2.getUsername(), "Buenas Juan, como estás?",
                "Bienvenido!");//, new Timestamp(System.currentTimeMillis()));
        Message msg2 = new Message(user1.getUsername(), user2.getUsername(), "segundo?",
                "Bienvenido!");//, new Timestamp(System.currentTimeMillis()));
        Message msg3 = new Message(user1.getUsername(), user2.getUsername(), "que haces con tu vida paias",
                "Bienvenido!");//, new Timestamp(System.currentTimeMillis()));

        user1.getBox().sendMail(msg);
        user1.getBox().sendMail(msg2);
        user2.getBox().updateMail(user2.getUsername());
        user2.getBox().listMail();
        /**
        mail2.sendMail(msg2);
        mail2.sendMail(msg);
        mail2.sendMail(msg3);
        list = mail2.getMail("juan");

         mail.sendMail(msg);
         mail.sendMail(msg2); //s'ha d'utilitzar el send mail del mailboxlist = mail.getMail("juan");
        for (Message message : list) {
            System.out.println(message.getSender());
            System.out.println(message.getReceiver());
            System.out.println(message.getSubject());
            System.out.println(message.getBody());
            System.out.println("--------------------------");
            //System.out.println(message.getTimestamp());
        }*/
    }
}
