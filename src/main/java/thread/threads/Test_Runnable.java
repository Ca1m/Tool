package thread.threads;

public class Test_Runnable implements Runnable {

	public static void main(String[] args) {
		

		Test_Runnable test_Runnable = new Test_Runnable();
		
		new Thread(test_Runnable).start();
		
	}

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getId() + "," + Thread.currentThread().getName());
		
	}

	
	
	
	
}
