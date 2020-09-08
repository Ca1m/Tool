package top.yancc.java.lamdba;



public class Lamdba_02 {

	
	
	public static void main(String[] args) {
		
		final String greet = "Hello";
		
		GreetService greetService = (name) -> {
			System.out.println("start...");
			
			System.out.print(greet + name);
		};
		
		greetService.sayMessage("yancy_01");
		
		
	}

	
	interface GreetService {
		void sayMessage(String message);
	}
	
}



