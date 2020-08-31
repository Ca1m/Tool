package top.yancc.java.class_.innerClass;

/**
 * 匿名内部类：首先是内部类，没有名称，只执行一次
 * 
 * @author Infosec_jy
 *
 * 总结： 1. 匿名内部类直接在方法中 创建类的结构体，必要有接口或者基类，用来指向实例，后续调用
 * 
 */
public class AnonyInnerClass {

	interface HelloWorld {
		public void greet();

		public void greetSomeone(String someone);
	}

	public static void main(String... args) {
		AnonyInnerClass myApp = new AnonyInnerClass();
		myApp.sayHello();
	}

	class Inner {
		public void greet(String name) {
		}
	}

	/**
	 * 展示方法
	 */
	public void sayHello() {

		String name = "111";

		// 1、创建对象，有基类
		Inner inner = new Inner() {
			public void greet(String name) {
				System.out.println(name);
			}
		};
		inner.greet(name);

		// 2、创建对象：匿名类实现HelloWorld接口
		HelloWorld frenchGreeting = new HelloWorld() {
			String name = "tout le monde";

			public void greet() {
				greetSomeone("tout le monde");
			}
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Salut " + name);
			}
		};
		frenchGreeting.greetSomeone("Fred");
	}

}
