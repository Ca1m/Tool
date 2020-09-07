package top.yancc.java.thread.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 通过指定参数的构造方法创建线程池
 * @author Infosec_jy
 *
 * ThreadPoolExecutor 线程池，用来替代 Executors
 */
public class Test_ThreadPoolExecutor {

	private static CountDownLatch cdl = new CountDownLatch(10); // 任务执行次数
	
	/**
	 * 创建  ThreadPoolExecutor  线程池
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		ThreadFactory myThreadFactory = new MyThreadFactory();
		AbortPolicy abortPolicy = new AbortPolicy();
		
		BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(10);
		
		ThreadPoolExecutor tPollExecutor = new ThreadPoolExecutor(20, 50, 5, TimeUnit.SECONDS, arrayBlockingQueue, myThreadFactory, abortPolicy);
		
		MyThread myThread = new Test_ThreadPoolExecutor().new MyThread();
		
		for (int i = 0; i < 10; i++) {
			tPollExecutor.execute(myThread);
		}
		cdl.await();
		tPollExecutor.shutdown();
		
		System.out.println("over....");
	}

	/**
	 * 线程   任务
	 * @author Infosec_jy
	 */
	class MyThread implements Runnable {

		@Override
		public void run() {
			System.out.println("myThread" + Thread.currentThread().getName());
			cdl.countDown();
		}
	}
}








