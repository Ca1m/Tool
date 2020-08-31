package top.yancc.cert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cn.com.infosec.util.Base64;

public class Test_String_Byte {

	public static void main(String[] args) throws IOException {
		
		stringToByte();

	}
	
	
	private static void stringToByte() throws IOException {
		
		String s = "<source><header><TASKTAG>����</TASKTAG><LOCALTIME>1563191937368</LOCALTIME><VERSION>0</VERSION><TYPE>LOGIN</TYPE></header></source>";
		
		byte[] bs = s.getBytes("gbk");
		
		String ss = Base64.encode(bs);
		
		
		byte[] bs_1 = Base64.decode(ss);
		
		String ss_1 = new String(bs_1, "UTF-8");
		String ss_2 = new String(bs_1, "GBK");
		
		
		System.out.println(ss);
		System.out.println(ss_1);
		System.out.println(ss_2);
		
		
		String s_u = "��";
		
		byte[] bs_2 = s_u.getBytes("ISO8859-1");
		
		String ss_u1 = new String(bs_2,"ISO8859-1");
		
		System.out.println(ss_u1);
		
		
		
		
		
	}

}
