package top.yancc.java.thread.threads;

public class My_Thread extends Thread {

	// 线程任务
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	public static void main(String[] args) {
		My_Thread thread_1 = new My_Thread();
		thread_1.start();
		thread_1.start();
	}
}

