import utilities.*;
import java.sql.Timestamp;
import static utilities.MailboxPredicates.*;

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
        Mailbox salombox = mailSystem.createNewUser("salom", "Artur Salom", 1996);
        Mailbox davidbox = mailSystem.createNewUser("david", "David Arqués", 1992);

        /**
         * Send mails
         */
        joshbox.sendMail(new Message(joshbox.getUsername(), salombox.getUsername(), "Welcome Artur",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        joshbox.sendMail(new Message(joshbox.getUsername(), davidbox.getUsername(), "Welcome David",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        salombox.sendMail(new Message(salombox.getUsername(), joshbox.getUsername(), "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        salombox.sendMail(new Message(salombox.getUsername(), davidbox.getUsername(), "Teammates",
                "Hi david, We're teammates", new Timestamp(System.currentTimeMillis())));

        /**
         * Update list
         */
        davidbox.updateMail(davidbox.getUsername());

        System.out.println("-----------------------------------------------------");
        /**
         * List messages
         * i have to do this with get  mail sorted, point 4
         */
        //TODO
        for (Message msg : davidbox.listMail()){
            System.out.println(msg.getSubject());
        }

        System.out.println("-----------------------FILTER BY SUBJECT------------------------------");
        /**
         * filter the messages by subject
         */
        for (Message message: davidbox.filterMail(filterBySubject("Welcome"))){
            System.out.println("----Message----");
            System.out.println("Sender: "+message.getSender());
            System.out.println("Receiver: "+message.getReceiver());
            System.out.println("Subject: "+message.getSubject());
            System.out.println(message.getBody());
            System.out.println(message.getTimestamp());
        }

        System.out.println("-----------------------FILTER BY SENDER------------------------------");

        /**
         * filter the messages by sender
         */
        for (Message message: davidbox.filterMail(filterBySender(salombox.getUsername()))){
            System.out.println("----Message----");
            System.out.println("Sender: "+message.getSender());
            System.out.println("Receiver: "+message.getReceiver());
            System.out.println("Subject: "+message.getSubject());
            System.out.println(message.getBody());
            System.out.println(message.getTimestamp());
        }

        System.out.println("-------------------------All MESSAGES----------------------------");

        /**
         * retrieve all messages with mail System
         */
        for (Message message: mailSystem.getAllMessage()){
            System.out.println("----Message----");
            System.out.println("Sender: "+message.getSender());
            System.out.println("Receiver: "+message.getReceiver());
            System.out.println("Subject: "+message.getSubject());
            System.out.println(message.getBody());
            System.out.println(message.getTimestamp());
        }

        System.out.println("-------------------------COUNT MESSAGES----------------------------");
        /**
         * Count messages in the system
         */
        mailSystem.countMessages();


        System.out.println("-------------------------AVERAGE MESSAGES PER USER----------------------------");
        /**
         * Average messages per user
         */
        mailSystem.averageMessagesPerUser();

    }
}
