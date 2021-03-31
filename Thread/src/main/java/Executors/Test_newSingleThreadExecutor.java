package Executors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程化 线程池，只会用一个线程来执行任务，保证所有任务按照指定顺序执行
 * @author Infosec_jy
 *
 */
public class Test_newSingleThreadExecutor {

	private static CountDownLatch cdl = new CountDownLatch(100); // 任务执行次数
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(); // 单线程
		MyThread myThread = new Test_newSingleThreadExecutor().new MyThread(); // 创建线程任务
		
		for (int i = 0; i < 100; i++) {
			singleThreadExecutor.execute(myThread);
		}
		
		cdl.await();
		singleThreadExecutor.shutdown();
		System.out.println("");
		System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName());
		System.out.println("Thread main exit;");
		
	}

	/**
	 * 线程 任务
	 * @author Infosec_jy
	 */
	private class MyThread implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getId() +","+ Thread.currentThread().getName());
			cdl.countDown();
		}
	}
	
}








