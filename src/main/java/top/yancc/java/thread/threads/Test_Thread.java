package top.yancc.java.thread.threads;

public class Test_Thread extends Thread {

	public static void main(String[] args) {
		
		Test_Thread test_Thread = new Test_Thread();
		
		test_Thread.start();

	}
	
	/**
	 * 线程任务
	 */
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getId()+ "," + Thread.currentThread().getName());
		}
	}
	
}
