package factorypattern;

import decoratorpattern.ReverseCode;
import structure.MailStore;
import structure.MailStoreFiles;
/**
 * Class MailStoreReverseFactory
 * @author Marc García
 * @version 1.0
 */
public class MailStoreOnlyReverseFactory implements MailStoreFactory {
    /**
     * Method to create a new MailStoreFiles with reverse wrapper
     * @return MailStoreFiles
     */
    @Override
    public MailStore createMailStore() {
        return new ReverseCode(new MailStoreFiles());
    }
}
