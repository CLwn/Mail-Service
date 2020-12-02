package Part3;

import Structure.MailStore;
import Structure.Message;
import java.util.List;

/**
 * Class RedisMailStore
 * @author Marc García
 * @version 1.0
 */
public class RedisMailStore implements MailStore {
    private Redis redis;

    /**
     * Constructor with one parameter
     * @param redis
     */
    public RedisMailStore(Redis redis) {
        this.redis = redis;
    }

    /**
     * Method to send a message
     * @param message
     */
    @Override
    public void sendMail(Message message) {
        redis.putData(message);
    }

    /**
     * Method to get a list of messages by username
     * @param username
     * @return a list of messages
     * @throws Exception
     */
    @Override
    public List<Message> getMail(String username) throws Exception {
        return redis.getData(username);
    }
}
