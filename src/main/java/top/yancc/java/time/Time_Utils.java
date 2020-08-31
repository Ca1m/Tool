package top.yancc.java.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;



public class Time_Utils {

	public static void main(String[] args) throws ParseException {
		
		testCalendar();
		
		
		
	}
	
	// 
	public static void testFormat() {
		Date date = new Date();
		System.out.println(date);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String ss = df.format(date);
		
		System.out.println(ss);
	}
	
	public static void testCalendar() throws ParseException {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2020);
		c.set(Calendar.MONTH, 8 - 1);
		c.set(Calendar.DATE, 5);
		c.set(Calendar.HOUR, 18); // date_time_archive.getMonth()
		// 系统时间为  下午，设置值大于 12 ，calandar 认为 半天过去了，应该是下一天的时间，day+1 hour-12
		c.set(Calendar.MINUTE, 56);
		c.set(Calendar.SECOND, 0);
		
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String _st_archive = sdf.format(c.getTime());
		SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyyMMdd");
		Date audit = sdf_1.parse(_st_archive);
		String sAuditDateGMT = format(audit);
		System.out.println(sAuditDateGMT);
		*/
		
		System.out.println(c.getTime());
		
		String time_ = formatZwithGMT(c.getTime());
		
		System.out.println(time_);
		
	}
	
	public static String format(Date d){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(d);
	}
	
	
	public static void testDateTime() {
		long time = System.currentTimeMillis();
		System.out.println(time);

		Date date = new Date(time); 
		System.out.println(date); // 只有输出的时候，才会按照当前时区输出
		
		long time_1 = date.getTime();
		System.out.println(time_1);
		
		long time_2 = 1596527360591l;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date(time_2)));
	}
	
	/**
	 * 日期格式转字符串  GMT 北京时间前置8小时
	 * @param    (Date) 
	 * @return   (String)      
	 * @throws   (ParseException)
	 * @Author 江岩 
	 * @Time   2019-06-05 10:13
	 * @version 1.0
	 * @memo  'Z' 格林威治标准时间
	 * 将 Date 对象转换成 String 为了保存到数据库
	 */
	public static String formatZwithGMT(Date d) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(d);
	}
	
	
	
}
