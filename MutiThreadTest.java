package MultiThreadTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/7/10 20:36
 */
public class MutiThreadTest {
    private static Object resource1 = new Object ();
    private static Object resource2 = new Object ();
    private static boolean flag = true;

    public static void main (String[] args) throws InterruptedException {
        Thread t1 = new Thread (new wait (), "t1");
        t1.start ();
        TimeUnit.SECONDS.sleep (2);
        Thread t2 = new Thread (new notify (), "t2");
        t2.start ();

//        new Thread(()->{
//            synchronized (resource1){
//                System.out.println(Thread.currentThread()+"get r1");
//                try{
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread()+"waiting get r2");
//                synchronized (resource2){
//                    System.out.println(Thread.currentThread()+"get r2");
//                }
//            }
//
//        },"xc1").start();
//
//        new Thread(()->{
//            synchronized(resource1){
//                System.out.println(Thread.currentThread()+"get r2");
//                try{
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread()+"waiting get r1");
//                synchronized (resource2){
//                    System.out.println(Thread.currentThread()+"get r1");
//                }
//            }
//
//        },"xc2").start();


//        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
//        ThreadInfo[]threadInfos=threadMXBean.dumpAllThreads(false, false);
//        for (ThreadInfo threadInfo:threadInfos){
//            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
//        }

    }

    public static class wait implements Runnable {

        @Override
        public void run () {
            synchronized (resource1) {
                while (flag) {
                    System.out.println (Thread.currentThread () + " flag true" + new SimpleDateFormat ("HH:mm:ss").format (new Date ()));
                    try {
                        resource1.wait ();
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
                System.out.println (Thread.currentThread () + "flag is false");

            }

        }
    }

    public static class notify implements Runnable {
        @Override
        public void run () {
            synchronized (resource1) {

                System.out.println (Thread.currentThread () + " flag false" + new SimpleDateFormat ("HH:mm:ss").format (new Date ()));
                resource1.notifyAll ();
                flag = false;

            }
            System.out.println ("flag is false");

        }

    }

}
