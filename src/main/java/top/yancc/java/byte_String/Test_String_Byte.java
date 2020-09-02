package top.yancc.java.byte_String;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;



public class Test_String_Byte {

	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();
	
	
	public static void main(String[] args) throws IOException {
		string_To_Byte();
	}
	
	private static void string_To_Byte() throws IOException {
		
		String s = "123";
		System.out.println(s);
		
		// 编码，输出
		byte[] bs_10 = s.getBytes("gbk");
		System.out.println(Arrays.toString(bs_10));  // 中 在 utf-8 中的编码为[-28, -72, -83]
		
		byte[] bs_11 = encoder.encode(bs_10);
		System.out.println(Arrays.toString(bs_11));  // 经过Base64编码之后 [53, 76, 105, 116]
		
		String s_encode = new String(bs_11, "gbk");
		System.out.println(s_encode);
		
		// 解码，输出
		byte[] bs_20 = s_encode.getBytes("utf-8");
		System.out.println(Arrays.toString(bs_20));
		
		byte[] bs_21 = decoder.decode(bs_20);
		System.out.println(Arrays.toString(bs_21));
		
		String s_21 = new String(bs_21, "utf-8");
		System.out.println(s_21);
	}

}
