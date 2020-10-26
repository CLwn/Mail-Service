import utilities.*;

import java.sql.Timestamp;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        MailSystem mailSystem = new MailSystem(new MailStoreFiles());
        Mailbox pepebox = mailSystem.createNewUser("pepe", "Pedro García", 1998);
        Mailbox antbox = mailSystem.createNewUser("ant", "Antonio Ramon", 1995);

        antbox.sendMail(new Message("ant", "pepe", "Callaitos", "Paias", new Timestamp(System.currentTimeMillis())));
        antbox.sendMail(new Message("ant", "pepe", "Callaitos2222", "Paias222s", new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "ant","Como estas bro? espero que bien", "Saludos", new Timestamp(System.currentTimeMillis())));
        //Thread.sleep(4000);
        pepebox.sendMail(new Message("pepe", "ant", "Quiero saber si mi gestor email va bien", "Urgente", new Timestamp(System.currentTimeMillis())));
        pepebox.sendMail(new Message("pepe", "ant", "Funciona correctamente", "Correcto", new Timestamp(System.currentTimeMillis())));
        antbox.updateMail("ant");
        pepebox.updateMail("pepe");

        /**
         * El timestamp al storeMemory funciona, pero al StoreFiles a medias i think
         */
        List<Message> list = antbox.listMail();
        for (Message msg: list){
            //System.out.println(msg.getSender());
            //System.out.println(msg.getReceiver());
            //System.out.println(msg.getSubject());
            System.out.println(msg.getTimestamp());
        }

        /**
         * listea bien
         */
        List<Message> list2 = antbox.listMail();
        for (Message msg: list2) System.out.println(msg.getBody());

        /**
         * muestra los usuarios bien
         */
        List<User> users = mailSystem.getAllUsers();
        for (User user: users) System.out.println(user.getUsername());

        /**
         * muestra todoss los mensajes, pierde un mensaje en cada run
         */
        //List<Message> totalmsg = mailSystem.getAllMessage();
        //for (Message msg: totalmsg) System.out.println(msg.getSubject());


        mailSystem.countMessages();
    }
}
