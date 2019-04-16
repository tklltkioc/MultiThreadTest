package threadtest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WitNotify {
    static boolean flag=true;
    static Object lock=new Object();

    public static void main(String[] args) {
        Thread waitThread=new Thread();
    }
    static class Wait implements Runnable{
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

            }
        }
    }
}
