package threadtest.bfToolsTest;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/29 14:28
 */
public class BankWaterService implements Runnable{
    private static final int threadCount=4;
    private final CyclicBarrier cyclicBarrier=new CyclicBarrier(4,this);
    private Executor executor=Executors.newFixedThreadPool (4);
    private ConcurrentHashMap<String,Integer>sheetBankCount=new ConcurrentHashMap<> ();
    private void count(){
        for (int i = 0; i < 4; i++) {
            executor.execute (new Runnable () {
                @Override
                public void run () {
                    sheetBankCount.put (Thread.currentThread ().getName (), 1);
                    //计算完毕，插入屏障
                    try{
                        cyclicBarrier.await ();
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace ();
                    }
                }
            });
        }
    }
    public void run(){
        int res=0;
        for (Map.Entry<String,Integer>sheet:sheetBankCount.entrySet ()){
            res+=sheet.getValue ();
        }
        sheetBankCount.put ("res", res);
        System.out.println (res);
    }
    public static void main(String[]args){
        BankWaterService bankWaterService=new BankWaterService ();
        bankWaterService.count ();
    }
}
