package DecoratorPattern;

import StrategyPattern.StrategyCipher;
import Structure.MailStore;
import Structure.Message;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ReverseCode implements MailStore, StrategyCipher {
    private MailStore mailStore;

    public ReverseCode(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    @Override
    public void sendMail(Message message) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        message.setBody(encrypt(message.getBody()));
        mailStore.sendMail(message);
    }

    @Override
    public List<Message> getMail(String username) throws Exception {
        List<Message> list = mailStore.getMail(username);
        for (Message message: list) message.setBody(decrypt(message.getBody()));
        return list;
    }

    @Override
    public String encrypt(String body) {
        StringBuilder builder = new StringBuilder();
        builder.append(body);
        return builder.reverse().toString();
    }

    @Override
    public String decrypt(String encryptData) {
        StringBuilder builder = new StringBuilder();
        builder.append(encryptData);
        return builder.reverse().toString();
    }
}
