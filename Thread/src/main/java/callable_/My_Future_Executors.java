package callable_;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class My_Future_Executors implements Callable<String>{

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10); // 线程池	
		List<Future<String>> list = new ArrayList<Future<String>>(); // 保存 future
		
		for (int i = 0; i < 50; i++) {
			Future<String> future = executorService.submit(new My_Future_Executors());
			list.add(future);
		}
		for (Future<String> future : list) { // 输出
			System.out.println(future.get());
		}
		executorService.shutdown();
	}

	@Override
	public String call() throws Exception {
		return "yancy_01";
	}
}




