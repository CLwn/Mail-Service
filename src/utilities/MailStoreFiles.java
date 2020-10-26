package utilities;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MailStoreFiles implements MailStore {
    private File file = new File("mailStore.txt");

    @Override
    public void sendMail(Message message) throws IOException {
        FileWriter fos = new FileWriter(file, true);
        PrintWriter pos = new PrintWriter(fos);
        pos.print(message.getSender()+";");
        pos.print(message.getReceiver()+";");
        pos.print(message.getSubject()+";");
        pos.print(message.getBody()+";");
        pos.println(message.getTimestamp()+";");
        pos.close();
    }

    @Override
    public List<Message> getMail(String username) throws FileNotFoundException, ParseException {//, ParseException { //obtenir tots els missatges de un user concret.
        List<Message> list = new LinkedList<>();
        Scanner reader = new Scanner(file);
        reader.useDelimiter(";");

        while (reader.hasNextLine()){
            String sender = reader.next();
            String receiver = reader.next();
            if(receiver.equalsIgnoreCase(username)){
                String subject = reader.next();
                String body = reader.next();
                String date = reader.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(date);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                reader.nextLine();
                Message message = new Message(sender,receiver,body,subject,timestamp);
                list.add(message);
            }

        }
        return list;
    }
}
