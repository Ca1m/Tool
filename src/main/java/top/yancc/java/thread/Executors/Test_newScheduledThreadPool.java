package top.yancc.java.thread.Executors;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定长线程池，可设置定时及周期性任务
 * 
 * @author Infosec_jy
 *
 *  1. 如何关闭线程池
 *  2. 如何使用周期定时线程
 *
 */
public class Test_newScheduledThreadPool {

	private static final CountDownLatch cdl = new CountDownLatch(10); // 控制任务一共执行多少次
	
	public static void main(String[] args) throws InterruptedException {

		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

		// 获取一个线程，执行 功能代码
		myThread myThread = new Test_newScheduledThreadPool().new myThread();

		// main 线程循环10，每次通过线程池获取一个线程，执行线程的run 方法
		for (int i = 0; i < 10; i++) {
			scheduledThreadPool.schedule(myThread, 3, TimeUnit.SECONDS);
		}
		cdl.await();
		scheduledThreadPool.shutdown(); // 停止空闲的线程，给运行的进程设置中断标识
		System.out.println("over");
		
		
		// 周期线程
		// long nowTime = new Date().getTime();
		// scheduledThreadPool.scheduleAtFixedRate(myThread, 0, 2, TimeUnit.SECONDS);
	}

	/**
	 * 线程任务: 重点理解 是 一个 任务
	 * @author Infosec_jy
	 */
	class myThread implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName());
			cdl.countDown();
		}
	}

}
