import Structure.*;

import java.sql.Timestamp;

import java.util.List;
import static utilities.Predicates.*;
import static utilities.Comparators.*;

public class Test2 {
    public static void main(String[] args) throws Exception {
        /**
         * create user funciona bien
         */
        MailSystem mailSystem = new MailSystem(new MailStoreMemory());
        Mailbox pepebox = mailSystem.createNewUser("pepe", "Pedro García", 1998);
        Mailbox antbox = mailSystem.createNewUser("ant", "Antonio Ramon", 1995);
        Mailbox santibox = mailSystem.createNewUser("santi", "Santiago Barrales", 1992);

        pepebox.sendMail(new Message("pepe", "ant","Buenas Como estas bro? espero que bien", "Saludos",new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "ant", "Quiero saber si mi gestor email va bien", "Urgente",new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "ant", "Funciona correctamente", "Todo Correcto",new Timestamp(System.currentTimeMillis())));
        antbox.sendMail(new Message("ant", "pepe", "Callaitos", "Paias",new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "santi", "try 4", "Urgente",new Timestamp(System.currentTimeMillis())));
        Thread.sleep(4000);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        santibox.sendMail(new Message("santi", "ant", "Buenas soy santiago", "Paias",time));

        Thread.sleep(4000);
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        antbox.sendMail(new Message("ant", "santi", "buenas santi bienvenido", "bienvenida",time2));

        antbox.updateMail("ant");
        santibox.updateMail("santi");

        /**
         * listea bien
         */
        List<Message> list = santibox.listMail();
        for (Message msg: list) System.out.println(msg.getBody());

        /**
         * muestra los usuarios bien
         */
        List<User> users = mailSystem.getAllUsers();
        for (User user: users) System.out.println(user.getUsername());

        /**
         * muestra todoss los mensajes
         */
        //List<Message> totalmsg = mailSystem.getAllMessage();
        //for (Message msg: totalmsg) System.out.println(msg.getSubject());

        /**
         * muestra correctamente todos los mensajes
         */

        mailSystem.countMessages();

        /**
         * Hace correctamente el promedio de mensajes por usuario
         */
        mailSystem.averageMessagesPerUser();

        mailSystem.groupMessagePerSubject("pepe");

        /**
         * Word count funcionando correctamente
         */
        mailSystem.countWords("santi");

        /**
         * GET message by year done
         */
        for (Message messg : mailSystem.getMessageByYO(1996)) System.out.println(messg.getReceiver());

        /**Filter
         * use predicate
         */
        System.out.println(time);
        System.out.println(time2);
        System.out.println("-----------------------------------------------");
        for (Message message: antbox.filterMail(filterBySubject("Buenas"))) System.out.println(message.getSender());

        System.out.println("----------------------EL NUEVO TRY-----------------------------");

        antbox.getMail(orderBySender());

        mailSystem.getMailboxList().forEach(mailbox -> System.out.println(mailbox));

        System.out.println("----------------------Filtrar todos los mensajes-----------------------------");

        for (Message message: mailSystem.filterAllMessage(filterBySender("pepe"))) System.out.println(message.toString());;
    }
}
