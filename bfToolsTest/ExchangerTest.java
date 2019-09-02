package MultiThreadTest.bfToolsTest;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 15:08
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<> ();
    private static ExecutorService threadPool = Executors.newFixedThreadPool (2);

    public static void main(String[] args) {
        threadPool.execute (new Runnable () {
            @Override
            public void run() {
                try {
                    String A = "aa";
                    exgr.exchange (A);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        });
        threadPool.execute (new Runnable () {
            @Override
            public void run() {
                try {
                    String B = "bb";
                    String A = exgr.exchange ("B");
                    System.out.println ("是否一致:" + A.equals (B) + " a是:" + A + ",b是:" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }

            }
        });
        threadPool.shutdown ();

    }


}
