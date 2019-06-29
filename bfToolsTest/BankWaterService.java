package threadtest.bfToolsTest;

import java.util.concurrent.*;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 14:28
 */
public class BankWaterService {
    private static final int threadCount=500;
    static final CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()-> {
        System.out.println ("线程数达到后优先执行");
    });
    public static void main (String[] args) throws InterruptedException{
        ExecutorService threadPool= Executors.newFixedThreadPool (10);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum=i;
            Thread.sleep (1000);
            threadPool.execute (()->{
                try {
                    test (threadNum);
                }catch (InterruptedException e){
                    e.printStackTrace ();
                }catch (BrokenBarrierException e){
                    e.printStackTrace ();
                }
            });
        }
        threadPool.shutdown ();
    }
    public static void test(int threadnum) throws InterruptedException, BrokenBarrierException {
        System.out.println ("threadnum"+threadnum+" is ready");
        cyclicBarrier.await ();
        System.out.println ("threadnum"+threadnum+" is finish");

    }

}
