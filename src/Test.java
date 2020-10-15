import utilities.*;

import java.io.IOException;
import java.util.Date;

public class Test {
    public static void main(String[] args){
        MailStore mail = new MailStoreFiles();
        User user1 = new User("pepe","pedro García",1991);
        User user2 = new User("juan","Juan Arcos",1979);

        Date date = new Date();
        Message msg = new Message(user1.getUsername(), user2.getUsername(), "Buenas Juan, como estás?", "Bienvenido!",date);

        try {
            mail.sendMail(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
