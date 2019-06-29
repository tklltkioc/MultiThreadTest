package threadtest;

class Myc implements Runnable{
	private int ticket=5;
	public void run() {
		for (int i = 0; i < 10; i++) {
			test();
		}
	}
	public synchronized void test() {
			if(ticket>0) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("che:"+ticket--);
			}
	}
}
public class ThreadSync {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Myc a=new Myc();
		Thread b=new Thread(a);
		Thread c=new Thread(a);
		Thread d=new Thread(a);
		b.start();
		c.start();
		d.start();
	}

}
