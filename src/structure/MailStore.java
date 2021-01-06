package structure;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MailStore {
    void sendMail(Message message) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException;
    List<Message> getMail(String username) throws Exception;

}
