package MultiThreadTest.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/27 10:47
 */
public class lockTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock ();
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        lockTest lt = new lockTest ();
        Thread t1 = new Thread (lt);
        Thread t2 = new Thread (lt);
        t1.start ();
        t2.start ();
        //主线程结束的比子线程快，join使得主线程等待子线程完成
        t1.join ();
        t2.join ();
        System.out.println (i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            lock.lock ();
            lock.lock ();
            try {
                i++;
            } finally {
                lock.unlock ();
                lock.unlock ();
            }
        }
    }
}
