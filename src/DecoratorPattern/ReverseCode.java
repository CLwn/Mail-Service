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

    /**
     * Constructor of ReverseCode
     * @param mailStore
     */
    public ReverseCode(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    /**
     * Method to get the list of messages from a username
     * @param message
     * @throws IOException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public void sendMail(Message message) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        message.setBody(encrypt(message.getBody()));
        mailStore.sendMail(message);
    }

    /**
     * Method to get the list of messages from a username
     * @param username
     * @return a list of messages
     * @throws Exception
     */
    @Override
    public List<Message> getMail(String username) throws Exception {
        List<Message> list = mailStore.getMail(username);
        for (Message message: list) message.setBody(decrypt(message.getBody()));
        return list;
    }


    /**
     * Method to encrypt the message's body
     * @param body
     * @return a encrypted message
     */
    @Override
    public String encrypt(String body) {
        StringBuilder builder = new StringBuilder();
        builder.append(body);
        return builder.reverse().toString();
    }

    /**
     * Method to decrypt a encrypted message's body
     * @param encryptData
     * @return a decrypted message
     */
    @Override
    public String decrypt(String encryptData) {
        StringBuilder builder = new StringBuilder();
        builder.append(encryptData);
        return builder.reverse().toString();
    }
}
