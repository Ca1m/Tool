package top.yancc.java.byte_String;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 从文件读取 byte 流，验证是否有乱码情况
 * @author Infosec_jy
 * 1. 准备 gbk 和 utf-8 的文本文件，且内容包含中文
 * 2. 在程序中 以 字节流的方式读取文件内容，并解析显示
 * 
 * 验证结果：
 * 
 * 分别以字节流的方式读取 gbk 和 utf8 编码的文本文件，
 * 读取之后，对 byte[] 通过 new String(bs, "charsetName") 的方式转换为 String，
 * charsetName 与文件的编码格式一致，就不会乱码
 * 
 */
public class Byte_String_read_file {

	private static Byte_String_read_file base64_read_file = new Byte_String_read_file();
	
	public static void main(String[] args) throws IOException {
		
		ClassLoader classLoader = base64_read_file.getClass().getClassLoader();
		
		String str_gbk = classLoader.getResource("byte_String/read_gbk.txt").getPath();
		String str_utf8 = classLoader.getResource("byte_String/read_utf8.txt").getPath();
		FileInputStream fis_gbk = new FileInputStream(new File(str_gbk));
		FileInputStream fis_utf8 = new FileInputStream(new File(str_utf8));
		
		byte[] bs_gbk = new byte[fis_gbk.available()];
		fis_gbk.read(bs_gbk);
		fis_gbk.close();
		
		byte[] bs_utf8 = new byte[fis_utf8.available()];
		fis_utf8.read(bs_utf8);
		fis_utf8.close();
		
		String str_gbk_ = new String(bs_gbk, "gbk");
		String str_utf8_ = new String(bs_utf8, "utf-8");
		
		System.out.println(str_gbk_);
		System.out.println(str_utf8_);
		
	}
}





