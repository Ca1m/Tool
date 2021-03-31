package innerClass;

import innerClass.pack.InnerClass_pack;

/**
 * 匿名内部类：内部类，没有名称，只在方法体中执行
 * 总结： 1. 匿名内部类直接在方法中 创建类的结构体， 必要有接口或者基类，用来指向实例，后续调用
 */
public class AnonyInnerDemo {

	public static void main(String[] args) {
		// 1. 匿名类 继承 父类
		InnerClass inner = new InnerClass() {
			public void greet(String name) {
				System.out.println(name);
			}
		};
		inner.greet("yancy_01");

		// 2. 匿名类 实现 接口
		new InnerInterFace() {
			@Override
			public void greet() {
				System.out.println("yancy_02");
			}
		}.greet();

		// 3. 调用 不同包中的 protected 方法
		new InnerClass_pack() {
			public void testProtectMethod() {
				super.greet();
			}
		}.testProtectMethod();
	}
}
