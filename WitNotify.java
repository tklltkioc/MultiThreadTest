package threadtest;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WitNotify {
    static boolean flag=true;
    static Object lock=new Object();

    public static void main(String[] args) {
        Thread waitThread=new Thread(new Wait(),"WaitThread");
        waitThread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread notifyThread=new Thread(new Notify(),"notifyThread");
        notifyThread.start();
    }
    static class Wait implements Runnable{
        @Override
        public void run(){
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread()+"flag is true.wait@"+
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    }catch (InterruptedException e){

                    }
                }
                System.out.println(Thread.currentThread()+"flag is false.running@"+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
    static class Notify implements Runnable{
        public void run(){
            synchronized (lock){
                System.out.println(Thread.currentThread()+"hold lock."+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag=false;
                SleepUntils.second(5);
            }
            synchronized (lock){
                System.out.println(Thread.currentThread()+"hold again lock."+
                        new  SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUntils.second(5);
            }
        }
    }
}
