package thread.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_newFixedThreadPool {

	public static void main(String[] args) {
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		
		//System.out.println(Runtime.getRuntime().availableProcessors());
		
		// 任务
		for (int i = 0; i < 10; i++) {
			final int index = i;
			
			// 从线程池获取一个 线程，执行逻辑代码
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}




