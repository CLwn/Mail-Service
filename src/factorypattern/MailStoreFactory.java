package factorypattern;

import structure.MailStore;

public interface MailStoreFactory {

    MailStore createMailStore();
}
