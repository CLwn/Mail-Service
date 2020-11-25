import DecoratorPattern.CipherCode;
import DecoratorPattern.ReverseCode;
import Structure.*;
import java.sql.Timestamp;

import java.util.List;
import static utilities.Predicates.*;
import static utilities.Comparators.*;

public class Test2 {
    public static void main(String[] args) throws java.lang.Exception {
        /**
         * create user funciona bien
         */
        //MailSystem mailSystem = new MailSystem(new ReverseCode(new MailStoreFiles()));
        //TODO teoricament haig de poder fer això.
        MailSystem mailSystem= new MailSystem(new ReverseCode(new CipherCode(new MailStoreFiles())));
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

       // antbox.updateMail("ant");
        santibox.updateMail("santi");
        for (Message message: antbox.listMail()) System.out.println(message.toString());

    }
}
