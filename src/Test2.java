import utilities.*;
import java.util.List;


public class Test2 {
    public static void main(String[] args) throws Exception {
        /**
         * create user funciona bien
         */
        MailSystem mailSystem = new MailSystem(new MailStoreMemory());
        Mailbox pepebox = mailSystem.createNewUser("pepe", "Pedro García", 1998);
        Mailbox antbox = mailSystem.createNewUser("ant", "Antonio Ramon", 1995);

        pepebox.sendMail(new Message("pepe", "ant","Como estas bro? espero que bien", "Saludos"));
        pepebox.sendMail(new Message("pepe", "ant", "Quiero saber si mi gestor email va bien", "Urgente"));
        pepebox.sendMail(new Message("pepe", "ant", "Funciona correctamente", "Todo Correcto"));
        antbox.updateMail("ant");

        /**
         * listea bien
         */
        List<Message> list = antbox.listMail();
        for (Message msg: list) System.out.println(msg.getBody());

        /**
         * muestra los usuarios bien
         */
        List<User> users = mailSystem.getAllUsers();
        for (User user: users) System.out.println(user.getUsername());

    }
}
