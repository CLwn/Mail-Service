package junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RedisMailStoreTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RedisMailStoreTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("TEST REDIS MAIL STORE OK? " + result.wasSuccessful());
    }
}
