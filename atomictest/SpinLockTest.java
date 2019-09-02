package MultiThreadTest.atomictest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/26 16:50
 */
public class SpinLockTest {
    private AtomicReference<Thread> sign = new AtomicReference<> ();

    public void lock() {
        Thread current = Thread.currentThread ();
        while (!sign.compareAndSet (null, current)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread ();
        sign.compareAndSet (current, null);
    }

}
