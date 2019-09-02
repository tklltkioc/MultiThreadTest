package MultiThreadTest.bfToolsTest;

import java.util.concurrent.*;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 14:16
 */
public class CyclicBarrierTest {
    private static final int threadCount = 500;
    static final CyclicBarrier cyclicBarrier = new CyclicBarrier (5);

    public static void main (String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool (5);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep (1000);
            threadPool.execute (() -> {
                try {
                    test (threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace ();
                }
            });
        }
        threadPool.shutdown ();
    }

    public static void test (int threadnum) throws InterruptedException, BrokenBarrierException {
        System.out.println ("threadnum" + threadnum + " is ready");
        try {
            cyclicBarrier.await (60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println ("cycexception");
        }
        System.out.println ("threadnum" + threadnum + " is finish");

    }

}
