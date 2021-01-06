package structure;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class MailStoreFiles
 * @author Marc García
 * @version 1.0
 */
public class MailStoreFiles implements MailStore {
    private File file = new File("mailStore.txt");

    /**
     * Method to send messages
     * @param message message object
     * @throws IOException
     */
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

    /**
     * Method to get a messages list
     * @param username username of user
     * @return messages list
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public List<Message> getMail(String username) throws IOException, ParseException {
        List<Message> list = new LinkedList<>();
        Scanner reader = new Scanner(file);
        reader.useDelimiter(";");

        while(reader.hasNextLine()){
            String sender = reader.next();
            String receiver = reader.next();
            if(receiver.equalsIgnoreCase(username)) {
                String subject = reader.next();
                String body = reader.next();
                String date = reader.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(date);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                Message message = new Message(sender, receiver, subject, body, timestamp);
                list.add(message);
                reader.nextLine();
            }else reader.nextLine();
        }
        return list;
    }
}
