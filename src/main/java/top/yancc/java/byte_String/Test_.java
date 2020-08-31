package top.yancc.java.byte_String;

import java.io.IOException;
import java.util.Base64;

public class Test_ {

	public static void main(String[] args) throws IOException {

		String email = "408493323@qq.com";
		
		byte[] byte_1 = Base64.getEncoder().encode(email.getBytes());
		
		String email_encode = new String(byte_1);
		
		System.out.println(email_encode);
		
		
		
		String email_encode_ = "NDA4NDkzMzIzQHFxLmNvbQ==";

		byte[] byte_2 = Base64.getDecoder().decode(email_encode_.getBytes());
		
		String email_ = new String(byte_2);
		
		System.out.println(email_);
		
	}

}
