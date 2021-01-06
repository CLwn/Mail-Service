package factorypattern;

import part3.Redis;
import part3.RedisMailStore;
import structure.MailStore;
/**
 * Class RedisMailStoreFactory
 * @author Marc García
 * @version 1.0
 */
public class RedisMailStoreFactory implements MailStoreFactory {
    /**
     * Method to create a new RedisMailStore
     * @return RedisMailStore
     */
    @Override
    public MailStore createMailStore() {
        return new RedisMailStore(Redis.getInstance());
    }
}
