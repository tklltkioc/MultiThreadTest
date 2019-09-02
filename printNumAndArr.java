package MultiThreadTest;

public class printNumAndArr {
    static Object lock = new Object ();

    public static void main (String[] args) {
        TwoMethods tm = new TwoMethods ();
        Thread ti = new Thread (new theInt (tm), "int");
        ti.start ();
        Thread tc = new Thread (new theChar (tm), "char");
        tc.start ();
//        theInt ti=new theInt (tm);
//        theChar tc=new theChar (tm);
//        new Thread (ti).start ();
//        new Thread (tc).start ();
    }

    static class theInt implements Runnable {
        TwoMethods tm;

        public theInt (TwoMethods tm) {
            super ();
            this.tm = tm;
        }

        public void run () {
            tm.showNum ();
        }
    }

    static class theChar implements Runnable {
        TwoMethods tm;

        public theChar (TwoMethods tm) {
            super ();
            this.tm = tm;
        }

        public void run () {
            tm.showChar ();
        }
    }

    static class TwoMethods {
        private int flag = 1;

        public void showNum () {
            for (int i = 1; i <= 26; i++) {
                synchronized (lock) {
                    if (flag != 1) {
                        try {
                            lock.wait ();
                        } catch (InterruptedException e) {
                            e.printStackTrace ();
                        }
                    }
                    System.out.print (i + ",");
                    flag = 0;
                    lock.notify ();
                }
            }
        }

        public void showChar () {
            for (int i = 0; i < 26; i++) {
                synchronized (lock) {
                    if (flag != 0) {
                        try {
                            lock.wait ();
                        } catch (InterruptedException e) {
                            e.printStackTrace ();
                        }
                    }
                    System.out.print ((char) (i + 'a') + ",");
                    flag = 1;
                    lock.notify ();
                }
            }
        }
    }
}
