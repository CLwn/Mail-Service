package decoratorpattern;

import strategypattern.StrategyCipher;
import structure.MailStore;
import structure.Message;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * Class CipherCode
 * @author Marc García
 * @version 1.0
 */
public class CipherCode implements MailStore, StrategyCipher {
    private MailStore mailStore;
    private String key = "IWantToPassTAP12";


    /**
     * Constructor of CipherCode with 1 parameter
     * @param mailStore mailStore's instance
     */
    public CipherCode(MailStore mailStore) {
        this.mailStore = mailStore;
    }


    /**
     * Method to send a message
     * @param message message's instance
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @Override
    public void sendMail(Message message) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        String encryptData = encrypt(message.getBody());
        message.setBody(encryptData);
        mailStore.sendMail(message);


    }

    /**
     * Method to get the list of messages from a username
     * @param username username of user
     * @return a list of messages
     * @throws Exception
     */
    @Override
    public List<Message> getMail(String username) throws Exception {
        List<Message> list = mailStore.getMail(username);
        for (Message message: list){
            message.setBody(decrypt(message.getBody()));
        }
        return list;
    }

    /**
     * Method to encrypt the message's body
     * @param body message's body
     * @return a encrypted message
     */
    public String encrypt(String body) {
        byte[] encrypted = new byte[0];
        try{
            java.security.Key aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(body.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Method to decrypt a encrypted message's body
     * @param encryptData encrypted message's body
     * @return a decrypted message
     */
    public String decrypt(String encryptData){
        byte[] encrypted = Base64.getDecoder().decode(encryptData.getBytes());
        String decrypted = null;
        try{
            java.security.Key aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}
