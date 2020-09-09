package top.yancc.strategy;


// 懒汉式单例
public class Singleton{
	private static Singleton instance; // 唯一实例
	private Singleton(){} // 私有化构造方法
	
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
