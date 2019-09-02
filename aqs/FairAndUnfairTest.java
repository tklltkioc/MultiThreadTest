package threadtest.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/8/14 11:18
 */
public class FairAndUnfairTest {
    private static Lock fair = new ReentrantLock (true);
    private static Lock unfair = new ReentrantLock (false);


}
