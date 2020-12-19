package JUnit;


import Structure.MailStoreFiles;
import Structure.Message;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailStoreFilesTest {
    MailStoreFiles mailStoreFiles = new MailStoreFiles();


    @Test
    public void test1SendMail() throws Exception {
        System.out.println("Test send mail");
        mailStoreFiles.sendMail(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        Assert.assertTrue(mailStoreFiles.getMail("josh") != null);
    }

    @Test
    public void test2GetMail() throws IOException, ParseException {
        System.out.println("Test get mail");
        List<Message> result = mailStoreFiles.getMail("josh");

        List<Message> expected = new LinkedList<>();
        expected.add(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));

        Assert.assertEquals(expected.size(), result.size());
        for (int i = 0; i<result.size(); i++){
            Assert.assertTrue(result.get(i).equals(expected.get(i)));
        }

    }
}
