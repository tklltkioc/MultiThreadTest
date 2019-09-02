package threadtest;

public class MyThread extends Thread{
	private String name;
	public MyThread(String name){
		this.name=name;
	}
	public void run(){
		System.out.println(name);
	}

	public static void main (String[] args) {
		MyThread t1=new MyThread("sada");
		MyThread t2=new MyThread("ada");
		for (int i = 0; i < 100; i++) {
			if ((i&1)==0){
				t1.run();
			}
			else
				t2.run();
		}

	}


}
