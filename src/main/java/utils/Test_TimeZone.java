package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date 和  String
 * 关于时间的格式，在应用中一般用 Stirng 和 Date 来表示，
 *
 * Date--> String 没有时区的概念，Date中的内容都是 国际的，当getHour() toString() 等方法时，
 * 通过 获取本地的时区对Date进行格式转换再输出
 * 
 * String --> Date 时，传递的 String，默认是本地的时区时间，如果传递的参数不是本地时区时间时，
 * 在转换为 Date时，需要指定时区
 */
public class Test_TimeZone {

	public static void main(String[] args) throws ParseException {
		
		Date date = new Date();
		System.out.println(date.getTime());  // 全球统一值
		System.out.println(date.toString()); // toString的时候获取本地时区，做了时间转换，并不是Date的真实时间
		System.out.println();
		
		String date_str_1 = format(date);
		System.out.println(date_str_1);
		
		Date date_1 = parse(date_str_1);
		System.out.println(date_1.toString());
	
		/*  查看 支持的 TimeZone
		String[] ss = TimeZone.getAvailableIDs();
		for (String string : ss) {
			if (string.indexOf("GMT") > 0) {
				System.out.println(string);
			}
		}
		*/
	}
	
	/**
	 * 日期格式转字符串   (Date -- > String)
	 * Date 转字符串，格式随便定义都可以
	 */
	public static String format(Date date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		//df.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
		df.setTimeZone(TimeZone.getTimeZone("GMT")); // 国际时间,决定输出的 String 是国际格式

		return df.format(date);
	}
	/**
	 * 字符串转日期格式    (String --> Date)
	 */
	public static Date parse(String s) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		//df.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8")); 
		df.setTimeZone(TimeZone.getTimeZone("GMT")); // 指定参数 String为国际格式，输出时，按本地时区输出
		
		return df.parse(s);
	}
}
