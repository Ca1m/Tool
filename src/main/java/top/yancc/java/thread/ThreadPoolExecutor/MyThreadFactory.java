package top.yancc.java.thread.ThreadPoolExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 构造自己的 线程工厂
 * @author Infosec_jy
 *
 */
public class MyThreadFactory implements ThreadFactory {

	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;
			
	public MyThreadFactory() {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
	}
	
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		
		if (t.isDaemon()) { // 如果线程是 守护线程，设置为 非守护线程
			t.setDaemon(false);
		}
		if (t.getPriority() != Thread.NORM_PRIORITY) { // 如果线程优先级不是 NORM，设置为 NORM
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}
	
}




