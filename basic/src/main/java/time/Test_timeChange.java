package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**   
 * @Description (��������) 
 * @Author jy
 * @Time 2019-07-23 18:06
 */
public class Test_timeChange {

	/**        
	 * @Author jy 
	 * @Time   2019-07-23 18:06
	 * @version 1.0 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// Calendar ��  Date
		//Test_timeChange.Calendar_And_Date();
		// Date �� String
		//Test_timeChange.Date_to_String();
		// String �� Date 
		//Test_timeChange.String_to_Date();
				
		//System.out.println(new Date());
		//System.out.println(new Date().getTime());
		
		String time = "20190807000000Z";
		SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String time_1 = sdf_1.format(Test_timeChange.parse(time));
		
		System.out.println(time_1);
		

		//  ������
		//String timeServer = "20190807080000";
		
		SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date audit = sdf_2.parse(time_1);
		String sAuditDateGMT = Test_timeChange.format(audit);
		System.out.println(sAuditDateGMT);
		
	}
	
	
	public static Date parse(String s) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.parse(s);
	}
	
	public static String format(Date d){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(d);
	}
	
	// Date ת�� Ϊ String
	public static void Date_to_String() throws ParseException {
		// Date ת String
		Date date = new Date();
		System.out.println(date); // ��ǰʱ�������ʾΪ ��Thu Jul 25 10:16:44 CST 2019

		SimpleDateFormat df_1 = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		String str_1 = df_1.format(date);
		System.out.println(str_1); // �����ʾΪ �� 20190725101644Z

		SimpleDateFormat df_2 = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df_2.setTimeZone(TimeZone.getTimeZone("GMT"));
		String str_2 = df_2.format(date);
		System.out.println(str_2); //�����ʾΪ�� 20190725021644Z  GMT ����ʱ���ʽ���й�ʱ�� = GMT + 8Сʱ��������� ʱ��Ҫ��8Сʱ

		SimpleDateFormat df_3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str_3 = df_3.format(date);
		System.out.println(str_3); // �����ʾΪ �� 20190725101644Z

	}
	// String ת��Ϊ  Date
	public static void String_to_Date() throws ParseException {
		// String ת��Ϊ Date ʱ��String��û�� Z �����ԣ���ʽ�лᴦ��
		//  �м� ��Ҫ�� "20190725102542Z" �� new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ��ʽת�����У�����ʱ��ʽ��ƥ��
		String str_4 = "20190725102542Z";
		SimpleDateFormat df_4 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date_4 = df_4.parse(str_4);
		System.out.println(str_4);
		
		SimpleDateFormat df_6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str_6 = df_6.format(date_4);
		System.out.println(str_6); // �����ʾΪ �� Thu Jul 25 10:25:42 CST 2019

		String str_5 = "2019-07-25 10:25:42Z"; // ��û��Zû��Ӱ��
		SimpleDateFormat df_5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date_5 = df_5.parse(str_5);
		System.out.println(date_5); // �����ʾΪ �� Thu Jul 25 10:25:42 CST 2019
	}

	// ����� Calendar ��  Date �� ��ϵ
	public static void Calendar_And_Date() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2019);
		c.set(Calendar.MONTH, 8); // ����Ľ���� 9�£���ʾ�µı�ʶ�� ��MONTH_OF_YEAR �� MONTH ��������ʹ��
		c.set(Calendar.DAY_OF_MONTH, 2); // ������ �� 2�£���ʶ�յı�ʶ�� DAY_OF_MONTH �� DATE �����߽��һ�£�����ʹ��ǰ��
		c.set(Calendar.HOUR_OF_DAY, 22); // �������� 22ʱ����ʾʱ�ı�ʶ �� HOUR_OF_DAY �� HOUR ������ HOUR Ϊ12��ʾ��������12Ĭ�Ͻ�λ���Ƽ�ʹ�� ǰ��
		c.set(Calendar.MINUTE, 30);
		c.set(Calendar.SECOND, 10);

		// ͨ��������Ҫ��ת Date
		Date date = c.getTime();
		System.out.println(date); // �����ʾΪ  Mon Sep 02 22:30:10 CST 2019  ����ΪDate��ʽ
		// �ر�ע�� c.set(xxxx,xxx); �κ�ֵû���ֶ����ã�����ȡ��ǰ�������ʱ��/ϵͳʱ�� 
	}

	// Ӧ�ó����� ���ݿ��ȡ 20190725101644Z�����Ϊ 2019-07-25 10:16:44 
	public static void demo() throws ParseException {
		// �ȸ��� String�ĸ�ʽ תΪ Date
		String str_6 = "20190725101644Z";
		SimpleDateFormat df_6 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date_6 = df_6.parse(str_6);
		System.out.println(date_6); // �����ʾΪ��Thu Jul 25 10:16:44 CST 2019
		// �ٸ�����Ҫ�ĸ�ʽ��תΪ String
		SimpleDateFormat df_7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str_7 = df_7.format(date_6);
		System.out.println(str_7); // �����ʾΪ�� 2019-07-25 10:16:44
	}
}
