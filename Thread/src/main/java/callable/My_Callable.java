package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class My_Callable implements Callable<String> {

	public static void main(String[] args) throws Exception {
		
		FutureTask<String> futureTask = new FutureTask<String>(new My_Callable());
		new Thread(futureTask).start();
		
		System.out.println(futureTask.get());
	}

	@Override
	public String call() throws Exception {
		
		return "yancy";
	}
}
