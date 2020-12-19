package JUnit;

import Part3.Redis;
import Part3.RedisMailStore;
import Structure.Message;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class RedisMailStoreTest {
    RedisMailStore redisMailStore = new RedisMailStore(Redis.getInstance());


    @Test
    public void test1SendMail() throws Exception {
        System.out.println("Test send mail");
        redisMailStore.sendMail(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));

        Assert.assertTrue(redisMailStore.getMail("josh") != null);
    }

    @Test
    public void test2GetMail() throws Exception {
        System.out.println("Test get mail");
        redisMailStore.sendMail(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        List<Message> result = redisMailStore.getMail("josh");

        List<Message> expected = new LinkedList<>();
        expected.add(new Message("david", "josh", "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));

        Assert.assertEquals(expected.size(), result.size());
        for (int i = 0; i<result.size(); i++){
            Assert.assertTrue(result.get(i).equals(expected.get(i)));
        }

    }
}
