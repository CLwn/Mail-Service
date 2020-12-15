package JUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MailStoreFilesTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MailStoreFilesTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("TEST MAIl STORE FILES OK? " + result.wasSuccessful());
    }
}
