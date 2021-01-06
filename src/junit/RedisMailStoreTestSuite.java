package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value= Suite.class)
@Suite.SuiteClasses(value={RedisMailStoreTest.class})
public class RedisMailStoreTestSuite {
}
