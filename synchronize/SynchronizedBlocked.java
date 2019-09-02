package MultiThreadTest.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/25 14:04
 */
public class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        System.out.println ("Trying to");
        while (true) {
            Thread.yield ();
        }
    }

    public SynchronizedBlocked() {
        new Thread () {
            public void run() {
                f ();
            }
        }.start ();

    }


    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted ()) {
                System.out.println ("zhongduanxiancheng");
                break;
            } else {
                f ();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlocked sync = new SynchronizedBlocked ();
        Thread t = new Thread (sync);
        t.start ();
        TimeUnit.SECONDS.sleep (2);
        t.isInterrupted ();

    }

}
