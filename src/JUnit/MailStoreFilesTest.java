package JUnit;

import FactoryPattern.MailStoreFilesFactory;
import Structure.MailStoreFiles;
import Structure.MailSystem;
import Structure.Mailbox;
import Structure.Message;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;


public class MailStoreFilesTest {
    MailStoreFiles mailStoreFiles = new MailStoreFiles();


    @Test
    public void testSendMail() throws Exception {
        System.out.println("Test send mail");
        mailStoreFiles.sendMail(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        for (Message message: mailStoreFiles.getMail("josh")) System.out.println(message.toString());
        Assert.assertTrue(mailStoreFiles.getMail("josh") != null);
    }


    public void testGetMail() throws IOException, ParseException {
        System.out.println("Test get mail");
        List<Message> result = mailStoreFiles.getMail("josh");
        for (Message message: result) System.out.println(message.toString());
        List<Message> expected = new LinkedList<>();
        expected.add(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));

        Assert.assertEquals(result.size(), expected.size());
        for (int i = 0; i<result.size(); i++){
            Assert.assertTrue(result.get(i).equals(expected.get(i)));
        }

    }
}
