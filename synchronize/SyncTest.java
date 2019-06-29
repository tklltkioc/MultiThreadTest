package threadtest.synchronize;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/24 10:51
 */
public class SyncTest implements Runnable{
    static int i=0;
    static int j=0;

    /**
     * 作用于静态方法,锁是当前class对象,也就是
     * SyncTest类对应的class对象
     */
    public synchronized static void incre(){
        i++;
    }
    /**
     * 非静态,访问时锁不一样不会发生互斥
     */
    public synchronized  void incres(){
        j++;
    }
    static SyncTest ins=new SyncTest();


    @Override
    public void run () {
        for (int j = 0; j < 100000; j++){
            synchronized (this){
                i++;
                incres();
            }

        }

    }

    public static void main (String[] args) throws InterruptedException{
        //单个实例
//        SyncTest instan=new SyncTest();
//        Thread t1=new Thread(instan);
//        Thread t2=new Thread(instan);
        //多个实例，导致多把锁,方法加上静态解决
        Thread t1=new Thread(ins);
        Thread t2=new Thread(ins);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
