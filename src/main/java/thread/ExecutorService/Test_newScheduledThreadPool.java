package thread.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test_newScheduledThreadPool {

	public static void main(String[] args) {

		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		
		for (int i = 0; i < 10; i++) {
			final int index = i;
			
			// 获取一个线程，执行 功能代码
			scheduledThreadPool.schedule(new Runnable() {
				public void run() {
					System.out.println("delay 3 seconds" + index);
				}
			}, 3, TimeUnit.SECONDS);
		}
	}

}
