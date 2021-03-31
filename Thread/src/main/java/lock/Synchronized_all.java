package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import callable_.My_Callable;


public class Synchronized_all {
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		My_Callable my_Callable = new My_Callable();
		
		for (int i = 0; i < 10; i++) {
			Future<String> future = executorService.submit(my_Callable);
		}
		
		executorService.shutdown();
	}

	
}
