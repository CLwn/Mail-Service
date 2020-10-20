package utilities;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class MailStoreFiles implements MailStore {

    @Override
    public void sendMail(Message message) throws IOException {
        FileWriter fos = new FileWriter("mailStore.txt");
        PrintWriter pos = new PrintWriter(fos);
        pos.print(message.getSender()+";");
        pos.print(message.getReceiver()+";");
        pos.print(message.getSubject()+";");
        pos.print(message.getBody()+";");
        pos.print(message.getTimestamp());
        pos.close();
    }

    @Override
    public List<Message> getMail(String username) throws FileNotFoundException { //obtenir tots els missatges de un user concret.
        List<Message> list = new LinkedList<>();
        File file = new File("mailStore.txt");
        Scanner reader = new Scanner(file);
        reader.useDelimiter(";");
        while (reader.hasNextLine()){
            String sender = reader.next();
            String receiver = reader.next();
            if(receiver.equalsIgnoreCase(username)){
                String subject = reader.next();
                String body = reader.next();
                String date = reader.next();
                Message message = new Message(sender,receiver,body,subject,date);
                list.add(message);
            }

        }
        return list;
    }
}
