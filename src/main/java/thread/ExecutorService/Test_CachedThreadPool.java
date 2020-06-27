package thread.ExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_CachedThreadPool {

	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				//Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// 从线程池获取一个线程，执行逻辑操作：执行过程，不等待，main进程继续执行
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index);
				}
			});
		}
		

	}

	
	
	
}

