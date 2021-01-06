package factorypattern;

import structure.MailStore;
import structure.MailStoreMemory;

/**
 * Class MailStoreMemoryFactory
 * @author Marc García
 * @version 1.0
 */
public class MailStoreMemoryFactory implements MailStoreFactory {
    /**
     * Method to create a new MailStoreMemory
     * @return MailStoreMemory
     */
    @Override
    public MailStore createMailStore() {
        return new MailStoreMemory();
    }
}
