package JUnit;
import FactoryPattern.MailStoreMemoryFactory;
import Structure.*;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matcher.*;



public class MailboxTest {
    MailSystem mailSystem = new MailSystem();
    Mailbox joshbox, david01box;

    public void startParameters() throws Exception {
        mailSystem.createMailStore(new MailStoreMemoryFactory());
        joshbox = mailSystem.createNewUser("josh", "Josh", 1998);
        david01box = mailSystem.createNewUser("david01", "David", 2001);
        david01box.sendMail(new Message(david01box.getUsername(), joshbox.getUsername(), "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        joshbox.updateMail("josh");
    }
    @Test //seguramente no haga falta hacerlo ya que nos devuelve void
    public void testUpdateMail() throws Exception{
        System.out.println("Test update email");
        startParameters();
        joshbox.updateMail("josh");
        Assert.assertEquals("", "");
    }

    @Test
    public void testListMail() throws Exception {
        System.out.println("Test list mail");
        startParameters();
        mailSystem.createMailStore(new MailStoreMemoryFactory());
        List<Message> expected = new LinkedList<>();
        expected.add(new Message(david01box.getUsername(), joshbox.getUsername(), "Urgent",
                "Hi Josh, I need access into logs directory, thanks.", new Timestamp(System.currentTimeMillis())));
        List<Message> result = joshbox.listMail();
        Assert.assertEquals(result.size(), expected.size());
        for (int i = 0; i<result.size(); i++){
            Assert.assertTrue(result.get(i).equals(expected.get(i)));
        }
    }

    @Test
    public void testFilterMail() {

    }

    @Test
    public void testGetSpam() {

    }



}
