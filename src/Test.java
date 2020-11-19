import ObserverPattern.SpamUserFilter;
import ObserverPattern.TooLongFilter;
import Structure.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws Exception, java.lang.Exception {
        MailSystem mailSystem = new MailSystem(new MailStoreFiles());
        Mailbox pepebox = mailSystem.createNewUser("pepe", "Pedro García", 1998);
        Mailbox antbox = mailSystem.createNewUser("ant", "Antonio Ramon", 1995);

        antbox.sendMail(new Message("ant", "pepe", "Callaitos", "Paias", new Timestamp(System.currentTimeMillis())));
        antbox.sendMail(new Message("spam", "pepe", "Calla", "Paias", new Timestamp(System.currentTimeMillis())));
        antbox.sendMail(new Message("ant", "pepe", "Calla2", "12345678912345678900wsdasdasfqww", new Timestamp(System.currentTimeMillis())));
        antbox.sendMail(new Message("ant", "pepe", "Callaitos2222", "Paias222s", new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "ant","Como estas bro? espero que bien", "Saludos", new Timestamp(System.currentTimeMillis())));
        //Thread.sleep(4000);
        pepebox.sendMail(new Message("pepe", "ant", "Quiero saber si mi gestor email va bien", "Urgente", new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "ant", "Funciona correctamente", "Correcto", new Timestamp(System.currentTimeMillis())));
        antbox.updateMail("ant");

        pepebox.attach(new SpamUserFilter());
        pepebox.attach(new TooLongFilter());
        pepebox.updateMail("pepe");
        System.out.println("------------------------------LIST--------------------------------");
        for (Message message: pepebox.listMail()) System.out.println(message.toString());

        System.out.println("------------------------------SPAM------------------------------");
        /**for (Message message: pepebox.getSpam()) System.out.println(message.toString());
        List<String> spamUsers = new LinkedList<>();
        for (Mailbox mailbox: mailSystem.getMailboxList()){
            for (Message message: mailbox.getSpam()){
                if (!spamUsers.contains(message.getSender())) spamUsers.add(message.getSender());
            }
        }
        for (String spammers: spamUsers) System.out.println("this guy's spammer: "+spammers);

*/
        mailSystem.getSpammers();
        /**
         * El timestamp al storeMemory funciona, pero al StoreFiles a medias i think
         *
        List<Message> list = antbox.listMail();
        for (Message msg: list) System.out.println(msg.toString());

        /**
         * listea bien
         *
        List<Message> list2 = antbox.listMail();
        for (Message msg: list2) System.out.println(msg.getBody());

        /**
         * muestra los usuarios bien
         *
        List<User> users = mailSystem.getAllUsers();
        for (User user: users) System.out.println(user.getUsername());

        /**
         * muestra todoss los mensajes, pierde un mensaje en cada run
         *
        //List<Message> totalmsg = mailSystem.getAllMessage();
        //for (Message msg: totalmsg) System.out.println(msg.getSubject());

        /**
         * Cuenta todos los mensajes de todos los usuarios correctamente. Cuenta el total
         *
        mailSystem.countMessages();

        /**
         * Hace correctamente el promedio de mensajes por usuario
         *
        mailSystem.averageMessagesPerUser();

        /**
         * Words count funcionando correctamente
         *
        mailSystem.countWords("ant");

        /**
         * funciona el coger mensajes de usario de edades superiores
         *
        for (Message messg : mailSystem.getMessageByYO(1996)) System.out.println(messg.getReceiver());
*/

    }
}
