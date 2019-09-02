package MultiThreadTest;

public class ThreadLock {
    private static int a = 1;
    private static Object lock = new Object ();

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Thread t1 = new Thread () {
            public void run () {
                while (a <= 10)
                    synchronized (lock) {
                        if (a % 2 == 1) {
                            System.out.println ("t1:" + a++);
                        } else {
                            lock.notifyAll ();
                            try {
                                lock.wait (1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace ();
                            }
                        }
                    }
            }
        };
        Thread t2 = new Thread () {
            public void run () {
                while (a <= 10)
                    synchronized (lock) {
                        if (a % 2 == 0) {
                            System.out.println ("t2:" + a++);
                        } else {
                            lock.notifyAll ();
                            try {
                                lock.wait (1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace ();
                            }
                        }
                    }
            }
        };
        t1.start ();
        t2.start ();
    }
}
