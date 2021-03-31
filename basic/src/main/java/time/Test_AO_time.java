package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;




public class Test_AO_time {

	public static void main(String[] args) throws ParseException {
		
		
		Calendar c = Calendar.getInstance(); // 2020 07 02  10  20 30
		c.set(Calendar.YEAR, 2020);
		c.set(Calendar.MONTH, 9); // 从0开始计数
		c.set(Calendar.DATE, 2);
		c.set(Calendar.HOUR_OF_DAY, 12);
		c.set(Calendar.MINUTE, 20);
		c.set(Calendar.SECOND, 30);
		
		System.out.println(c.getTime());
		
		String _endTime = Time_Utils.formatZwithGMT(c.getTime()); // 当前为 GMT+8 的时区，修改为 GMT 的时区，时间 提前 8 小时
		
		System.out.println(c.getTime());
		
		
		
		Calendar c1 = Calendar.getInstance(); 
		
		System.out.println(c1.get(Calendar.MONTH));
		
		System.out.println(c1.getTime());
	}

	
	/**
	 *    将数据库读出来的 String (GMT)转换为 Date(当前的时区) 对象 (用来处理返回值)
	 * @param     (String)
	 * @return    (Date)   
	 * @throws    (ParseException)
	 * @Author 江岩 
	 * @Time   2019-06-05 10:12
	 * @version 1.0 
	 */
	public static Date parseLocal(String s) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.parse(s);
	}
	/**
	   *   将 Date 对象转换成 Sting 用来显示 (用来处理返回值)
	 * @param     (Date)
	 * @return    (String)   
	 * @throws    (ParseException)
	 * @Author 江岩 
	 * @Time   2019-06-05 10:12
	 * @version 1.0  
	 */
	public static String format(Date d) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(d);
	}
	/**
	 * 日期格式转字符串  GMT 北京时间前置8小时   AO查询日志时，将时间处理成 GMT时间
	 * @param    (Date) 
	 * @return   (String)      
	 * @throws   (ParseException)
	 * @Author 江岩 
	 * @Time   2019-06-05 10:13
	 * @version 1.0
	 * @memo  'Z' 格林威治标准时间
	 */
	public static String formatZwithGMT(Date d) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(d);
	}
	
}
