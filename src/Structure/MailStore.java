package Structure;

import java.io.IOException;
import java.util.List;

public interface MailStore {
    void sendMail(Message message) throws IOException;
    List<Message> getMail(String username) throws Exception;

}
