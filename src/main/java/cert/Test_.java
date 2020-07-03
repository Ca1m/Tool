package cert;

import java.io.IOException;

import cn.com.infosec.util.Base64;

public class Test_ {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		String ss = "f29af7";
		
		byte[] bs = Base64.decode(ss);
		
		System.out.println(bs);
		System.out.println("...");
	}

}
