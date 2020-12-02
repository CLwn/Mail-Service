package Structure;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Class MailStoreMemory
 * @author Marc García
 * @version 1.0
 */
public class MailStoreMemory implements MailStore {
    HashMap<String,List<Message>> map = new HashMap<>();


    /**
     * Method to send messages
     * @param message
     * @throws IOException
     */
    @Override
    public void sendMail(Message message){
        List<Message> list;
        if (map.containsKey(message.getReceiver())) list = map.get(message.getReceiver());
        else list = new LinkedList<>();
        list.add(message);
        map.put(message.getReceiver(), list);
    }

    /**
     * Method to get a messages list
     * @param username
     * @return messages list
     * @throws IOException
     */
    @Override
    public List<Message> getMail(String username) throws Exception {
        List<Message> list;
        if (map.containsKey(username)) list = map.get(username);
        else throw new Exception("Don't Exist this user");

        return list;
    }
}
