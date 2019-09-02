package MultiThreadTest.produceAndConsumer;

import java.util.Queue;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/8/8 12:50
 */
public class Consumer extends Thread {
    private final Queue sharedQueue;

    public Consumer(Queue sharedQueue) {
        super ();
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (sharedQueue) {
                while (sharedQueue.size () == 0) {
                    try {
                        System.out.println ("队列空了，等待生产");
                        sharedQueue.wait ();

                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }

                }
                int number = (int) sharedQueue.poll ();
                System.out.println ("进行消费：" + number);
                sharedQueue.notify ();
            }
        }

    }

}
