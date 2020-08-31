package top.yancc.java.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * ������
 * @Author ����    
 * @Time 2019-06-05 10:11
 * �Ժ����������û��'Z'  ��û�� GMT 
 */
public class Test_time_2 {
	public static String separator = System.getProperty("line.separator");
	public static final String NameConnector = new String(new byte[] { 10 });

	/**
	 * �ַ���ת���ڸ�ʽ
	 * @param     (String)
	 * @return    (Date)   
	 * @throws    (ParseException)
	 * @Author ���� 
	 * @Time   2019-06-05 10:12
	 * @version 1.0 
	 */
	public static Date parse(String s) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.parse(s);
	}
	/**
	 * ���ڸ�ʽת�ַ���
	 * @param     (Date)
	 * @return    (String)   
	 * @throws    (ParseException)
	 * @Author ���� 
	 * @Time   2019-06-05 10:12
	 * @version 1.0
	 */
	public static String format(Date d) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(d);
	}
	/**
	 *  ���ڸ�ʽת�ַ���
	 * @Author ���� 
	 * @Time   2019-06-05 10:13
	 * @version 1.0
	 * @memo  'Z' �������α�׼ʱ��
	 */
	public static String formatZ(Date d) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		return df.format(d);
	}
	/**
	 * ���ڸ�ʽת�ַ��� : ���� �鵵����鿴ʱ��ת��
	 * @Author ���� 
	 * @Time   2019-07-23 17:45
	 * @version 1.0
	 */
	public static String format_archiveTime(Date d) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}
}






