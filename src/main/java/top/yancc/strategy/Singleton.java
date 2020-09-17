package top.yancc.strategy;


// 双重检查单例
public class Singleton{
	// 保证可见性 和 指令重排序
	private volatile static Singleton instance; // 唯一实例
	
	private Singleton(){} // 私有化构造方法
	
	public static Singleton getInstance() {
		// 第一重检查锁定
		if (instance == null) { 
			//  同步锁定代码块
			synchronized (Singleton.class) { // 保证原子操作
				// 第二重检查锁定
				if (instance == null) {
					// 非原子操作
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
