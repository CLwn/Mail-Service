package FactoryPattern;

import DecoratorPattern.CipherCode;
import DecoratorPattern.ReverseCode;
import Structure.MailStore;
import Structure.MailStoreFiles;

public class MailStoreFilesFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new ReverseCode(new CipherCode(new MailStoreFiles()));
    }
}
