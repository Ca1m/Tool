package top.yancc.java.class_.innerClass;

/**
 * 成员内部类
 * @author Infosec_jy
 *
 * 总结： 
 * 1.内部类作为外部类的成员，实例化需要使用 外部类的实例对象访问
 * 2.private 的私有化 是针对 class 文件来说的，因为 内部类的私有化方法可以在外部类被访问
 * 
 * 建议： 创建内部类使用 getInstance() 方法，代码更易读
 * 
 * 问题： 为什么第二种不行，是 static 的问题还是 main 方法的问题？
 * 
 */
class MembersInnerClass {

	private double radius = 0;
	
	public static void main(String[] args) {
		
		MembersInnerClass mClass = new MembersInnerClass(10);
		
		// 第一种方法
		Inner_ inner_1 = mClass.new Inner_();
		inner_1.draw();
		
		// 第二种方法： 内部实例 和 外部实例 建立联系
		//Inner_ inner_2 = new Inner_(mClass);
		//inner_2.draw();
		
		// 第三种方式 ： 推荐使用，更易读
		Inner_ inner_3 = mClass.getInnerInstance();
		inner_3.draw();
		
		mClass.test_1();
		mClass.test_2();
		mClass.test_3();
		
		Test_1 test_1 = new Test_1();
		
	}
	
	static class Test_1 {
		
	}
	
	/**
	 * 构造方法
	 * @param radius
	 */
	private MembersInnerClass(double radius) {
		this.radius = radius;
	}
	
	/**
	 * 第一种方法 创建 内部类对象
	 */
	private void test_1 () {
		Inner_ inner_1 = new Inner_();
		inner_1.draw();
	}
	/**
	 * 第二种方式 创建 内部类对象
	 */
	public void test_2 () {
		Inner_ inner_2 = new Inner_(this);
		inner_2.draw();
	}
	/**
	 * 第三种方式 创建 内部类对象
	 */
	public void test_3 () {
		MembersInnerClass mClass = new MembersInnerClass(10);
		Inner_ inner_3 = mClass.getInnerInstance();
		inner_3.draw();
	}
	
	/**
	 * 创建成员内部类对象
	 * @return 
	 */
	private Inner_ getInnerInstance() {
		return new Inner_(this);
	}
	
	/**
	 * 成员内部类
	 * @author Infosec_jy
	 */
	private class Inner_ {
		
		private MembersInnerClass membersInnerClass;
		
		// 第一种方法
		private Inner_() {}
		
		// 第二种方法
		private Inner_(MembersInnerClass membersInnerClass) {
			this.membersInnerClass = membersInnerClass;
		}
		
		 //访问外部类成员变量
		private void draw() {
			System.out.println(radius);
		}
	}
}




