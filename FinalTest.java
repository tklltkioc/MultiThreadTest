package MultiThreadTest;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/25 16:49
 */
public class FinalTest {
    int i;
    final int j;
    static FinalTest obj;

    public FinalTest() {
        i = 0;
        j = 1;
    }

    public static void write() {
        obj = new FinalTest ();
    }

    public static void read() {
        FinalTest object = obj;
        int a = object.i;
        int b = object.j;
    }


}
