package factorypattern;

import decoratorpattern.CipherCode;
import structure.MailStore;
import structure.MailStoreFiles;
/**
 * Class MailStoreCipherFactory
 * @author Marc García
 * @version 1.0
 */
public class MailStoreOnlyCipherFactory implements MailStoreFactory{
    /**
     * Method to create a new MailStoreFiles with Cipher wrapper
     * @return MailStoreFiles
     */
    @Override
    public MailStore createMailStore() {
        return new CipherCode(new MailStoreFiles());
    }
}
