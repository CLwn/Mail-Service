package part3;

import structure.Message;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 * Class Redis
 * @author Marc García
 * @version 1.0
 */
public class Redis {
    private Jedis jedis = new Jedis("localhost");
    private static Redis instance;

    /**
     * Private constructor
     */
    private Redis() {
    }

    /**
     * Method to get instance of Redis
     * @return instance
     */
    public static Redis getInstance(){
        if (instance == null) instance = new Redis();
        return instance;
    }

    /**
     * Method to save message in Redis server
     * @param message message's object
     */
    public void putData(Message message){

        String data = message.getTimestamp().toString();
        String content = message.getSender()+";"+message.getReceiver()+";"+
                message.getSubject()+";"+message.getBody()+";"+data+";";
        jedis.lpush(message.getReceiver(),content);

    }

    /**
     * Method to get a list of messages from Redis server
     * @param username username of user
     * @return a list of messages
     * @throws ParseException
     */
    public List<Message> getData(String username) throws ParseException {
        List<String> list = jedis.lrange(username, 0, -1);
        List<Message> result = new LinkedList<>();
        for (String content: list){
            String[] parts = content.split(";");
            String sender = parts[0];
            String receiver = parts[1];
            String subject = parts[2];
            String body = parts[3];
            String time = parts[4];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(time);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            result.add(new Message(sender,receiver,subject,body,timestamp));
        }
        return result;
    }
}

