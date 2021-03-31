package threads;

public class My_Runnable implements Runnable {

	// 线程任务
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		
		My_Runnable test_Runnable = new My_Runnable();
		Thread my_Runnable = new Thread(test_Runnable);
		
		my_Runnable.start();
		my_Runnable.start();
	}
}
