package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MailStore {
    void sendMail(Message message) throws IOException;
    Message getMail() throws FileNotFoundException;

}
