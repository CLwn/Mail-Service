import utilities.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * Initialize mail system
         */
        MailSystem mailSystem = new MailSystem(new MailStoreMemory());

        /**
         * Create users
         */
        Mailbox joshbox = mailSystem.createNewUser("josh", "Josh Hammond", 1998);
        Mailbox salombox = mailSystem.createNewUser("salom", "David Salom", 1996);
        Mailbox davidbox = mailSystem.createNewUser("david", "David Arqués", 1992);

        /**
         * Send mails
         */
        joshbox.sendMail(new Message(joshbox.getUsername(), salombox.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        joshbox.sendMail(new Message(joshbox.getUsername(), davidbox.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        salombox.sendMail(new Message(salombox.getUsername(), joshbox.getUsername(), "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        salombox.sendMail(new Message(salombox.getUsername(), davidbox.getUsername(), "Teammates",
                "Hi david, We're teammates", new Timestamp(System.currentTimeMillis())));

        /**
         * Update list
         */
        davidbox.updateMail(davidbox.getUsername());

        /**
         * List messages
         * i have to do this with get  mail sorted, point 4
         */
        for (Message msg : davidbox.listMail()){
            System.out.println();
        }


    }
}
