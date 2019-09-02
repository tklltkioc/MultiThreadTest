package MultiThreadTest.produceAndConsumer;

import java.util.Queue;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/8/8 11:18
 */
public class Producer extends Thread {
    private static final int MAX_QUEUE_SIZE = 5;
    private final Queue sharedQueue;

    public Producer (Queue sharedQueue) {
        super ();
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run () {
        for (int i = 0; i < 100; i++) {
            synchronized (sharedQueue) {
                while (sharedQueue.size () >= MAX_QUEUE_SIZE) {
                    System.out.println ("队列满了，阻塞");
                    try {
                        sharedQueue.wait ();
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
                sharedQueue.add (i);
                System.out.println ("进行生产：" + i);
                sharedQueue.notify ();
            }
        }


    }


}
