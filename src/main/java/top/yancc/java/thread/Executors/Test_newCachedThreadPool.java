package top.yancc.java.thread.Executors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存线程池，如果线程池超过处理需要，灵活回收线程
 * @author Infosec_jy
 *
 * 测试执行20次任务，查看执行任务的线程名称 ： 使用线程数量少
 * 测试执行20次任务，每次线程在执行任务时，都等待2s,以启动其他线程来执行后面的任务 ： 使用更多线程
 *
 */
public class Test_newCachedThreadPool {

	public String threadPoolName = "Test_newCachedThreadPool";
	
	final static CountDownLatch cdl = new CountDownLatch(10);
	
	public static void main(String[] args) throws InterruptedException {
		
		Test_newCachedThreadPool test_cachedThreadPool = new Test_newCachedThreadPool();
		/*
		MyThread myThread = test_cachedThreadPool.new MyThread();
		System.out.println(test_cachedThreadPool.threadPoolName);
		System.out.println(myThread.threadPoolName);
		*/
		
		MyThread myThread = new MyThread();
		
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			try {
				// 从线程池获取一个线程，执行逻辑操作：执行过程，不等待，main进程继续执行
				cachedThreadPool.execute(myThread);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cdl.await();
		cachedThreadPool.isShutdown();
		System.out.println("ThreadPool is shutdown");
	}
	/**
	 * 自定义线程任务
	 * @author Infosec_jy
	 *
	 */
	static class MyThread implements Runnable {

		@Override
		public void run() {
			
			System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName());
			cdl.countDown();
		}
	}
	
	
}

