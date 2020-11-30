package FactoryPattern;

import Part3.Redis;
import Part3.RedisMailStore;
import Structure.MailStore;

public class RedisMailStoreFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new RedisMailStore(Redis.getInstance());
    }
}
