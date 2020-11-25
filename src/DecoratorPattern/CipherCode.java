package DecoratorPattern;

import StrategyPattern.StrategyCipher;
import Structure.MailStore;
import Structure.Message;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class CipherCode implements MailStore, StrategyCipher {
    private MailStore mailStore;
    private String key = "IWantToPassTAP12";


    public CipherCode(MailStore mailStore) {
        this.mailStore = mailStore;
    }


    @Override
    public void sendMail(Message message) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        String encryptData = encrypt(message.getBody());
        message.setBody(encryptData);
        mailStore.sendMail(message);


    }

    @Override
    public List<Message> getMail(String username) throws Exception {
        List<Message> list = mailStore.getMail(username);
        for (Message message: list){
            message.setBody(decrypt(message.getBody()));
        }
        return list;
    }

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
