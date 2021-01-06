package junit;


import structure.MailStoreMemory;
import structure.Message;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class MailStoreMemoryTest {
    MailStoreMemory mailStoreMemory = new MailStoreMemory();


    @Test
    public void testSendMail() throws Exception {
        System.out.println("Test send mail");
        mailStoreMemory.sendMail(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));

        Assert.assertTrue(mailStoreMemory.getMail("josh") != null);
    }

    @Test
    public void testGetMail() throws Exception {
        System.out.println("Test get mail");
        mailStoreMemory.sendMail(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        List<Message> result = mailStoreMemory.getMail("josh");

        List<Message> expected = new LinkedList<>();
        expected.add(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));

        Assert.assertEquals(expected.size(), result.size());
        for (int i = 0; i<result.size(); i++){
            Assert.assertTrue(result.get(i).equals(expected.get(i)));
        }

    }
}
