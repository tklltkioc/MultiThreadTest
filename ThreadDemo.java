package MultiThreadTest;

public class ThreadDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Thread a = new Thread (new RunnableTest (), "A");
        Thread b = new Thread (new RunnableTest (), "B");
        Thread c = new Thread (new RunnableTest (), "C");
        a.setPriority (Thread.MIN_PRIORITY);
        b.setPriority (Thread.MAX_PRIORITY);
        c.setPriority (Thread.NORM_PRIORITY);
        a.start ();
        b.start ();
        c.start ();
    }

}
