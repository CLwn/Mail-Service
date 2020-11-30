package Test;

import FactoryPattern.MailStoreMemoryFactory;
import ObserverPattern.SpamUserFilter;
import ObserverPattern.TooLongFilter;
import Structure.*;
import java.sql.Timestamp;

public class TestFiltersMain {
    public static void main(String[] args) throws java.lang.Exception {

        //Initialize mail system
        MailSystem mailSystem = new MailSystem();

        //Create new type of mailStore
        mailSystem.createMailStore(new MailStoreMemoryFactory());

        //Create users
        Mailbox joshbox = mailSystem.createNewUser("josh", "Josh", 1998);
        Mailbox david01box = mailSystem.createNewUser("david01", "David", 2001);
        Mailbox davidbox = mailSystem.createNewUser("david", "David", 1992);
        Mailbox spammerbox = mailSystem.createNewUser("spammer", "Mr.spam", 1988);

        //Send mails
        joshbox.sendMail(new Message(joshbox.getUsername(), david01box.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        joshbox.sendMail(new Message(joshbox.getUsername(), davidbox.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        david01box.sendMail(new Message(david01box.getUsername(), joshbox.getUsername(), "Urgent",
                "need access,into DB", new Timestamp(System.currentTimeMillis())));
        david01box.sendMail(new Message(david01box.getUsername(), davidbox.getUsername(), "Teammates",
                "Hi david, We are teammates", new Timestamp(System.currentTimeMillis())));
        davidbox.sendMail(new Message(davidbox.getUsername(), david01box.getUsername(), "Teammates",
                "Yes, We gonna work together", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), joshbox.getUsername(), "FREE VPN",
                "BUY NOW VPNFAST FOR 9,99$/MONTH", new Timestamp(System.currentTimeMillis())));
        davidbox.sendMail(new Message(davidbox.getUsername(), joshbox.getUsername(), "Welcome",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                        " Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, " +
                        "nascetur ridiculus mus.", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), david01box.getUsername(), "FREE VPN",
                "BUY NOW VPNFAST FOR 9,99$/MONTH", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), davidbox.getUsername(), "FREE VPN",
                "BUY NOW VPNFAST FOR 9,99$/MONTH", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), davidbox.getUsername(), "FREE VPN",
                "LINK!", new Timestamp(System.currentTimeMillis())));


        //Apply filters
        joshbox.attach(new TooLongFilter());
        joshbox.attach(new SpamUserFilter());
        david01box.attach(new SpamUserFilter());
        davidbox.attach(new SpamUserFilter());

        //Update messages list
        joshbox.updateMail(joshbox.getUsername());
        david01box.updateMail(david01box.getUsername());
        davidbox.updateMail(davidbox.getUsername());

        //List mails
        System.out.println("------------MAIL LIST---------------");
        System.out.println("------------josh's mail---------------");
        for (Message message: joshbox.listMail()) System.out.println(message.toString());
        System.out.println("------------david's mail---------------");
        for (Message message: davidbox.listMail()) System.out.println(message.toString());
        System.out.println("------------david01's mail---------------");
        for (Message message: david01box.listMail()) System.out.println(message.toString());

        //List spam
        System.out.println("------------SPAM LIST---------------");
        System.out.println("------------josh's mail---------------");
        for (Message message: joshbox.getSpam()) System.out.println(message.toString());
        System.out.println("------------david's mail---------------");
        for (Message message: davidbox.getSpam()) System.out.println(message.toString());
        System.out.println("------------david01's mail---------------");
        for (Message message: david01box.getSpam()) System.out.println(message.toString());

        //list spammers
        mailSystem.getSpammers();


        System.out.println("---------------------------CHANGE IMPLEMENTATION OF MAIL SYSTEM--------------------------");
        mailSystem.setMailStore(new MailStoreFiles());

        //Move users to new Store
        joshbox.setStore(mailSystem.getMailStore());
        david01box.setStore(mailSystem.getMailStore());
        davidbox.setStore(mailSystem.getMailStore());
        spammerbox.setStore(mailSystem.getMailStore());

        //Send mails
        joshbox.sendMail(new Message(joshbox.getUsername(), david01box.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        joshbox.sendMail(new Message(joshbox.getUsername(), davidbox.getUsername(), "Welcome",
                "Welcome to this department", new Timestamp(System.currentTimeMillis())));
        david01box.sendMail(new Message(david01box.getUsername(), joshbox.getUsername(), "Urgent",
                "need access,into DB", new Timestamp(System.currentTimeMillis())));
        david01box.sendMail(new Message(david01box.getUsername(), davidbox.getUsername(), "Teammates",
                "Hi david, We are teammates", new Timestamp(System.currentTimeMillis())));
        davidbox.sendMail(new Message(davidbox.getUsername(), david01box.getUsername(), "Teammates",
                "Yes, We gonna work together", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), joshbox.getUsername(), "FREE VPN",
                "BUY NOW VPNFAST FOR 9,99$/MONTH", new Timestamp(System.currentTimeMillis())));
        davidbox.sendMail(new Message(davidbox.getUsername(), joshbox.getUsername(), "Welcome",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                        " Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, " +
                        "nascetur ridiculus mus.", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), david01box.getUsername(), "FREE VPN",
                "BUY NOW VPNFAST FOR 9,99$/MONTH", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), davidbox.getUsername(), "FREE VPN",
                "BUY NOW VPNFAST FOR 9,99$/MONTH", new Timestamp(System.currentTimeMillis())));
        spammerbox.sendMail(new Message(spammerbox.getUsername(), davidbox.getUsername(), "FREE VPN",
                "LINK!", new Timestamp(System.currentTimeMillis())));


        //Apply filters
        joshbox.attach(new TooLongFilter());
        joshbox.attach(new SpamUserFilter());
        david01box.attach(new SpamUserFilter());
        davidbox.attach(new SpamUserFilter());

        //Update messages list
        joshbox.updateMail(joshbox.getUsername());
        david01box.updateMail(david01box.getUsername());
        davidbox.updateMail(davidbox.getUsername());

        //List mails
        System.out.println("------------MAIL LIST---------------");
        System.out.println("------------josh's mail---------------");
        for (Message message: joshbox.listMail()) System.out.println(message.toString());
        System.out.println("------------david's mail---------------");
        for (Message message: davidbox.listMail()) System.out.println(message.toString());
        System.out.println("------------david01's mail---------------");
        for (Message message: david01box.listMail()) System.out.println(message.toString());

        //List spam
        System.out.println("------------SPAM LIST---------------");
        System.out.println("------------josh's mail---------------");
        for (Message message: joshbox.getSpam()) System.out.println(message.toString());
        System.out.println("------------david's mail---------------");
        for (Message message: davidbox.getSpam()) System.out.println(message.toString());
        System.out.println("------------david01's mail---------------");
        for (Message message: david01box.getSpam()) System.out.println(message.toString());

        //list spammers
        mailSystem.getSpammers();

    }
}
