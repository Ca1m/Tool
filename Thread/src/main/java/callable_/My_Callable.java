package callable_;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class My_Callable implements Callable<String> {

	public static void main(String[] args) {
		My_Callable my_Callable = new My_Callable();
		FutureTask<String> futureTask = new FutureTask<String>(my_Callable);
		new Thread(futureTask).start();
	}

	@Override
	public String call() throws Exception {
		print_();
		System.out.println("call...yancy_01");
		return "yancy_01";
	}
	
	private static synchronized String print_() throws Exception {
		
		Thread.sleep(1000);
		System.out.println("print_()....yancy_1");
		return "yancy_1";
	}
}





