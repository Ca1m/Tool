package thread;

/**
 * 
 * start() 会执行 run方法
 * 
 * @author Infosec_jy
 */
public class Test_Thread {

	public static void main(String[] args) {
		
		MyThread myThread = new Test_Thread().new MyThread();

		new Thread(myThread).start();
		
	}
	
	/**
	 * 任务 线程
	 * @author Infosec_jy
	 */
	class MyThread implements Runnable {
		@Override
		public void run() {
			System.out.println("MyThread.run()....");
			//Thread.currentThread().interrupt();
		}
	}

}
