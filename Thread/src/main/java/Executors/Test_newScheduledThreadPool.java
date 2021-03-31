package Executors;

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

	
	public static void main(String[] args) throws InterruptedException {

		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() { // 延迟执行
		    @Override
		    public void run() {
		        System.out.println("delay 3 seconds");
		    }
		}, 3, TimeUnit.SECONDS);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() { // 定时执行
		@Override
		public void run() {
		System.out.println("delay 1 seconds, and excute every 3 seconds");
		}
		}, 1, 3, TimeUnit.SECONDS);
	}
}
