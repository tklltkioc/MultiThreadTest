package threadtest;

import java.util.concurrent.TimeUnit;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/24 21:18
 */
public class SyncCodeBlock {
    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(){
            public void run(){
                try {
                    //判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
                    while (!Thread.interrupted()){
                        boolean inter=this.isInterrupted();
                        System.out.println("inter:"+inter);
                        TimeUnit.SECONDS.sleep(2);

                    }
                }catch (InterruptedException e){
                    System.out.println("sss");
                    boolean inter=this.isInterrupted();
                    System.out.println("inter:"+inter);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
        Thread t2=new Thread(){
            public void run(){
                    while (true){
                        if (this.isInterrupted()){
                            System.out.println("aaa");
                            break;
                        }
                    }
                System.out.println("jumpout each");
            }
        };
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        t2.interrupt();
    }
}
