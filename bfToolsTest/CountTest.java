package threadtest.bfToolsTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 11:44
 */
public class CountTest {
    static CountDownLatch countDownLatch=new CountDownLatch (3);

    public static void main (String[] args) throws InterruptedException{
        new Thread (new Runnable () {
            @Override
            public void run () {
                System.out.println (1);
                countDownLatch.countDown ();
                System.out.println (2);
                countDownLatch.countDown ();
            }
        }).start ();
        countDownLatch.await ();
        System.out.println (3);

    }
}
