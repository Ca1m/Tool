package byte_String;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
   *    测试读取不同的编码的文件并输出
 * @author Infosec_jy
 * 
 * 结论： 读取文件，与编码无关，通过字节序读取文件内容，然后 通过 new String(byte[], charset) 完成转换并显示
 */
public class Test_read_File {
	
	public static void main(String[] args) throws IOException {

		FileInputStream fis_gbk = new FileInputStream(new File(Test_read_File.class.getClassLoader().getResource("byte_String/read_gbk.txt").getPath()));
		FileInputStream fis_utf8 = new FileInputStream(new File(Test_read_File.class.getClassLoader().getResource("byte_String/read_utf8.txt").getPath()));
		
		byte[] bs_gbk = new byte[fis_gbk.available()];
		fis_gbk.read(bs_gbk);
		fis_gbk.close();
		
		byte[] bs_utf8 = new byte[fis_utf8.available()];
		fis_utf8.read(bs_utf8);
		fis_utf8.close();
		
		System.out.println(Arrays.toString(bs_gbk));
		System.out.println(Arrays.toString(bs_utf8));
		
		
		String str_gbk = new String(bs_gbk, "gbk");      // 正常显示     Base64_read_gbk 测试读取中文问题
		String str_utf8 = new String(bs_utf8, "utf-8"); // 正常显示 Base64_read_utf8 测试读取中文问题
		
		String str_utf8_ = new String(bs_gbk, "utf-8"); // 异常显示 Base64_read_gbk ���Զ�ȡ��������
		String str_gbk_ = new String(bs_utf8, "gbk");    // 异常显示     Base64_read_utf8 娴嬭瘯璇诲彇涓枃闂
		
		System.out.println(str_gbk);
		System.out.println(str_utf8);
		
		System.out.println(str_utf8_);
		System.out.println(str_gbk_);
		
	}

}
