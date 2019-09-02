package MultiThreadTest.aqs;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/28 15:24
 */
public class Muti_Producer_ConsumerByCondition {

    public static void main (String[] args) {
        threadtest.aqs.ResourceByCondition r = new threadtest.aqs.ResourceByCondition ();
        Mutil_Producer pro = new Mutil_Producer (r);
        Mutil_Consumer con = new Mutil_Consumer (r);
        //生产者线程
        Thread t0 = new Thread (pro);
        Thread t1 = new Thread (pro);
        //消费者线程
        Thread t2 = new Thread (con);
        Thread t3 = new Thread (con);
        //启动线程
        t0.start ();
        t1.start ();
        t2.start ();
        t3.start ();
    }
}

/**
 * @decrition 生产者线程
 */
class Mutil_Producer implements Runnable {
    private threadtest.aqs.ResourceByCondition r;

    Mutil_Producer (threadtest.aqs.ResourceByCondition r) {
        this.r = r;
    }

    public void run () {
        while (true) {
            r.product ("北京烤鸭");
        }
    }
}

/**
 * @decrition 消费者线程
 */
class Mutil_Consumer implements Runnable {
    private threadtest.aqs.ResourceByCondition r;

    Mutil_Consumer (threadtest.aqs.ResourceByCondition r) {
        this.r = r;
    }

    public void run () {
        while (true) {
            r.consume ();
        }
    }
}
