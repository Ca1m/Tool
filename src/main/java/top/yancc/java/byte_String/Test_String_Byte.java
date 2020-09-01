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
		
		String s = "A";  // UTF-8
		
		byte[] bs_g = s.getBytes("gbk");
		byte[] bs_g0 = encoder.encode(bs_g);
		
		System.out.println(Arrays.toString(bs_g));  //  中 在 gbk中的编码为 [-42, -48]
		System.out.println(Arrays.toString(bs_g0)); //    [49, 116, 65, 61]
		
		
		// 直接输出
		String s_g1 = new String(bs_g0, "gbk");
		System.out.println(s_g1);		          // [49, 116, 65, 61]
		byte[] bs_g1 = s_g1.getBytes("gbk");
		byte[] bs_g11 = decoder.decode(bs_g1);
		String s_g11 = new String(bs_g11, "gbk");
		
		// 解码，输出
		byte[] bs_g2 = decoder.decode(bs_g0);
		String s_g2 = new String(bs_g2, "gbk");
		System.out.println(s_g2);
		
		
		byte[] bs_u = s.getBytes("utf-8");
		byte[] bs_u0 = encoder.encode(bs_u);
		
		// 直接输出
		String s_u1 = new String(bs_u0, "utf-8");
		System.out.println(s_u1);
		byte[] bs_u1 = s_u1.getBytes("utf-8");
		byte[] bs_u11 = decoder.decode(bs_u1);
		String s_u11 = new String(bs_u11, "utf-8");
		System.out.println(s_u11);
		
		// 解码，输出
		byte[] bs_u2 = decoder.decode(bs_u0);
		String s_u2 = new String(bs_u2, "utf-8");
		System.out.println(s_u2);
		
		
	}

}
