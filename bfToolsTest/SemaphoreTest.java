package threadtest.bfToolsTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 14:56
 */
public class SemaphoreTest {
    private static final int threadcount=500;

    public static void main (String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool (300);
        final Semaphore semaphore=new Semaphore (20);
        for (int i = 0; i < threadcount; i++) {
            final int threadnum=i;
            threadPool.execute (()->{
                try {
                    semaphore.acquire ();//获取一个许可，所以可运行线程数量为20/1=20
                    test (threadnum);
                    semaphore.release ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }

            });
        }
        threadPool.shutdown ();
        System.out.println ("finish");
    }
    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }

}
