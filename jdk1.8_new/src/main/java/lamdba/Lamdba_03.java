package lamdba;

public class Lamdba_03 {

	public static void startThread(Runnable run) {
		
		new Thread(run).start(); // // 启动一个线程
	}
	
	public static void main(String[] args) {
		
		startThread(new Runnable() {  // 常规写法，匿名内部类 ：创建实例 + 实现方法
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "-> 线程启动了");
			}
		});
		
		startThread(() -> {  System.out.println(Thread.currentThread().getName()); });
		// Lambda 写法 ： () 代表 run() : Runnable 只有一个抽象方法 run
		
		Runnable runnable = () -> {  System.out.println(Thread.currentThread().getName()); };
		
		runnable.run();
	}
}
