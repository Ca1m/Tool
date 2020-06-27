package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test_Time {

	public static void main(String[] args) throws ParseException {
		parse_format();
	}
	/**
	 * 将 某个时区的 String 的格式 转换为 另一个时区的 String 格式 
	 * @throws ParseException
	 */
	public static void parse_format() throws ParseException {

		String timeStr = "2017-8-24 11:17:10";
		/* 
		 * 一般是通过 Date 转换过来的，Date 转换到 String 的时候 需要指定时区，所以得到的String是有时区的
		 * (当你此刻输入一个 String 格式的时间时，这个String一定是有时区的，所以 要深刻理解 String 是有时区的，
		 *  任何时候需要操作该值的时候，都要指定 String 代表的时区)
		 */
		
		SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bjSdf.setTimeZone(TimeZone.getTimeZone("GMT"));  
		/*
		 *  指定 字符串的时区
		 *  将字符串时间按 国际时间 解析成Date对象
		 *  (因为你通过其他地方获取的 String 是国际时间格式，所以按照国际时间转换)
		 */
		Date date = bjSdf.parse(timeStr); 
		System.out.println(date.getTime());   
		/*  
		 * 如果你获取的 是 本地的字符串时间，那就需要按照 本地的 时区转换，
		 * 转换成功之后，获取的Date都是国际格式，但是在 toString的时候，会默认按本地时区输出，但是输出内容并不是国际时间，
		 *
		 * 但getTime()是统一的，所以我们认为 Date 没有时区的概念，任何的输出操作都会先获取本地时区，将国际时间格式的Date，按本地时区输出。(输出内容不可信)
		 */ 
		SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 东京
		tokyoSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo")); // 设置东京时区
		System.out.println("国际时间: " + timeStr + " 对应的东京时间为:" + tokyoSdf.format(date));
		/*
		 *  输出：
		 *    1503573430000
		 *    国际时间: 2017-8-24 11:17:10 对应的东京时间为:2017-08-24 20:17:10
		 * */
	}

}





