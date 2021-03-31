package lamdba;

public class Lamdba_02 {

	public static void main(String[] args) {
		
		final String greet = "Hello";
		
		GreetService greetService = (name) -> {System.out.println(name);};
		
		greetService.sayMessage(greet);
		
	}

	interface GreetService {
		void sayMessage(String message);
	}
	
}



