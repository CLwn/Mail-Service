package FactoryPattern;

import DecoratorPattern.CipherCode;
import DecoratorPattern.ReverseCode;
import Structure.MailStore;
import Structure.MailStoreFiles;
/**
 * Class MailStoreFilesFactory
 * @author Marc García
 * @version 1.0
 */
public class MailStoreFilesFactory implements MailStoreFactory {
    /**
     * Method to create a new MailStoreFiles with Reverse and Cipher wrappers
     * @return MailStoreFiles
     */
    @Override
    public MailStore createMailStore() {
        return new ReverseCode(new CipherCode(new MailStoreFiles()));
    }
}
