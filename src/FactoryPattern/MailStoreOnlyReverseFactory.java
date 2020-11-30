package FactoryPattern;

import DecoratorPattern.ReverseCode;
import Structure.MailStore;
import Structure.MailStoreFiles;

public class MailStoreOnlyReverseFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new ReverseCode(new MailStoreFiles());
    }
}
