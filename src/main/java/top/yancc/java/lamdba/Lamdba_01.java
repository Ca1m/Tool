package top.yancc.java.lamdba;


/**
 *     匿名实现接口
 * @author Infosec_jy
 *
 */
public class Lamdba_01 {

	public static void main(String[] args) {
		
		Lamdba_01 lamdba_all = new Lamdba_01();
		
		
		MathOperation addition = (int a, int b) -> a + b;
		
		MathOperation subtraction = (a, b) -> a - b;
		
		MathOperation multiplication = (a, b) -> a * b;
		
		MathOperation division = (a, b) -> a / b;
		
		System.out.println("10 + 5 = " + lamdba_all.operate(10, 5, addition));
		System.out.println("10 - 5 = " + lamdba_all.operate(10, 5, subtraction));
		System.out.println("10 * 5 = " + lamdba_all.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + lamdba_all.operate(10, 5, division));

		GreetingService greetingService = (message) -> System.out.print("Hello " + message);
		greetingService.sayMessage("yancy");
		
		
		
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
	
	
	interface MathOperation {
		int operation(int a, int b);
	}
	
	interface GreetingService {
		void sayMessage(String message);
	}
	
	
	
}
