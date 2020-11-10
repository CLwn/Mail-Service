import Structure.MailStoreMemory;
import Structure.MailSystem;
import Structure.Mailbox;
import Structure.Message;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static utilities.Predicates.*;
import static utilities.Comparators.*;

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
        Mailbox salombox = mailSystem.createNewUser("salom", "Artur Salom", 2001);
        Mailbox davidbox = mailSystem.createNewUser("david", "David Arqués", 1992);

        /**
         * Send mails
         */
        joshbox.sendMail(new Message(joshbox.getUsername(), salombox.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        Thread.sleep(1000);
        joshbox.sendMail(new Message(joshbox.getUsername(), davidbox.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        Thread.sleep(1000);
        salombox.sendMail(new Message(salombox.getUsername(), joshbox.getUsername(), "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        Thread.sleep(1000);
        salombox.sendMail(new Message(salombox.getUsername(), davidbox.getUsername(), "Teammates",
                "Hi david, We're teammates", new Timestamp(System.currentTimeMillis())));

        /**
         * Update list
         */
        davidbox.updateMail(davidbox.getUsername());

        System.out.println("---------------------------ORDER BY TIMESTAMP--------------------------");
        /**
         * List messages by newer first
         */
        davidbox.getMail(orderByTimestamp());

        System.out.println("---------------------------ORDER BY USERNAME SENDER--------------------------");
        /**
         * List messages by username sender
         */
        davidbox.getMail(orderBySender());

        System.out.println("-----------------------FILTER BY SUBJECT------------------------------");
        /**
         * filter the messages by subject
         */
        for (Message message: davidbox.filterMail(filterBySubject("Welcome"))){
            System.out.println(message.toString());
        }

        System.out.println("-----------------------FILTER BY SENDER------------------------------");

        /**
         * filter the messages by sender
         */
        for (Message message: davidbox.filterMail(filterBySender(salombox.getUsername()))){
            System.out.println(message.toString());
        }

        System.out.println("-------------------------All MESSAGES----------------------------");

        /**
         * retrieve all messages with mail System
         */
        for (Message message: mailSystem.getAllMessage()){
            System.out.println(message.toString());
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

        System.out.println("-------------------------FILTER BY SINGLE WORD----------------------------");
        /**
         * Filter by single word
         */
        for (Message message: mailSystem.filterAllMessage(filterSubjectSingleWord("urgent")))
            System.out.println(message.toString());


        System.out.println("-------------------------FILTER BY YEAR----------------------------");
        /**
         * filter messages where sender was born after 2000
         */
        for(Message message: mailSystem.getMessageByYO(filterByAge(2000)))System.out.println(message.toString());


        System.out.println("---------------------------FILTER BY YEAR BEFORE 2000--------------------------");
        for (Message message: mailSystem.getMessageByYO(2000)) System.out.println(message.toString());

        System.out.println("---------------------------GROUP MESSSAGES BY SUBJECT--------------------------");
        Map<String, List<Message>> map = mailSystem.groupMessagePerSubject();
        map.forEach((s, messages) -> System.out.println(messages.toString()));
    }
}
