package top.yancc.hutool;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class Test_Date {

	public static void main(String[] args) {
		String dateStr = "2020-09-21 21:58:59";
		Date date = DateUtil.parse(dateStr);
		System.out.println(date);
	}

}
