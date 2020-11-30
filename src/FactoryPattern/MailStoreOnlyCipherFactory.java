package FactoryPattern;

import DecoratorPattern.CipherCode;
import Structure.MailStore;
import Structure.MailStoreFiles;

public class MailStoreOnlyCipherFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return new CipherCode(new MailStoreFiles());
    }
}
