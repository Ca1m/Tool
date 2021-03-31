package byte_String;

import java.io.IOException;
import java.util.Base64;

public class Test_ {

	public static void main(String[] args) throws IOException {

		String email = "40849332中@qq.com";
		
		byte[] byte_1 = Base64.getEncoder().encode(email.getBytes("utf-8"));
		
		
		String email_encode = new String(byte_1,"gbk");
		String email_encode_1 = new String(byte_1,"utf-8");
		
		System.out.println(email_encode);
		System.out.println(email_encode_1);
		
		
		//String email = "NDA4NDkzMzLkuK1AcXEuY29t";

		
		byte[] bs_2 = Base64.getDecoder().decode(email_encode.getBytes("gbk"));
		
		String ss_2 = new String(bs_2, "utf-8");
		
		System.out.println(ss_2);
		
		
	}

}
