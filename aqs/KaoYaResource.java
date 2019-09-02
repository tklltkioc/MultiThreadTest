package MultiThreadTest.aqs;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/28 15:29
 */
public class KaoYaResource {

    private String name;
    private int count = 1;//烤鸭的初始数量
    private boolean flag = false;//判断是否有需要线程等待的标志

    /**
     * 生产烤鸭
     */
    public synchronized void product (String name) {
        while (flag) {
            //此时有烤鸭，等待
            try {
                this.wait ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
        this.name = name + count;//设置烤鸭的名称
        count++;
        System.out.println (Thread.currentThread ().getName () + "...生产者..." + this.name);
        flag = true;//有烤鸭后改变标志
        notifyAll ();//通知消费线程可以消费了
    }

    /**
     * 消费烤鸭
     */
    public synchronized void consume () {
        while (!flag) {//如果没有烤鸭就等待
            try {
                this.wait ();
            } catch (InterruptedException e) {
            }
        }
        System.out.println (Thread.currentThread ().getName () + "...消费者........" + this.name);//消费烤鸭1
        flag = false;
        notifyAll ();//通知生产者生产烤鸭
    }
}
