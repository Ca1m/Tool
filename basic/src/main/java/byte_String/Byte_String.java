package byte_String;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * Base64 编解码
 * @author Infosec_jy
 *
 * 在对接过程中，确定好用什么编码格式之后，
 * 发送信息时，对待发送的内容按 字符集 转成byte[] ,发送 
 * 接收消息时，对于接收到内容  new String(byte[],format) 转换成 字符串处理 
 * 
 * notice : 
 * 1. byte[] 的 GBK 转成 UTF-8 的字符串时，可能出现 ？？？？ （UTF-8 对于某些编号没有定义字符）
 * 2. byte[] 的 UTF-8 转成 gbk 的字符串时，可能出现 错误的中文
 */
public class Byte_String {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		Encoder encoder = Base64.getEncoder(); // 编码
		Decoder decoder = Base64.getDecoder(); // 解码
	
		String ss = "测试";
		System.out.println(ss);
		byte[] bs = ss.getBytes("utf-8");
		System.out.println("byte[] 的长度：" + bs.length);
		
		// 在组成 对象时，不合适使用 byte，需要使用 encoder 先编码，再赋值，最后 通过对 req 做 getEncode 得到 byte 发送
		String ss_1 = encoder.encodeToString(bs); 
		System.out.println(ss_1); 
		
		byte[] bs_2 = decoder.decode(ss_1);
		String ss_2 = new String(bs_2,"utf-8");
		System.out.println(ss_2);
		
		
		byte[] ss1 = encoder.encode(bs);
		
		
	}

}
