package MultiThreadTest;

class RunnableTest implements Runnable {
    //	private String name;
//	public RunnableTest(String name) {
//		// TODO Auto-generated constructor stub
//		this.name=name;
//	}
    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            System.out.println (Thread.currentThread ().getName () + ":" + i);
        }

    }

}
