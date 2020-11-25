package Structure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MailStoreMemory implements MailStore {
    HashMap<String,List<Message>> map = new HashMap<>();



    @Override
    public void sendMail(Message message){
        List<Message> list;
        if (map.containsKey(message.getReceiver())) list = map.get(message.getReceiver());
        else list = new LinkedList<>();
        list.add(message);
        map.put(message.getReceiver(), list);
    }

    @Override
    public List<Message> getMail(String username) throws Exception {
        List<Message> list;
        if (map.containsKey(username)) list = map.get(username);
        else throw new Exception("Don't Exist this user");

        return list;
    }
}
