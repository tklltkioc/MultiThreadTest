package MultiThreadTest.produceAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/8/8 11:17
 */
public class ProducerConsumerTest {
    public static void main (String[] args) {
        final Queue<Integer> sharedQueue = new LinkedList<> ();
        Thread pro = new Producer (sharedQueue);
        Thread con = new Consumer (sharedQueue);
        pro.start ();
        con.start ();

    }

}
