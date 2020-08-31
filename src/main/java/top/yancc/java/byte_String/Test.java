package top.yancc.java.byte_String;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


public class Test {
	
	private static Test base64_read_file = new Test();
	
	public static void main(String[] args) throws IOException {

		Encoder encoder = Base64.getEncoder();
		Decoder decoder = Base64.getDecoder();
		
		ClassLoader classLoader = base64_read_file.getClass().getClassLoader();
		
		classLoader.getResource("sign.txt").getPath();

		
		FileInputStream fis_gbk = new FileInputStream(new File("222"));
		FileInputStream fis_utf8 = new FileInputStream(new File(Test.class.getClassLoader().getResource("Base64/Base64_read_utf8.txt").getPath()));
		
		byte[] bs_gbk = new byte[fis_gbk.available()];
		fis_gbk.read(bs_gbk);
		fis_gbk.close();
		
		byte[] bs_utf8 = new byte[fis_utf8.available()];
		fis_utf8.read(bs_utf8);
		fis_utf8.close();
		
		String str_gbk = new String(bs_gbk, "gbk");
		String str_utf8 = new String(bs_utf8, "utf-8");
		
	}

}
