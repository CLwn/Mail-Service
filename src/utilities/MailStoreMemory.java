package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MailStoreMemory implements MailStore {
    @Override
    public void sendMail(Message message) throws IOException {

    }

    @Override
    public List<Message> getMail(String username) throws FileNotFoundException {
        return null;
    }
}
