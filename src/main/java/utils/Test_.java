package utils;

import java.io.IOException;
import java.util.Base64;


public class Test_ {

	public static void main(String[] args) throws IOException {


		String ss = "NDA4NDkzMzIzQHFxLmNvbQ==";

		ss.getBytes("UTF-8");
		
		byte[] byte_ = Base64.getDecoder().decode(ss.getBytes("UTF-8"));
		
		String ss_ = new String(byte_ , "UTF-8");
		
		System.out.println(ss_);
	}

}
