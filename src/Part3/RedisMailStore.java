package Part3;

import Structure.MailStore;
import Structure.Message;
import java.util.List;


public class RedisMailStore implements MailStore {
    private Redis redis;

    public RedisMailStore(Redis redis) {
        this.redis = redis;
    }

    @Override
    public void sendMail(Message message) {
        redis.putData(message);
    }

    @Override
    public List<Message> getMail(String username) throws Exception {
        return redis.getData(username);
    }
}
