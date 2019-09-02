package MultiThreadTest.synchronize;

import threadtest.SleepUntils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object ();

    public static void main (String[] args) {
        Thread waitThread = new Thread (new wait (), "WaitThread");
        waitThread.start ();
        try {
            TimeUnit.SECONDS.sleep (2);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        Thread notifyThread = new Thread (new notify (), "notifyThread");
        notifyThread.start ();
    }

    static class wait implements Runnable {

        @Override
        public void run () {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println (Thread.currentThread () + "flag true" + new SimpleDateFormat ("HH:mm:ss").format (new Date ()));
                        lock.wait ();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println (Thread.currentThread () + "flag is false.running@" +
                        new SimpleDateFormat ("HH:mm:ss").format (new Date ()));
            }
        }
    }

    static class notify implements Runnable {

        @Override
        public void run () {
            synchronized (lock) {
                System.out.println (Thread.currentThread () + "true.notify" + new SimpleDateFormat ("HH:mm:ss").format (new Date ()));
                lock.notifyAll ();
                flag = false;
                SleepUntils.second (5);
            }
//            synchronized (lock){
//                System.out.println(Thread.currentThread()+"hold again lock."+
//                        new  SimpleDateFormat("HH:mm:ss").format(new Date()));
//                SleepUntils.second(5);
//            }
        }
    }
}
