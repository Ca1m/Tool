package callable;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<String> {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10); // 线程池

		Future<String> future = executorService.submit(new MyCallable());
		System.out.println(new Date() + "::" + future.get());

		executorService.shutdown(); // 关闭线程池
	}

	@Override
	public String call() throws Exception {

		return "yancy";
	}
}
