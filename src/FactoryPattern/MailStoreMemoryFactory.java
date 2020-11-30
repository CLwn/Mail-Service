package FactoryPattern;

import Structure.MailStore;
import Structure.MailStoreMemory;

public class MailStoreMemoryFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new MailStoreMemory();
    }
}
