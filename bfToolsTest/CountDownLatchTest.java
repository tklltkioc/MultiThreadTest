package MultiThreadTest.bfToolsTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 11:17
 */
public class CountDownLatchTest {
    private static final int threadCount = 500;

    public static void main (String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool (300);
        final CountDownLatch countDownLatch = new CountDownLatch (threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            threadPool.execute (() -> {
                try {
                    test (threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                } finally {
                    countDownLatch.countDown ();
                }
            });
        }
        countDownLatch.await ();
        threadPool.shutdown ();
        System.out.println ("finish");
    }

    public static void test (int threadnum) throws InterruptedException {
        Thread.sleep (1000);// 模拟请求的耗时操作
        System.out.println ("threadnum:" + threadnum);
        Thread.sleep (1000);// 模拟请求的耗时操作
    }

}
